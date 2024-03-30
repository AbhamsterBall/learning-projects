package work.hampster.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.hampster.blog.mapper.BlogTypeMapper;
import work.hampster.blog.model.BlogType;
import work.hampster.blog.service.BlogTypeService;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class BlogTypeImpl extends EntityServiceImpl<BlogTypeMapper, BlogType> implements BlogTypeService {

    @Autowired
    public BlogTypeImpl(ServiceImpl<BlogTypeMapper, BlogType> blogTypeServicePlus) {
        super(blogTypeServicePlus);
    }

    @Override
    protected Class<BlogType> getEntityClass() {
        return BlogType.class;
    }

}
