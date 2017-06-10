package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Embeddable;

/**
 * Created by yazhouye on 6/10/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Embeddable
public class UserInfo {
    private String username;
    private String address;

    public UserInfo() {

    }

    public UserInfo(@JsonProperty("username") String username, @JsonProperty("address") String address) {
        this.username  = username;
        this.address = address;
    }

}
