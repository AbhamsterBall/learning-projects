package work.hampster.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import work.hampster.blog.model.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper extends BaseMapper<Blog> {
    @Select("SELECT bt.bt_name, GROUP_CONCAT(b.b_name) as blogNames FROM blog_type bt LEFT JOIN (select s1.* from blog as s1 where(select count(*) from blog as s2 where s1.b_type=s2.b_type and s1.b_time < s2.b_time) < 3 order by s1.b_type, s1.b_time) b ON bt.bt_id = b.b_type GROUP BY bt.bt_id having blogNames is not null")
    List<Map<String, Object>> selectBlogNames();

}
