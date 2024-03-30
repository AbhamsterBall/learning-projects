package tries;

import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.junit.Test;

import java.io.IOException;

public class TestElastic {

    @Test
    public void test() {
        // 创建 Elasticsearch REST 客户端连接
        RestClient restClient = RestClient.builder(
                        new HttpHost("localhost", 9200, "http"))
                .build();

        // 发送 GET 请求查询数据
        try {
            // 构建查询请求
                Request request = new Request("POST", "/mynewindex/_search");
            // 替换 "your_index" 为你要查询的索引名称

            // 添加请求体（如果有的话）
            request.setJsonEntity("{\n" +
                    "  \"query\": {\n" +
                    "    \"match\": {\n" +
                    "      \"pname\": \"车\"\n" +
                    "    }\n" +
                    "  }\n" +
                    "}");

            // 发送请求并获取响应
            Response response = restClient.performRequest(request);

            // 处理响应结果
            // 输出响应内容
//            System.out.println("Response Status: " + response.getStatusLine());
            System.out.println("Response Body: " + EntityUtils.toString(response.getEntity()));

            // 关闭 REST 客户端连接
            restClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
