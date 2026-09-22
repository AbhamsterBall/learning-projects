package work.hampster.service.impl;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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
}
