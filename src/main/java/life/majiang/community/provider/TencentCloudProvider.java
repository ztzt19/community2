package life.majiang.community.provider;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.Upload;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.cvm.v20170312.CvmClient;
import com.tencentcloudapi.cvm.v20170312.models.DescribeInstancesRequest;
import com.tencentcloudapi.cvm.v20170312.models.DescribeInstancesResponse;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.exception.CustomizeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * @author zt
 * @create 2021-04-06 19:30
 */
@Service
@Component  //若要给静态变量赋值，可以使用set()方法，其中需要在类上加入@Component注解
public class TencentCloudProvider {
    private static String secretId;
    private static String secretKey;
    private static String regionId;
    private static Long expires;

    @Value("${tencentCloud.ufile.secretId}")
    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    @Value("${tencentCloud.ufile.secretKey}")
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Value("${tencentCloud.ufile.regionId}")
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    @Value("${tencentCloud.ufile.expires}")
    public void setExpires(Long expires) {
        this.expires = expires;
    }




    //    private static final String secretId = "AKID96oFgpQmJciYAhoA81e7KK3UtSnrryFO";
    //    private static final String secretKey = "54EG1pOmxrRltlfJhuqZ4PVcU4VBgRCg";
    //    private static final String regionId = "ap-shanghai";

    /**
     * 初始化CosClient相关配置， appid、accessKey、secretKey、region
     *
     * @return
     */
//    public static COSClient getCosClient() {
//        // 1 初始化用户身份信息(secretId, secretKey)
//        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
//        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
//        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
//        com.qcloud.cos.ClientConfig clientConfig = new ClientConfig(new Region(regionId));
//        // 3 生成cos客户端
//        COSClient cosClient = new COSClient(cred, clientConfig);
//        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
//        //String bucketName = BUCKETNAME;
//        return cosClient;
//    }

    /**
     * 上传文件     -->  示例4：提供更多细粒度的控制https://cloud.tencent.com/document/product/436/35215#.E4.B8.8A.E4.BC.A0.E5.AF.B9.E8.B1.A1.EF.BC.88.E5.88.9B.E5.BB.BA.E6.96.87.E4.BB.B6.E5.A4.B9)
     *
     * @return //绝对路径和相对路径都OK
     */
//    public static String uploadFile() {
//        //自己更改本地上传文件
//        File localFile = new File(KEY);
//        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, KEY, localFile);
//
//        // 设置存储类型, 默认是标准(Standard), 低频(standard_ia),一般为标准的
//        putObjectRequest.setStorageClass(StorageClass.Standard);
//
//        COSClient cosClient = getCosClient();
//        try {
//            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
//            // putobjectResult会返回文件的etag
//            String etag = putObjectResult.getETag();
//            System.out.println(etag);
//        } catch (CosServiceException e) {
//            e.printStackTrace();
//        } catch (CosClientException e) {
//            e.printStackTrace();
//        }
//        // 关闭客户端
//        cosClient.shutdown();
//        return null;
//    }

    public static COSClient getCosClient() {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        com.qcloud.cos.ClientConfig clientConfig = new ClientConfig(new Region(regionId));
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        //String bucketName = BUCKETNAME;
        return cosClient;
    }

    /**
     * 上传文件     -->  示例4：提供更多细粒度的控制https://cloud.tencent.com/document/product/436/35215#.E4.B8.8A.E4.BC.A0.E5.AF.B9.E8.B1.A1.EF.BC.88.E5.88.9B.E5.BB.BA.E6.96.87.E4.BB.B6.E5.A4.B9)
     *
     * @return //绝对路径和相对路径都OK
     */
    public static String uploadFile(String bucketName, String key, InputStream fileStream, String generatedFileName) {

        //自己更改本地上传文件
        File localFile = new File(key);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);

        // 设置存储类型, 默认是标准(Standard), 低频(standard_ia),一般为标准的
        putObjectRequest.setStorageClass(StorageClass.Standard);

        COSClient cosClient = getCosClient();
        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentLength(localFile.length());
        try {
            metadata.setContentLength(fileStream.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        metadata.setContentDisposition("inline;filename=" + generatedFileName);
        try {
            PutObjectResult putObjectResult = cosClient.putObject(bucketName,key,fileStream,metadata);
//            PutObjectResult putObjectResult = cosClient.putObject(bucketName,generatedFileName,fileStream,metadata);
            // putobjectResult会返回文件的etag
            String etag = putObjectResult.getETag();
            System.out.println(etag);

            if (putObjectResult != null){
                GeneratePresignedUrlRequest req =
                        new GeneratePresignedUrlRequest(bucketName, key, HttpMethodName.GET);
                // 设置签名过期时间(可选), 若未进行设置, 则默认使用 ClientConfig 中的签名过期时间(1小时)
                // 这里设置签名在半个小时后过期
                Date expirationDate = new Date(System.currentTimeMillis() + expires);
                req.setExpiration(expirationDate);
                URL url = cosClient.generatePresignedUrl(req);
                System.out.println(url.toString());
                cosClient.shutdown();
                return url.toString();
            }else {
                throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
            }

        } catch (CosServiceException e) {
            e.printStackTrace();
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        } catch (CosClientException e) {
            e.printStackTrace();
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        }
        // 关闭客户端
//        cosClient.shutdown();
//        return generatedFileName;
    }


//    public static void main(String[] args) {
//        TencentCloudProvider tencentCloudProvider = new TencentCloudProvider();
//        tencentCloudProvider.getCosClient();
//        tencentCloudProvider.uploadFile();
//        getCosClient();
//        uploadFile();
//    }
}
