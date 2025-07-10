package com.atguigu.gulimall.ware.config;


import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonTimeConfig {

  private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

  // 然后使用这个常量
  @Bean
  public Jackson2ObjectMapperBuilderCustomizer customizer() {
    return builder -> {
      builder.simpleDateFormat("yyyy-MM-dd HH:mm:ss");
      JavaTimeModule javaTimeModule = new JavaTimeModule();
      javaTimeModule.addDeserializer(LocalDateTime.class,
          new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
      javaTimeModule.addSerializer(LocalDateTime.class,
          new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
      builder.timeZone(TimeZone.getDefault()); // 或指定特定时区
      builder.modules(javaTimeModule);
    };
  }
}
