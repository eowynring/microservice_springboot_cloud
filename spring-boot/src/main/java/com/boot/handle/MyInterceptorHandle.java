package com.boot.handle;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义拦截器
 * @author guofei
 * @date 2022/5/7 2:09 PM
 */
@Slf4j
public class MyInterceptorHandle implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    HandlerMethod handlerMethod = (HandlerMethod) handler;
    Method method = handlerMethod.getMethod();
    String name = method.getName();
    log.info("====拦截到了方法:{}，在该方法执行之前执行====", name);
    // 判断用户有没有登陆，一般登陆之后的用户都有一个对应的token
    String token = request.getParameter("token");
    if (null == token || "".equals(token)) {
      log.info("用户未登录，没有权限执行......请登录");
      return false;
    }
    // 返回true才会继续执行，返回false则取消当前请求
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    //HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    log.info("执行完方法之后进执行(Controller方法调用之后)，但是此时还没进行视图渲染");
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    //HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    log.info("整个请求都处理完咯，DispatcherServlet也渲染了对应的视图咯，此时我可 以做一些清理的工作了");
  }
}
