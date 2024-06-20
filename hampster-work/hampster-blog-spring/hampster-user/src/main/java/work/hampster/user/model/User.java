package work.hampster.user.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;

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