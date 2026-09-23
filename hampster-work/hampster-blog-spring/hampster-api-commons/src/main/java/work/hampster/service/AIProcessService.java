package work.hampster.service;

import org.springframework.web.multipart.MultipartFile;

public interface AIProcessService {
    String process(String prompt);
    String process(String prompt, MultipartFile file);
}
