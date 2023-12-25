package tries;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.io.IOException;

public class TestElastic {

    @Test
    public void test() {
        // 创建 Elasticsearch 客户端连接
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // 构建搜索请求
        SearchRequest searchRequest = new SearchRequest("myindex"); // 替换为你的索引名称
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery()); // 查询所有文档
        searchRequest.source(searchSourceBuilder);

        try {
            // 执行搜索请求
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            // 处理搜索结果
            // 这里可以对搜索结果进行处理，比如获取 hits、聚合等

            // 关闭客户端连接
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
