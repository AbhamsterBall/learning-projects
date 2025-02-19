package work.hampster.transfer;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import work.hampster.transfer.AESDTO;
import work.hampster.model.User;
import work.hampster.util.ObjectHandler;

import java.lang.reflect.Field;

@Data
public class UserDTO extends User {
    @SerializedName("uPass")
    @JsonProperty("uPass")
    private AESDTO uPassDTO;

    @SerializedName("uFingerPrint")
    @JsonProperty("uFingerPrint")
    private AESDTO uFingerPrint;

    public void setUserInfo(User user) throws IllegalAccessException {
        ObjectHandler.convert(user, this);
    }
}
