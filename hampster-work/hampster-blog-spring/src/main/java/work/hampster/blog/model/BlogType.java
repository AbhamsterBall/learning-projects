package work.hampster.blog.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @TableName blog_type
 */
@Data
@TableName("blog_type")
public class BlogType implements Serializable {
    @TableId
    private Integer btId;

    private String btName;

    private static final long serialVersionUID = 1L;
}