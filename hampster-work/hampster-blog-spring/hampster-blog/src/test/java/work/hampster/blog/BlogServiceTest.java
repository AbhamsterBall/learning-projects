package work.hampster.blog;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BlogServiceTest {
    @Test
    public void test() {
        String re = "";
        try {
            String pythonProgram = "F:\\JAVA\\20231010\\hampster-work\\hampster-blog-spring\\hampster-blog\\src\\main\\resources\\summary.py";
            String arg = "添加后可以在visit-link里面的Configuration-Management -> Configurations中看到";
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
        System.out.println(re);
    }
}
