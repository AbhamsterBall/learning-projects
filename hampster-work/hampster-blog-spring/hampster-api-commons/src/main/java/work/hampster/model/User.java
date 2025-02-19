package work.hampster.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.gson.annotations.SerializedName;
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
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
// use annotation to all attribute to address database non-normative column name problem
public class User implements Serializable {
    @TableId
    @SerializedName("uId")
    @JsonProperty("uId")
    private Integer uId;

    @SerializedName("uToken")
    @JsonProperty("uToken")
    private String uToken;

    @SerializedName("uName")
    @JsonProperty("uName")
    private String uName;

    @SerializedName("uPass")
    @JsonProperty("uPass")
    private String uPass;

    @SerializedName("uMail")
    @JsonProperty("uMail")
    private String uMail;

    @SerializedName("uPhone")
    @JsonProperty("uPhone")
    private String uPhone;

    @SerializedName("uProfile")
    @JsonProperty("uProfile")
    private String uProfile;

    private static final long serialVersionUID = 1L;

}