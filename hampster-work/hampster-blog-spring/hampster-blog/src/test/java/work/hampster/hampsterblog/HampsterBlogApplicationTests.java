package work.hampster.hampsterblog;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import work.hampster.blog.model.Blog;

//@SpringBootTest
class HampsterBlogApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(Math.ceil(6 / (float)4));
    }

    @Test
    void testObject() {
//        int a;
//        System.out.println(a); // java: variable a might not have been initialized

        Blog b = new Blog();
        Blog c = new Blog();
        b.setBName("Hello");
        c.setBName("Hello");

        System.out.println(b.equals(c));
        System.out.println(b.hashCode() == c.hashCode());
        System.out.println(b == c);
    }

    @Test
    void testApi() {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost/blog-api/json/index/bt_name");

        driver.quit();
    }

}
