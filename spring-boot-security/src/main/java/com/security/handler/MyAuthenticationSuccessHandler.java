package com.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

/**
 * @author guofei
 * @date 2022/5/16 10:46 PM
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  private RequestCache requestCache = new HttpSessionRequestCache();
  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

   @Resource
   private ObjectMapper mapper;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    /*response.setContentType("application/json;charset=utf-8");
    response.getWriter().write(mapper.writeValueAsString(authentication));*/

    SavedRequest savedRequest = requestCache.getRequest(request, response);
    redirectStrategy.sendRedirect(request, response, savedRequest.getRedirectUrl());

    //redirectStrategy.sendRedirect(request, response, "/index");

  }

}
