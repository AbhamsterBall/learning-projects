package work.hampster.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import work.hampster.client.OcrClient;
import work.hampster.service.AIProcessService;
import work.hampster.transfer.AIDTO;
import work.hampster.util.AjaxResult;

import java.io.IOException;

@RestController
@RequestMapping("/ai")
@RefreshScope
public class AIController {

    @Resource
    AIProcessService processService;

    @Value("${spring.ai.is-local}")
    private String isLocal;

    @PostMapping(value = "/process", produces = "application/json; charset=utf-8")
    public AjaxResult process(AIDTO aiDTO) throws IOException {
        // 把 OCR 结果替换进 prompt 的占位符
        String prompt = aiDTO.getPrompt();

        if (isLocal.equals("1")) {
            if (ObjectUtils.isNotEmpty(aiDTO.getFile())) {
                MultipartFile file = aiDTO.getFile();

                OcrClient ocrClient = new OcrClient();
                String result = ocrClient.ocr(file);

                // 把 OCR 结果替换进 prompt 的占位符
                prompt = prompt.replace("{OCR_RESULT}", result);

            }

            ObjectMapper mapper = new ObjectMapper();
            String re = processService.process(prompt);
            Object data;
            if (re != null && !re.isBlank()) {
                String trimmed = re.trim();
                if (trimmed.startsWith("{") || trimmed.startsWith("[")) {
                    data = mapper.readTree(re);   // 对象/数组
                } else {
                    data = trimmed;               // 纯字符串
                }
            } else {
                data = null;
            }
            return AjaxResult.success(data);
        } else {
            ObjectMapper mapper = new ObjectMapper();
            String re = processService.process(prompt, aiDTO.getFile());
            JsonNode jsonNode = mapper.readTree(re);

            // 没有图片，纯文本
            return AjaxResult.success(jsonNode);
        }
    }

}
