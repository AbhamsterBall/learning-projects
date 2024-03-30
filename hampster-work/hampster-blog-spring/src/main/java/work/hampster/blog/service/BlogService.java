package work.hampster.blog.service;

import work.hampster.blog.mapper.BlogMapper;
import work.hampster.blog.model.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService extends EntityService<BlogMapper, Blog> {
}
