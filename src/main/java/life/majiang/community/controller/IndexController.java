package life.majiang.community.controller;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.schedule.HotTagCache;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zt
 * @create 2021-03-02 18:04
 */
@Controller
public class IndexController {
//    @GetMapping("/hello")
//    public String hello(@RequestParam(name = "name") String name, Model model){
//        model.addAttribute("name",name);
//        return "hello";
//    }

//    @Autowired
//    public UserMapper userMapper;

    @Autowired
    public QuestionService questionService;

    @Autowired
    private HotTagCache hotTagCache;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size,
                        @RequestParam(name = "search", required = false) String search,
                        @RequestParam(name = "tag", required = false) String tag) {
//        PaginationDTO pagination = questionService.list(search,page,size);
        PaginationDTO pagination = questionService.list(search,tag,page,size);
        List<String> tags = hotTagCache.getHots();
//        for (QuestionDTO questionDTO: questionList){
//            questionDTO.setDescription("444");
//        }
        model.addAttribute("pagination",pagination);
        model.addAttribute("search",search);
        model.addAttribute("tags",tags);
        //当选择热门话题html5时，点击第二页不至于全部显示，所以增加tag.在index.html中<nav aria-label="Page navigation">中的,tag=${tag}
        model.addAttribute("tag",tag);
        return "index";
    }
}
