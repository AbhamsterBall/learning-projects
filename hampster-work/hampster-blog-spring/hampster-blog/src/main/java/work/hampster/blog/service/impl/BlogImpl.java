package work.hampster.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.hampster.blog.mapper.BlogMapper;
import work.hampster.blog.model.Blog;
import work.hampster.blog.service.BlogService;
import work.hampster.blog.util.Es;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class BlogImpl extends EntityServiceImpl<BlogMapper, Blog> implements BlogService {

    @Autowired
    public BlogImpl(ServiceImpl<BlogMapper, Blog> blogServicePlus) {
        super(blogServicePlus);
    }

    @Override
    protected Class<Blog> getEntityClass() {
        return Blog.class;
    }

    @Override
    public List<HashMap<String, String>> getBlurSearch(String info, int page, int pageSize) {
        return Es.search("{   \"query\": {     \"match\": { \"b_content\": \"" + info + "\" }  }, \"highlight\": {             \"type\" : \"unified\", \"number_of_fragments\" : 3, \"fragment_size\": 250,\"fields\": {           \"b_content\": { }     }  },    \"_source\": [\"b_context\", \"b_name\", \"bt_name\"] }",
                page, pageSize);
    }

    @Override
    public List<HashMap<String, String>> getBtBlurSearch(String btType, String info, int page, int pageSize) {
        return Es.search("{ \"query\": {\n" +
                "    \"bool\": {\n" +
                "      \"must\": [\n" +
                "        { \"match\": { \"b_content\": \"" + info + "\" } },\n" +
                "        { \"match\": { \"bt_name\": \"" + btType + "\" } }\n" +
                "      ]\n" +
                "    }\n" +
                "  }, \"highlight\": {             \"type\" : \"unified\", \"number_of_fragments\" : 3, \"fragment_size\": 250,\"fields\": {           \"b_content\": { }     }  },    \"_source\": [\"b_context\", \"b_name\", \"bt_name\"] }",
                page, pageSize);
    }

    @Override
    public int getBlurSearchMxPage(String info, int pageSize) {
        return Es.getMxPage("{   \"query\": {     \"match\": { \"b_content\": \"" + info + "\" }  }, \"highlight\": {             \"type\" : \"unified\", \"number_of_fragments\" : 3, \"fragment_size\": 250,\"fields\": {           \"b_content\": { }     }  },    \"_source\": [\"b_context\", \"b_name\", \"bt_name\"] }",
                pageSize);
    }

    @Override
    public int getBtBlurSearchMxPage(String btType, String info, int pageSize) {
        return Es.getMxPage("{ \"query\": {\n" +
                        "    \"bool\": {\n" +
                        "      \"must\": [\n" +
                        "        { \"match\": { \"b_content\": \"" + info + "\" } },\n" +
                        "        { \"match\": { \"bt_name\": \"" + btType + "\" } }\n" +
                        "      ]\n" +
                        "    }\n" +
                        "  }, \"highlight\": {             \"type\" : \"unified\", \"number_of_fragments\" : 3, \"fragment_size\": 250,\"fields\": {           \"b_content\": { }     }  },    \"_source\": [\"b_context\", \"b_name\", \"bt_name\"] }",
                pageSize);
    }

    @Override
    public String getBlogSummary(String info) {
        String re = "";
        try {
            String pythonProgram = "F:\\JAVA\\20231010\\hampster-work\\hampster-blog-spring\\hampster-blog\\src\\main\\resources\\summary.py";
            String arg = info;
            Process process = Runtime.getRuntime().exec("python " + pythonProgram + " " + arg);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                ObjectMapper objectMapper = new ObjectMapper();

                JsonNode jsonNode = objectMapper.readTree(line);
                re = jsonNode.get("answer").asText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re;
    }
}
