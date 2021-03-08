package life.majiang.community.dto;

import lombok.Data;

/**
 * @author zt
 * @create 2021-03-03 14:17
 */
@Data
public class GithubUser {
    private Long id;
    private String name;
    private String bio;
//    private String avatar_url;
    private String avatarUrl;

}
