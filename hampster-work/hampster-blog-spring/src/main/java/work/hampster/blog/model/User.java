package work.hampster.blog.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @TableName user
 */
@Data
@TableName("user")
public class User implements Serializable {
    @TableId
    private Integer uId;

    private String uToken;

    private String uName;

    private String uPass;

    private String uMail;

    private String uPhone;

    private static final long serialVersionUID = 1L;
}