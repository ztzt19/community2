package life.majiang.community.model;

import lombok.Data;

/**
 * @author zt
 * @create 2021-03-04 21:06
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;

}
