package work.hampster.user.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import work.hampster.transfer.AESDTO;
import work.hampster.user.model.User;

@Data
public class UserDTO extends User {
    @SerializedName("uPass")
    @JsonProperty("uPass")
    private AESDTO uPassDTO;
}
