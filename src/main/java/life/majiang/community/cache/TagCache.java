package life.majiang.community.cache;

import com.sun.org.apache.bcel.internal.generic.NEW;
import life.majiang.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zt
 * @create 2021-03-18 12:06
 */
public class TagCache {
    public static List<TagDTO> get(){
        List<TagDTO> tagDTOS = new ArrayList<>();

        TagDTO frontEnd = new TagDTO();
        frontEnd.setCategoryName("前端");
        frontEnd.setTags(Arrays.asList("javascript","前端","vue.js","css","html","html5","node.js","react.js","jquery","css3","es6",
                "typescript","chrome","npm","bootstrap","sass","less","chrome-devtools","angular","firefox","coffeescript","safari",
                "postman","postcss","fiddler","yarn","webkit","firebug","edge"));
        tagDTOS.add(frontEnd);

        TagDTO backEnd = new TagDTO();
        backEnd.setCategoryName("后端");
        backEnd.setTags(Arrays.asList("php","java","node.js","python","c++","golang","c","spring","springboot"));
        tagDTOS.add(backEnd);

        TagDTO mobileEnd = new TagDTO();
        mobileEnd.setCategoryName("移动端");
        mobileEnd.setTags(Arrays.asList("java","android","ios","objective-c","小程序","react-native"));
        tagDTOS.add(mobileEnd);

        TagDTO database = new TagDTO();
        database.setCategoryName("数据库");
        database.setTags(Arrays.asList("mysql","redis","sql","mongodb","json","elasticsearch"));
        tagDTOS.add(database);

        return tagDTOS;
    }

    public static String filterInvalid(String tags){
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();

        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;

    }
}
