package com.security.handler;

import com.security.controller.ValidateController;
import com.security.mode.ImageCode;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author guofei
 * @date 2022/5/16 11:06 PM
 */
public class ValidateCodeFilter extends OncePerRequestFilter {

  @Resource
  private AuthenticationFailureHandler authenticationFailureHandler;

  private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
      FilterChain filterChain) throws ServletException, IOException {

    if (StringUtils.pathEquals("/login", httpServletRequest.getRequestURI())) {
      if (StringUtils.pathEquals(httpServletRequest.getMethod(), "post")) {
        try {
          validateCode(new ServletWebRequest(httpServletRequest));
        } catch (ValidateCodeException e) {
          authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,
              httpServletResponse, e);
          return;
        }
      }
    }
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
  private void validateCode(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
    ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, ValidateController.SESSION_KEY);
    String codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "imageCode");

    if (StringUtils.isEmpty(codeInRequest)) {
      throw new ValidateCodeException("验证码不能为空！");
    }
    if (codeInSession == null) {
      throw new ValidateCodeException("验证码不存在！");
    }
    if (codeInSession.isExpire()) {
      sessionStrategy.removeAttribute(servletWebRequest, ValidateController.SESSION_KEY);
      throw new ValidateCodeException("验证码已过期！");
    }
    if (!StringUtils.pathEquals(codeInSession.getCode(), codeInRequest)) {
      throw new ValidateCodeException("验证码不正确！");
    }
    sessionStrategy.removeAttribute(servletWebRequest, ValidateController.SESSION_KEY);
  }

}
