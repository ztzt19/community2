package life.majiang.community.model;

import lombok.Data;

/**
 * @author zt
 * @create 2021-03-04 11:01
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;


}
