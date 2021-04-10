package life.majiang.community.controller;

import com.qcloud.cos.model.ObjectMetadata;
import com.tencentcloudapi.tia.v20180226.models.Log;
import life.majiang.community.dto.FileDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.exception.CustomizeException;
import life.majiang.community.provider.TencentCloudProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * @author zt
 * @create 2021-03-19 15:43
 */
@Controller
@Slf4j
@Component  //若要给静态变量赋值，可以使用set()方法，其中需要在类上加入@Component注解--自己加的
            //SpringBoot使用@Value给静态变量注入值 --> https://blog.csdn.net/mononoke111/article/details/81088472
public class FileController {
    private static String bucketName;

    @Autowired
    private TencentCloudProvider tencentCloudProvider;

    @Value("${tencentCloud.ufile.bucketName}")
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
//    private static final String bucketName = "community2-1305507496";

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) throws IOException {
        String key = "C:\\Users\\zt\\Desktop\\fish.jpg";
//        String key = "cos_dir/";
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");

        try {
            //file.getName():editormd-image-file  |  file.getOriginalFilename():c.jpg
            //key决定上传云后的名字
            String fileName = TencentCloudProvider.uploadFile(bucketName, key, file.getInputStream(), file.getOriginalFilename());
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(1);
            fileDTO.setUrl(fileName);
            return fileDTO;
        } catch (Exception e) {
            log.error("upload error",e);
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(0);
//        fileDTO.setUrl("C:\\Users\\zt\\Desktop\\fish.jpg");
            fileDTO.setMessage("上传失败");
            return fileDTO;
        }


    }
}
