package work.hampster.blog.service;

import work.hampster.blog.mapper.BlogMapper;
import work.hampster.blog.model.Blog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface BlogService extends EntityService<BlogMapper, Blog> {

    List<HashMap<String, String>> getBlurSearch(String info, int page, int pageSize);
    List<HashMap<String, String>> getBtBlurSearch(String btType, String info, int page, int pageSize);

    int getBlurSearchMxPage(String info, int pageSize);
    int getBtBlurSearchMxPage(String btType, String info, int pageSize);

    String getBlogSummary(String info);

}
