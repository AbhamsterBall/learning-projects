package work.hampster.service;

import org.springframework.web.multipart.MultipartFile;

public interface AIProcessService {
    String process(String prompt);
    String process(String prompt, MultipartFile file);
}

// TODO
//抱歉，是我之前没理解到位。你用的是 **Spring AI**，那思路就要换一下了——不需要外部框架，直接用 Spring AI 自己的 **Tool Calling（工具调用）** 能力就能实现。
//
//        ### 核心思路：把“查数据库”定义成一个 Tool
//
//Spring AI 的 **Tool Calling** 机制，就是让大模型可以主动调用你预先定义好的 Java 方法。你要做的就是把“查数据库”包装成一个 `@Tool` 方法，模型会自动判断什么时候该调它、传什么参数。
//
//        ### 具体实现步骤
//
//**1. 定义一个 Tool 类，封装数据库查询**
//
//        ```java
//@Component
//public class DatabaseQueryTools {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    public DatabaseQueryTools(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Tool(description = "执行SELECT查询并返回结果，仅允许查询操作")
//    public List<Map<String, Object>> executeQuery(
//            @ToolParam(description = "要执行的SQL SELECT语句") String sql) {
//
//        // 安全校验：只允许 SELECT
//        String trimmed = sql.trim().toLowerCase();
//        if (!trimmed.startsWith("select")) {
//            throw new IllegalArgumentException("仅允许SELECT查询");
//        }
//        // 强制加 LIMIT 防止全表扫描
//        if (!trimmed.contains("limit")) {
//            sql = sql + " LIMIT 100";
//        }
//
//        return jdbcTemplate.queryForList(sql);
//    }
//}
//```
//
//        **2. 在 ChatClient 中注册这个 Tool，让模型能自动调用**
//
//        ```java
//@Configuration
//public class ChatConfig {
//
//    @Bean
//    public ChatClient chatClient(ChatClient.Builder builder, DatabaseQueryTools dbTools) {
//        return builder
//                .defaultSystem("你是一个数据查询助手。用户问数据相关问题时，调用工具查询数据库。")
//                .defaultTools(dbTools)  // 关键：注册工具
//                .build();
//    }
//}
//```
//
//        **3. 写一个 Controller 直接对话**
//
//        ```java
//@RestController
//public class DataController {
//
//    private final ChatClient chatClient;
//
//    public DataController(ChatClient chatClient) {
//        this.chatClient = chatClient;
//    }
//
//    @GetMapping("/ask")
//    public String ask(@RequestParam String question) {
//        // 模型会自己判断是否调用 executeQuery，并传入 SQL
//        return chatClient.prompt()
//                .user(question)
//                .call()
//                .content();
//    }
//}
//```
//
//        ### 流程是什么样子的
//
//当你请求 `/ask?question=订单表有多少条记录`：
//
//        1. Spring AI 把用户问题 + 工具描述一起发给本地模型；
//        2. 本地模型判断需要查数据库，生成 SQL 并“调用” `executeQuery`；
//        3. Spring AI 执行你写的 Java 方法，拿到结果；
//        4. 结果返回给模型，模型组织成自然语言回答。
//
//        ### 关于你本地 AI 的配置
//
//如果你的本地 AI 是 **Ollama**，加一个依赖和配置就行：
//
//        ```xml
//        <dependency>
//    <groupId>org.springframework.ai</groupId>
//    <artifactId>spring-ai-starter-model-ollama</artifactId>
//</dependency>
//        ```
//
//        ```properties
//spring.ai.ollama.base-url=http://localhost:11434
//spring.ai.ollama.chat.options.model=qwen2.5-coder:7b
//```
//
//        **关键提醒**：本地小模型（7B 级别）生成 SQL 的准确率有限，建议先让它从简单表结构开始试。如果需要更稳，可以额外加一个 **MCP Server** 专门暴露“查表结构”的工具，让模型先看表再写 SQL。
//
//你现在本地跑的是哪个模型？数据库是什么？我可以给你更具体的 `@Tool` 写法和提示词。