package work.hampster.service.impl;

import org.springframework.http.MediaType;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import work.hampster.service.AIProcessService;

@Service
public class AIProcessServiceImpl implements AIProcessService {

    private final ChatClient cloudClient;
    private final ChatClient localClient;

    @Value("${spring.ai.is-local}")
    private String isLocal;

    public AIProcessServiceImpl(
            @Qualifier("cloudChatModel") ChatModel cloudModel,
            @Qualifier("localChatModel") ChatModel localModel) {
        this.cloudClient = ChatClient.builder(cloudModel).build();
        this.localClient = ChatClient.builder(localModel).build();
    }

    @Override
    public String process(String prompt) {
        ChatClient client = isLocal.equals("1") ? localClient : cloudClient;
        return client.prompt(prompt).call().content();
    }

    @Override
    public String process(String prompt, MultipartFile file) {
        // 有图片，必须走云端视觉模型，不能走本地纯文本模型
        ChatClient client = cloudClient;

        Resource imageResource = file.getResource();
        String contentType = file.getContentType();
        MediaType mediaType = contentType != null
                ? MediaType.parseMediaType(contentType)
                : MediaType.IMAGE_JPEG;

        return client.prompt()
                .user(u -> u.text(prompt)
                        .media(mediaType, imageResource))
                .call()
                .content();
    }
}
