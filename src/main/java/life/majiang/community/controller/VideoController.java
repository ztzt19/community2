package life.majiang.community.controller;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zt
 * @create 2021-03-02 18:04
 */
@Controller
public class VideoController {
    @GetMapping("/videos")
    public String index(){
        return "videos";
    }
    @GetMapping("/video")
    public String video(@RequestParam(name = "aid") String aid,
                        @RequestParam(name = "page", defaultValue = "1") String page,
                        Model model){
        model.addAttribute("aid",aid);
        model.addAttribute("page",page);
        return "video";
    }
}
