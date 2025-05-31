package com.tifa.gulimallthirdparty.controller;

import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.sts20150401.models.AssumeRoleResponse;
import com.aliyun.sts20150401.models.AssumeRoleResponseBody;
import com.aliyun.tea.TeaException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.R;


@RestController
@RequestMapping("/oss")
public class WebController {

  //OSS基础信息 替换为实际的 bucket 名称、 region-id、host。
  String bucket = "gulimall--tifa";
  String region = "cn-beijing";
  //  String host = "http://examplebucket.oss-cn-hangzhou.aliyuncs.com";
  // 设置上传回调URL（即回调服务器地址），必须为公网地址。用于处理应用服务器与OSS之间的通信，OSS会在文件上传完成后，把文件上传信息通过此回调URL发送给应用服务器。
  //限定上传到OSS的文件前缀。

  String format = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

  String upload_dir = format + "/";

  //指定过期时间，单位为秒。
  Long expire_time = 3600L;

  /**
   * 通过指定有效的时长（秒）生成过期时间。
   *
   * @param seconds 有效时长（秒）。
   * @return ISO8601 时间字符串，如："2014-12-01T12:00:00.000Z"。
   */
  public static String generateExpiration(long seconds) {
    // 获取当前时间戳（以秒为单位）
    long now = Instant.now().getEpochSecond();
    // 计算过期时间的时间戳
    long expirationTime = now + seconds;
    // 将时间戳转换为Instant对象，并格式化为ISO8601格式
    Instant instant = Instant.ofEpochSecond(expirationTime);
    // 定义时区为UTC
    ZoneId zone = ZoneOffset.UTC;
    // 将 Instant 转换为 ZonedDateTime
    ZonedDateTime zonedDateTime = instant.atZone(zone);
    // 定义日期时间格式，例如2023-12-03T13:00:00.000Z
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    // 格式化日期时间
    String formattedDate = zonedDateTime.format(formatter);
    // 输出结果
    return formattedDate;
  }

  //初始化STS Client
  public static com.aliyun.sts20150401.Client createStsClient() throws Exception {
    // 工程代码泄露可能会导致 AccessKey 泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考。
    // 建议使用更安全的 STS 方式。
    com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
        // 必填，请确保代码运行环境设置了环境变量 OSS_ACCESS_KEY_ID。
        .setAccessKeyId(System.getenv("OSS_ACCESS_KEY_ID"))
        // 必填，请确保代码运行环境设置了环境变量 OSS_ACCESS_KEY_SECRET。
        .setAccessKeySecret(System.getenv("OSS_ACCESS_KEY_SECRET"));
    // Endpoint 请参考 https://api.aliyun.com/product/Sts
    config.endpoint = "sts.cn-hangzhou.aliyuncs.com";
    return new com.aliyun.sts20150401.Client(config);
  }

  /**
   * 获取STS临时凭证
   *
   * @return AssumeRoleResponseBodyCredentials 对象
   */
  public static AssumeRoleResponseBody.AssumeRoleResponseBodyCredentials getCredential()
      throws Exception {
    com.aliyun.sts20150401.Client client = WebController.createStsClient();
    com.aliyun.sts20150401.models.AssumeRoleRequest assumeRoleRequest = new com.aliyun.sts20150401.models.AssumeRoleRequest()
        // 必填，请确保代码运行环境设置了环境变量 OSS_STS_ROLE_ARN
        .setRoleArn(System.getenv("OSS_STS_ROLE_ARN"))
        .setRoleSessionName("yourRoleSessionName");// 自定义会话名称
    com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
    try {
      // 复制代码运行请自行打印 API 的返回值
      AssumeRoleResponse response = client.assumeRoleWithOptions(assumeRoleRequest, runtime);
      // credentials里包含了后续要用到的AccessKeyId、AccessKeySecret和SecurityToken。
      return response.body.credentials;
    } catch (TeaException error) {
      // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
      // 错误 message
      System.out.println(error.getMessage());
      // 诊断地址
      System.out.println(error.getData().get("Recommend"));
      com.aliyun.teautil.Common.assertAsString(error.message);
    } catch (Exception _error) {
      TeaException error = new TeaException(_error.getMessage(), _error);
      // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
      // 错误 message
      System.out.println(error.getMessage());
      // 诊断地址
      System.out.println(error.getData().get("Recommend"));
      com.aliyun.teautil.Common.assertAsString(error.message);
    }
    // 返回一个默认的错误响应对象，避免返回null
    AssumeRoleResponseBody.AssumeRoleResponseBodyCredentials defaultCredentials = new AssumeRoleResponseBody.AssumeRoleResponseBodyCredentials();
    defaultCredentials.accessKeyId = "ERROR_ACCESS_KEY_ID";
    defaultCredentials.accessKeySecret = "ERROR_ACCESS_KEY_SECRET";
    defaultCredentials.securityToken = "ERROR_SECURITY_TOKEN";
    return defaultCredentials;
  }

