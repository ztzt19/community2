package life.majiang.community.dto;

import lombok.Data;

/**
 * @author zt
 * @create 2021-03-03 13:20
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
