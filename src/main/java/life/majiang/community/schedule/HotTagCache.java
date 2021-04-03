package life.majiang.community.schedule;

import com.sun.org.apache.bcel.internal.generic.RET;
import life.majiang.community.dto.HotTagDTO;
import lombok.Data;
import org.springframework.core.metrics.StartupStep;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author zt
 * @create 2021-04-03 21:08
 */
@Component  //本身就是一个单例
@Data
public class HotTagCache {
    private Map<String, Integer> tags = new HashMap<>();
    private List<String> hots = new ArrayList<>();

    public void updateTags(Map<String, Integer> tags) {
        int max = 3;
        //构建基于java的优先队列
        PriorityQueue<HotTagDTO> priorityQueue = new PriorityQueue<>(max);

        tags.forEach((name, priority) -> {
            HotTagDTO hotTagDTO = new HotTagDTO();
            hotTagDTO.setName(name);
            hotTagDTO.setPriority(priority);
            if (priorityQueue.size() < 3) {  //当<3的时候，直接往里面放
                priorityQueue.add(hotTagDTO);
            } else { //拿到最小的元素出来比较,如果我我比他大的时候，再往里面放
                HotTagDTO minHot = priorityQueue.peek();
                if (hotTagDTO.compareTo(minHot) > 0) {
                    //把最小的一个拿出来
                    priorityQueue.poll();
                    priorityQueue.add(hotTagDTO);
                }
            }
        });

        List<String> sortedTags = new ArrayList<>();
        HotTagDTO poll = priorityQueue.poll();
        while (poll != null) {  //第一次拿到poll的时候判断！=空，直接追加到第一个元素
            sortedTags.add(0, poll.getName());
            //然后去做赋值,然后判断！=null的时候再次赋值
            poll = priorityQueue.poll();
        }
        hots = sortedTags;
        //hots打印出来：[java, spring, Java]
        System.out.println(hots);
    }
}
