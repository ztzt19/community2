package life.majiang.community.dto;

import lombok.Data;

/**
 * @author zt
 * @create 2021-03-20 16:20
 */
@Data
public class QuestionQueryDTO {
    private String search;
    private String tag;
    private Integer page;
    private Integer size;
}
