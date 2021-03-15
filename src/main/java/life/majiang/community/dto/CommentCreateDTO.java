package life.majiang.community.dto;

import lombok.Data;

/**
 * @author zt
 * @create 2021-03-12 22:55
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
