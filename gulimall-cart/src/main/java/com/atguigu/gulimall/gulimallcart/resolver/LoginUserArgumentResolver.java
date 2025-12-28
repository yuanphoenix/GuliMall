package com.atguigu.gulimall.gulimallcart.resolver;

import com.atguigu.gulimall.gulimallcart.annotation.LoginUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import constant.LoginConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import to.MemberEntityVo;

@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    // 参数类型必须是 MemberEntityVo 且标注了 @LoginUser
    return parameter.getParameterType().equals(MemberEntityVo.class)
        && parameter.hasParameterAnnotation(
        LoginUser.class);
  }

  @Override
  public @Nullable Object resolveArgument(MethodParameter parameter,
      @Nullable ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
      @Nullable WebDataBinderFactory binderFactory) throws Exception {
    HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
    HttpSession session = request.getSession();
    Object attribute = session.getAttribute(LoginConstant.LOGIN.getValue());
    return objectMapper.convertValue(attribute, MemberEntityVo.class);
  }
}
