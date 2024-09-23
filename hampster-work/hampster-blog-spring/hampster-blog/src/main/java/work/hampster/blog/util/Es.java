package work.hampster.blog.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class Es {
    public static RestClient restClient;

    @Autowired
    public Es(RestClient restClient) {
        this.restClient = restClient;
    }

    public static HashMap<String, Object> search(String body, int page, int pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        ArrayList re = getSearchRe(body);
        map.put("content", getPage(re, page, pageSize));
        map.put("max_page", (int)Math.ceil(re.size() / (float) pageSize));
        return map;
    }

//    public static int getMxPage(String body, int pageSize) {
//        return (int)Math.ceil(getSearchRe(body).size() / (float) pageSize);
//    }

    private static List<HashMap<String, String>> getPage(ArrayList<HashMap<String, String>> result, int page, int pageSize) {
        return new ArrayList<>(result.subList((page - 1) * pageSize, page * pageSize > result.size() ? result.size() : page * pageSize).stream().toList());
    }

    private static ArrayList<HashMap<String, String>> getSearchRe(String body) {
        Request request = new Request(
                "GET",
                "/blog_content/_search");
        String jsonString = body;
        request.setJsonEntity(jsonString);

        Response response = null;
        AtomicReference<String> highlights = new AtomicReference<>();
        ArrayList<HashMap<String, String>> ar = new ArrayList<>();
        try {
            response = restClient.performRequest(request);
            String responseBody = EntityUtils.toString(response.getEntity());

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                jsonNode.get("hits").get("hits").forEach(hit -> {
                    StringBuilder sb = new StringBuilder();
                    hit.get("highlight").get("b_content").forEach(i -> sb.append(i));
                    String text = sb.toString();

                    String regex = "\\\\r|\\\\n|```|#| ";
                    highlights.set(text.replaceAll(regex, ""));

                    HashMap hm = new HashMap();
                    hm.put("b_highlights", highlights.get().substring(1, highlights.get().length() < 300 ? highlights.get().length() : 300));
                    hm.put("b_name", hit.get("_source").get("b_name").toString().replaceAll("\"", ""));
                    hm.put("bt_name", hit.get("_source").get("bt_name").toString().replaceAll("\"", ""));
                    ar.add(hm);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ar;
    }

}
