package life.majiang.community.dto;

import lombok.Data;

/**
 * @author zt
 * @create 2021-04-03 21:40
 */
@Data
public class HotTagDTO implements Comparable{
    private String name;
    private Integer priority;

    @Override
    public int compareTo(Object o) {
        //希望插入的最新元素>=当前元素的时候，直接替换位置
        return this.getPriority() - ((HotTagDTO)o).getPriority();
    }
}
