package work.hampster.blog.controller;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import work.hampster.blog.mapper.BlogMapper;
import work.hampster.blog.model.Blog;
import work.hampster.blog.service.BlogTypeService;
import work.hampster.blog.util.Redis;

import static com.baomidou.mybatisplus.extension.handlers.GsonTypeHandler.getGson;

@RestController
@CrossOrigin(origins = "http://localhost")
public class IndexJsonController {

    private final BlogTypeService blogTypeService;
    private final ServiceImpl<BlogMapper, Blog> blogServicePlus;

    @Autowired
    public IndexJsonController(BlogTypeService blogTypeService, ServiceImpl blogServicePlus, RedisTemplate redisTemplate) {
        this.blogTypeService = blogTypeService;
        this.blogServicePlus = blogServicePlus;
    }

    @GetMapping(value = "/json/index/bt_name", produces = "application/json; charset=utf-8")
    public String indexBtName(HttpServletRequest rq) {
//        rq.getParameterMap().forEach((k, v) -> System.out.println(k + " " + Arrays.toString(v)));
        return getGson().toJson(Redis.readAndWrite("bt_name", () -> blogTypeService.cQuery(rq, 1, 1000), 15));
    }

    @GetMapping(value = "/json/index/b_name", produces = "application/json; charset=utf-8")
    public String indexBName() {
        return getGson().toJson(Redis.readAndWrite("b_name", () -> blogServicePlus.getBaseMapper().selectBlogNames(), 15));
    }

}
