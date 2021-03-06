package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

/**
 * @author zt
 * @create 2021-03-18 16:45
 */
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;

//    private User notifier;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerid;
    //评论还是回复
    private String typeName;
    //1 --> 回复了问题; 2 --> 回复评论
    private Integer type;
}
