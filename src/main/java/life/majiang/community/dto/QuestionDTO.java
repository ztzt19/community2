package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

/**
 * @author zt
 * @create 2021-03-06 13:09
 */
@Data
public class QuestionDTO {
//    private Integer id;
    private Long id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
//    private Integer creator;
    private Long creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;
}
