package work.hampster.transfer;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AIDTO {
    private String prompt;
    private MultipartFile file;   // 上传的图片
}
