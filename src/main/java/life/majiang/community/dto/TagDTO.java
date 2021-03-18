package life.majiang.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @author zt
 * @create 2021-03-18 12:08
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
