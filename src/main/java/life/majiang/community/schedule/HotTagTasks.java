package life.majiang.community.schedule;

import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.QuestionExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 在数据库中把问题全部拿出来，并且计算出问题的权重值，最终放到了hotTagCache里面
 * @author zt
 * @create 2021-04-03 20:32
 */
@Component
@Slf4j  //如果不想每次都写private  final Logger logger = LoggerFactory.getLogger(当前类名.class); 可以用注解@Slf4j;
public class HotTagTasks {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private HotTagCache hotTagCache;

    @Scheduled(fixedRate = 20000)
//    @Scheduled(cron = "0 0 1 * * *")    //"0 0 6,19 * * *" = 6:00 AM and 7:00 PM every day.
    public void hotTagSchedule() {
        int offset = 0;
        int limit = 20;
        log.info("hotTagSchedule start {}",new Date());
        List<Question> list = new ArrayList<>();

        Map<String, Integer> priorities = new HashMap<>();
        while (offset == 0 || list.size() == limit){    //等于第一页或者还有下一页
            list = questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,limit));
            for (Question question : list) {
                String[] tags = StringUtils.split(question.getTag(), ",");
                for (String tag : tags) {
                    Integer priority = priorities.get(tag);
                    if (priority != null){
                        priorities.put(tag,priority + 5 + question.getCommentCount());
                    }else {
                        priorities.put(tag,5 + question.getCommentCount());
                    }
                }
//                log.info("list question: {}" + question.getId());
            }
            offset += limit;
        }
//        hotTagCache.setTags(priorities);
//        hotTagCache.getTags().forEach(
        priorities.forEach(
                (k,v) -> {
                    System.out.print(k);
                    System.out.print(":");
                    System.out.print(v);
                    System.out.println();
                }
        );
        hotTagCache.updateTags(priorities);
        log.info("hotTagSchedule stop {}", new Date());
    }
}