  public static byte[] hmacsha256(byte[] key, String data) {
    try {
      // 初始化HMAC密钥规格，指定算法为HMAC-SHA256并使用提供的密钥。
      SecretKeySpec secretKeySpec = new SecretKeySpec(key, "HmacSHA256");

      // 获取Mac实例，并通过getInstance方法指定使用HMAC-SHA256算法。
      Mac mac = Mac.getInstance("HmacSHA256");
      // 使用密钥初始化Mac对象。
      mac.init(secretKeySpec);

      // 执行HMAC计算，通过doFinal方法接收需要计算的数据并返回计算结果的数组。
      byte[] hmacBytes = mac.doFinal(data.getBytes());

      return hmacBytes;
    } catch (Exception e) {
      throw new RuntimeException("Failed to calculate HMAC-SHA256", e);
    }
  }

  @GetMapping("/get_post_signature_for_oss_upload")
  public R getPostSignatureForOssUpload() throws Exception {

    AssumeRoleResponseBody.AssumeRoleResponseBodyCredentials sts_data = getCredential();

    String accesskeyid = sts_data.accessKeyId;
    String accesskeysecret = sts_data.accessKeySecret;
    String securitytoken = sts_data.securityToken;

    //获取x-oss-credential里的date，当前日期，格式为yyyyMMdd
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    String date = today.format(formatter);

    //获取x-oss-date
    ZonedDateTime now = ZonedDateTime.now().withZoneSameInstant(java.time.ZoneOffset.UTC);
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
    String x_oss_date = now.format(formatter2);

    // 步骤1：创建policy。
    String x_oss_credential = accesskeyid + "/" + date + "/" + region + "/oss/aliyun_v4_request";

    ObjectMapper mapper = new ObjectMapper();

    Map<String, Object> policy = new HashMap<>();
    policy.put("expiration", generateExpiration(expire_time));

    List<Object> conditions = new ArrayList<>();

    Map<String, String> bucketCondition = new HashMap<>();
    bucketCondition.put("bucket", bucket);
    conditions.add(bucketCondition);

    Map<String, String> securityTokenCondition = new HashMap<>();
    securityTokenCondition.put("x-oss-security-token", securitytoken);
    conditions.add(securityTokenCondition);

    Map<String, String> signatureVersionCondition = new HashMap<>();
    signatureVersionCondition.put("x-oss-signature-version", "OSS4-HMAC-SHA256");
    conditions.add(signatureVersionCondition);

    Map<String, String> credentialCondition = new HashMap<>();
    credentialCondition.put("x-oss-credential", x_oss_credential); // 替换为实际的 access key id
    conditions.add(credentialCondition);

    Map<String, String> dateCondition = new HashMap<>();
    dateCondition.put("x-oss-date", x_oss_date);
    conditions.add(dateCondition);

    conditions.add(Arrays.asList("content-length-range", 1, 10240000));
    conditions.add(Arrays.asList("eq", "$success_action_status", "200"));
    conditions.add(Arrays.asList("starts-with", "$key", upload_dir));

    policy.put("conditions", conditions);

    String jsonPolicy = mapper.writeValueAsString(policy);

    // 步骤2：构造待签名字符串（StringToSign）。
    String stringToSign = new String(Base64.encodeBase64(jsonPolicy.getBytes()));
    // System.out.println("stringToSign: " + stringToSign);

    // 步骤3：计算SigningKey。
    byte[] dateKey = hmacsha256(("aliyun_v4" + accesskeysecret).getBytes(), date);
    byte[] dateRegionKey = hmacsha256(dateKey, region);
    byte[] dateRegionServiceKey = hmacsha256(dateRegionKey, "oss");
    byte[] signingKey = hmacsha256(dateRegionServiceKey, "aliyun_v4_request");
    // System.out.println("signingKey: " + BinaryUtil.toBase64String(signingKey));

    // 步骤4：计算Signature。
    byte[] result = hmacsha256(signingKey, stringToSign);
    String signature = BinaryUtil.toHex(result);
    // System.out.println("signature:" + signature);

    Map<String, Object> response = new HashMap<>();
    // 将数据添加到 map 中
    response.put("version", "OSS4-HMAC-SHA256");
    response.put("policy", stringToSign);
    response.put("x_oss_credential", x_oss_credential);
    response.put("x-oss-signature-version", "OSS4-HMAC-SHA256");
    response.put("x_oss_date", x_oss_date);
    response.put("dir", upload_dir);
    response.put("signature", signature);
    response.put("security_token", securitytoken);
    response.put("key", UUID.randomUUID().toString().replace("-", ""));
//    response.put("host", host);
    // 返回带有状态码 200 (OK) 的 ResponseEntity，返回给Web端，进行PostObject操作
    return R.ok(response);
  }
}