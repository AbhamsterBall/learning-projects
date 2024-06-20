package work.hampster.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import work.hampster.blog.model.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper extends BaseMapper<Blog> {
    @Select("SELECT bt.bt_name, GROUP_CONCAT(b.b_name) AS blogNames FROM blog_type bt LEFT JOIN (SELECT b.*, ROW_NUMBER() OVER(PARTITION BY b.b_type ORDER BY b.b_time DESC) AS row_num FROM blog b) b ON bt.bt_id = b.b_type WHERE b.row_num <= 3 GROUP BY bt.bt_id HAVING blogNames IS NOT NULL")
    List<Map<String, Object>> selectBlogNames();

    @Select("select b_name from blog where b_type=(select bt_id from blog_type where bt_name=#{type_name}) order by b_time desc limit #{start}, #{size}")
    List<Map<String, Object>> selectBnInBt(@Param("type_name") String type_name, @Param("start") int start, @Param("size") int size);

    @Select("select count(*) from blog where b_type=(select bt_id from blog_type where bt_name=#{type_name}) ")
    int countBnInBt(@Param("type_name") String type_name);

    @Select("select b_name from blog order by b_time desc")
    List<Map<String, Object>> selectAllBlogNames();

    @Select("select b_name from blog where b_type=(select bt_id from blog_type where bt_name=#{type_name}) order by b_time desc")
    List<Map<String, Object>> selectAllBlogNamesInBt(@Param("type_name") String type_name);

    @Select("select b_content from blog where b_name=#{blog_name}")
    String selectBlogContent(@Param("blog_name") String blog_name);

}
