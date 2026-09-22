package work.hampster.config;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.ai.chat.model.ChatModel;

@Configuration
public class ModelConfig {

    @Value("${local.llama.base-url}")
    private String localBaseUrl;

    @Value("${local.llama.model-name}")
    private String localModelName;

    @Value("${spring.ai.openai.base-url}")
    private String url;
    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    // 本地模型 Bean
    @Bean("localChatModel")
    public ChatModel localChatModel() {
        OpenAiApi api = OpenAiApi.builder()
                .baseUrl(localBaseUrl)
                .apiKey("local-key") // 本地服务不校验，随便填
                .build();
        return OpenAiChatModel.builder()
                .openAiApi(api)
                .defaultOptions(OpenAiChatOptions.builder()
                        .maxTokens(1536)      // 关键，单据 JSON 够用
                        .model(localModelName)
                        .build())
                .build();
    }

    // 云端模型 Bean（标注 @Primary 作为默认）
    @Bean("cloudChatModel")
    @Primary
    public ChatModel cloudChatModel() {
        OpenAiApi api = OpenAiApi.builder()
                .baseUrl(url)
                .apiKey(apiKey)
                .build();
        return OpenAiChatModel.builder()
                .openAiApi(api)
                .defaultOptions(OpenAiChatOptions.builder()
                        .model("qwen3-vl-flash")
                        .build())
                .build();
    }
}