package work.hampster.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import work.hampster.blog.mapper.BlogMapper;
import work.hampster.blog.mapper.BlogTypeMapper;
import work.hampster.blog.model.Blog;
import work.hampster.blog.model.BlogType;

@Service
class BlogTypeServicePlus extends ServiceImpl<BlogTypeMapper, BlogType> {}

@Service
class BlogServicePlus extends ServiceImpl<BlogMapper, Blog> {}
