package life.majiang.community.provider;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.region.Region;

import java.io.File;

/**
 * @author zt
 * @create 2021-04-06 23:27
 */
public class CosClientTest {
//    private static final String SECRET_ID = "AKID96oFgpQmJciYAhoA81e7KK3UtSnrryFO";
//    private static final String SECRET_KEY = "54EG1pOmxrRltlfJhuqZ4PVcU4VBgRCg";
//    private static final String BUCKETNAME = "community2-1305507496";
//    private static final String REGIONID = "ap-shanghai";
//    private static final String KEY="C:\\Users\\zt\\Desktop\\fish.jpg";
//    /**
//     * 初始化CosClient相关配置， appid、accessKey、secretKey、region
//     * @return
//     */
//    public static COSClient getCosClient() {
//        // 1 初始化用户身份信息(secretId, secretKey)
//        COSCredentials cred = new BasicCOSCredentials(SECRET_ID, SECRET_KEY);
//        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
//        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
//        ClientConfig clientConfig = new ClientConfig(new Region(REGIONID));
//        // 3 生成cos客户端
//        COSClient cosClient = new COSClient(cred, clientConfig);
//        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
//        //String bucketName = BUCKETNAME;
//        return cosClient;
//    }
//
//    /**
//     * 上传文件
//     * @return
//     * //绝对路径和相对路径都OK
//     */
//    public static String uploadFile() {
//        //自己更改本地上传文件
//        File localFile = new File("C:\\Users\\zt\\Desktop\\fish.jpg");
//        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKETNAME, KEY, localFile);
//
//        // 设置存储类型, 默认是标准(Standard), 低频(standard_ia),一般为标准的
//        putObjectRequest.setStorageClass(StorageClass.Standard);
//
//        COSClient cc = getCosClient();
//        try {
//            PutObjectResult putObjectResult = cc.putObject(putObjectRequest);
//            // putobjectResult会返回文件的etag
//            String etag = putObjectResult.getETag();
//            System.out.println(etag);
//        } catch (CosServiceException e) {
//            e.printStackTrace();
//        } catch (CosClientException e) {
//            e.printStackTrace();
//        }
//        // 关闭客户端
//        cc.shutdown();
//        return null;
//    }
//
//    public static void main(String[] args) {
//        getCosClient();
//        uploadFile();
//    }
}
