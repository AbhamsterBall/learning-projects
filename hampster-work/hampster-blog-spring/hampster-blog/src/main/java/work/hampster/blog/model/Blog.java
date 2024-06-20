package work.hampster.blog.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName blogs
 */
@Data
@TableName("blog")
public class Blog implements Serializable {
    @TableId
    private Integer bId;

    private Integer bType;

    private String bName;

    private String bContext;

    private static final long serialVersionUID = 1L;
}