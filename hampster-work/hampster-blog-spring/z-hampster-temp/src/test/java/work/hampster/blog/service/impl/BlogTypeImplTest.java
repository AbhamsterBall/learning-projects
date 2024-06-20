package work.hampster.blog.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.RequestBase;
import co.elastic.clients.elasticsearch.core.GetRequest;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import com.google.gson.Gson;
import com.sendgrid.Method;
import com.sendgrid.SendGrid;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.ValidationRequest;
import org.apache.http.HttpHost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import work.hampster.blog.service.BlogService;
import work.hampster.blog.service.BlogTypeService;
import work.hampster.blog.model.BlogType;
import work.hampster.blog.model.Blog;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.io.IOException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
class BlogTypeImplTest {

    @Autowired
    BlogTypeService blogTypeService;

    @Autowired
    BlogService blogService;
    @Autowired
    BlogServicePlus blogServicePlus;

    @Test
    void test() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("btId", "1");
        System.out.println(blogTypeService.cQuery(request, 1, 100));
    }

    @Test
    void toTest() {
        String input = "btId";
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                output += "_" + (char) (c + 32);
            } else {
                output += c;
            }
        }
        System.out.println(output);
    }

    @Test
    void testGetBlogNameList() {
//        System.out.println(blogTypeService.cQuery(new MockHttpServletRequest(), 1, 100));
        List<BlogType> list = (List<BlogType>) blogTypeService.cQuery(new MockHttpServletRequest(), 1, 100).get("data");

        HashMap<String, ArrayList<String>> blogNames = new HashMap<>();
        for (BlogType blogType : list) {
            ArrayList<String> names = new ArrayList<String>();
            MockHttpServletRequest request = new MockHttpServletRequest();
            request.addParameter("bType", blogType.getBtId().toString());
            List<Blog> blogs = (List<Blog>) blogService.cQuery(request, 1, 100).get("data");
            for (Blog blog : blogs) {
                names.add(blog.getBName());
            }
            blogNames.put(blogType.getBtId().toString(), names);
        }

        System.out.println(blogNames);

        System.out.println(blogServicePlus.getBaseMapper().selectBlogNames());

    }

    @Test
    void testElasticSearch() {
        // 创建 Elasticsearch REST 客户端连接
        RestClient restClient = RestClient.builder(new HttpHost("47.94.167.22", 9200, "http")).build();

        // 发送 GET 请求查询数据
        try {
            // 构建查询请求
            URIBuilder uriBuilder = new URIBuilder("/blog_content/_search");
            uriBuilder.addParameter("q", "b_content:JAVA");

            // 构建请求
            URI uri = uriBuilder.build();
            Request request = new Request("GET", uri.toString());

            // 发送请求并获取响应
            Response response = restClient.performRequest(request);

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonNode = objectMapper.readTree(EntityUtils.toString(response.getEntity()));
                System.out.println(jsonNode.get("hits").get("hits").get(0).get("_source"));
                System.out.println(jsonNode.get("hits").get("hits").get(0).get("_source").get("b_name"));
                System.out.println(jsonNode.get("hits").get("hits").get(0).get("_source").get("b_content"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 关闭 REST 客户端连接
            restClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Autowired
    private RestClient restClient;

    @Test
    public void testRestClient() {

        Request request = new Request(
                "GET",
                "/blog_content/_search");
        String jsonString = "{   \"query\": {     \"match\": { \"b_content\": \"注意\" }  }, \"highlight\": {             \"type\" : \"unified\", \"number_of_fragments\" : 1, \"fragment_size\": 2000,\"fields\": {           \"b_content\": { }     }  },    \"_source\": [\"b_context\", \"b_name\", \"bt_name\"] }";
        request.setJsonEntity(jsonString);

        Response response = null;
        try {
            response = restClient.performRequest(request);
            String responseBody = EntityUtils.toString(response.getEntity());

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                jsonNode.get("hits").get("hits").forEach(hit -> {
                    String text = hit.get("highlight").get("b_content").get(0).toString();
                    System.out.println();

                    String regex = "\\\\r|\\\\n|```|#| ";
                    String re = text.replaceAll(regex, "");
                    System.out.println(re.substring(1, re.length() < 300 ? re.length() : 300));
                    System.out.println(hit.get("_source").get("b_name").toString().replaceAll("\"", ""));
                    System.out.println(hit.get("_source").get("bt_name").toString().replaceAll("\"", ""));
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}