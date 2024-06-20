package work.hampster.blog.controller;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import work.hampster.blog.mapper.BlogMapper;
import work.hampster.blog.model.Blog;
import work.hampster.blog.service.BlogService;
import work.hampster.blog.service.BlogTypeService;
import work.hampster.blog.util.Redis;

import java.util.ArrayList;
import java.util.List;

import static com.baomidou.mybatisplus.extension.handlers.GsonTypeHandler.getGson;

@RestController
@CrossOrigin(origins = "http://localhost")
public class BlogJsonController {

    private final BlogTypeService blogTypeService;
    private final BlogService blogService;
    private final ServiceImpl<BlogMapper, Blog> blogServicePlus;

    @Autowired
    public BlogJsonController(BlogTypeService blogTypeService, ServiceImpl blogServicePlus, BlogService blogService) {
        this.blogTypeService = blogTypeService;
        this.blogServicePlus = blogServicePlus;
        this.blogService = blogService;
    }

    @GetMapping(value = "/json/index/bt_name", produces = "application/json; charset=utf-8")
    public String indexBtName(HttpServletRequest rq) {
//        rq.getParameterMap().forEach((k, v) -> System.out.println(k + " " + Arrays.toString(v)));
        return getGson().toJson(Redis.readAndWrite("bt_name", () -> blogTypeService.cQuery(rq, 1, 1000), 15));
    }

    @GetMapping(value = "/json/index/b_name", produces = "application/json; charset=utf-8")
    public String indexBName() {
        return getGson().toJson(Redis.readAndWrite("b_name", () -> blogServicePlus.getBaseMapper().selectBlogNames(), 16));
    }

    @GetMapping(value = "/json/search/{bt_name}/b_name/{page}", produces = "application/json; charset=utf-8")
    public String searchBNameInBt(@PathVariable String bt_name, @PathVariable int page) {
        return getGson().toJson(Redis.readAndWrite(bt_name + "_b_name_" + page, () -> blogServicePlus.getBaseMapper().selectBnInBt(bt_name, (page - 1) * 4, 4), 17));
    }

    @GetMapping(value = "/json/search/{bt_name}/b_name/maxpage", produces = "application/json; charset=utf-8")
    public String searchBNameInBtMxPage(@PathVariable String bt_name) {
        return getGson().toJson(Redis.readAndWrite(bt_name + "_b_name_max_page", () -> (int)Math.ceil(blogServicePlus.getBaseMapper().countBnInBt(bt_name) / (float) 4), 18));
    }

    @GetMapping(value = "/json/search/blur/{info}/{page}", produces = "application/json; charset=utf-8")
    public String searchBlue(@PathVariable String info, @PathVariable int page) {
        return getGson().toJson(Redis.readAndWrite("es_blur_" + info + "_" + page, () -> blogService.getBlurSearch(info, page, 4), 5));
    }

    @GetMapping(value = "/json/search/blur/{info}/maxpage", produces = "application/json; charset=utf-8")
    public String searchBlueMxPage(@PathVariable String info) {
        return getGson().toJson(Redis.readAndWrite("es_blur_" + info + "_max_page", () -> blogService.getBlurSearchMxPage(info,  4), 5));
    }

    @GetMapping(value = "/json/search/blur/{bt_type}/{info}/{page}", produces = "application/json; charset=utf-8")
    public String searchBlue(@PathVariable String bt_type, @PathVariable String info, @PathVariable int page) {
        return getGson().toJson(Redis.readAndWrite("es_blur_" + bt_type + "_" + info + "_" + page, () -> blogService.getBtBlurSearch(bt_type, info, page, 4), 6));
    }

    @GetMapping(value = "/json/search/blur/{bt_type}/{info}/maxpage", produces = "application/json; charset=utf-8")
    public String searchBlueMxPage(@PathVariable String bt_type, @PathVariable String info) {
        return getGson().toJson(Redis.readAndWrite("es_blur_" + bt_type + "_" + info + "_max_page", () -> blogService.getBtBlurSearchMxPage(bt_type, info, 4), 6));
    }

    @GetMapping(value = "/json/blog/ALL/b_name", produces = "application/json; charset=utf-8")
    public String blogNames() {
        return getGson().toJson(Redis.readAndWrite("b_name_all", () -> blogServicePlus.getBaseMapper().selectAllBlogNames(), 11));
    }

    @GetMapping(value = "/json/blog/{bt_type}/b_name", produces = "application/json; charset=utf-8")
    public String blogNamesInBt(@PathVariable String bt_type) {
        return getGson().toJson(Redis.readAndWrite(bt_type + "_b_name_all", () -> blogServicePlus.getBaseMapper().selectAllBlogNamesInBt(bt_type), 12));
    }

    @GetMapping(value = "/json/blog/{info}/getsummary", produces = "application/json; charset=utf-8")
    public String blogGetSummary(@PathVariable String info) {
        return getGson().toJson(Redis.readAndWrite(info + "_summary", () -> blogService.getBlogSummary(info), 20));
    }

    @GetMapping(value = "/json/blog/{b_name}", produces = "application/json; charset=utf-8")
    public String blogGetContent(@PathVariable String b_name) {
        return getGson().toJson(Redis.readAndWrite(b_name + "_content", () -> blogServicePlus.getBaseMapper().selectBlogContent(b_name), 22));
    }

}
