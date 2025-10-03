package com.atguigu.gulimall.product;

import com.aliyuncs.exceptions.ClientException;
import java.io.FileNotFoundException;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class GulimallProductApplicationTests {

  @Autowired
  private StringRedisTemplate redisTemplate;

  @Autowired
  private RedissonClient redissonClient;

  @Test
  void testRedis() {
    ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
    stringStringValueOperations.set("hello", "world" + UUID.randomUUID());
    System.out.println(stringStringValueOperations.get("hello"));
  }


  @Test
  void testRedission() throws InterruptedException {
    RLock mylock = redissonClient.getLock("mylock");
    mylock.lock();
    Thread.sleep(50000);
    mylock.unlock();
    System.out.println("解锁啦");
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
