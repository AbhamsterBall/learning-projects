package work.hampster.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.hampster.blog.mapper.BlogMapper;
import work.hampster.blog.model.Blog;
import work.hampster.blog.model.BlogType;
import work.hampster.blog.service.BlogService;

import java.util.List;
import java.util.Map;

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

}
