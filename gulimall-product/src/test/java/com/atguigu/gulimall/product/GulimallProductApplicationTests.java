package com.atguigu.gulimall.product;

import com.aliyuncs.exceptions.ClientException;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;

@SpringBootTest
class GulimallProductApplicationTests {

  @Test
  void testUtils() {
    System.out.println(ObjectUtils.isEmpty(10));

  }

  @Test
  void contextLoads() throws ClientException, FileNotFoundException {
//        String bucketName = "gulimall--tifa";
//        String endpoint = "oss-cn-beijing.aliyuncs.com";
//        String region = "cn-beijing";
//        // 从环境变量中获取访问凭证。运行本代码示例之前，请先配置环境变量
//        EnvironmentVariableCredentialsProvider credentialsProvider =
//                CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
//
//        // 创建 OSSClient 实例
//        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
//        // 显式声明使用 V4 签名算法
//        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
//        OSS ossClient = OSSClientBuilder.create()
//                .endpoint(endpoint)
//                .credentialsProvider(credentialsProvider)
//                .region(region)
//                .build();
//
//
//        String objectName ="我的文件";
//        ossClient.putObject(bucketName, objectName, new FileInputStream( "E:\\wsl\\shortcut.ico"));
//        System.out.println("2. 文件 " + objectName + " 上传成功。");
//        System.out.println("4. 列出 Bucket 中的文件：");
//        ObjectListing objectListing = ossClient.listObjects(bucketName);
//        for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
//            System.out.println(" - " + objectSummary.getKey() + " (大小 = " + objectSummary.getSize() + ")");
//        }
  }

}
