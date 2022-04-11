package com.aop.config;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author guofei
 * @date 2022/4/11 3:28 PM
 */
@Aspect
@Component
@Slf4j
public class LogAspectHandle {

  /**
   * 1.@Pointcut:定义一个切面，即上面所描述的关注的某件事入口。
   * 2.@Before:在做某件事之前做的事。
   * 3.@After:在做某件事之后做的事。
   * 4.@AfterReturning:在做某件事之后，对其返回值做增强处理。
   * 5.@AfterThrowing:在做某件事抛出异常时，处理。
   */

  /**
   * 定义一个切面 拦截com.aop.controller包和子包下的所有方法
   * Pointcut 注解指定一个切面，定义需要拦截的东西，
   * 这里介绍两个常用的表达式:一个是使用 execution() ，另一个是使用 annotation()
   * 以 execution(* com.aop.controller..*.*(..)) 为例
   * 第一个 * 号的位置:表示返回值类型， * 表示所有类型 包名:表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，
   * com.aop.controller 包、子包下所有类的方法 第二个 * 号的位置:表示类名， * 表示所有类
   * *(..) :这个星号表示方法名，* 表示所有的方法，后面括弧里面表示方法的参数，两个句点 表示任何参数
   */
  @Pointcut("execution(* com.aop.controller..*.*(..))")
  public void pointCut(){

  }

  /**
   * annotation() 方式是针对某个注解来定义切面，比如我们对具有@GetMapping注解的方法做切面
   */
  @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
  public void annotationCut() {

  }

  @Before("pointCut()")
  public void doBefore(JoinPoint joinPoint){
    log.info("----doBefore start----");

    // 获取签名
    Signature signature = joinPoint.getSignature();
    // 获取切入的包名
    String declaringTypeName = signature.getDeclaringTypeName();
    // 获取即将执行的方法名
    String funcName = signature.getName();
    log.info("即将执行方法为: {}，属于{}包", funcName, declaringTypeName);
    // 也可以用来记录一些信息，比如获取请求的url和ip
    ServletRequestAttributes attributes = (ServletRequestAttributes)
        RequestContextHolder.getRequestAttributes();
    // 获取请求url
    HttpServletRequest request = attributes.getRequest();
    String url = request.getRequestURL().toString();
    // 获取请求ip
    String ip = request.getRemoteAddr();
    log.info("用户请求的url为:{}，ip地址为:{}", url, ip);

    log.info("----doBefore end----");

  }

  @After("pointCut()")
  public void doAfter(JoinPoint joinPoint){
    log.info("----doAfter start----");

    Signature signature = joinPoint.getSignature();
    String method = signature.getName();

    log.info("----doAfter end,method为：{}----",method);
  }

  /**
   * @AfterReturning 注解和 @After 有些类似，区别在于:
   * @AfterReturning 注解可以用来捕获切入方法执行完之后的返回值，对返回值进行业务逻辑上的增强处理
   * @param joinPoint
   * @param result
   */
  @AfterReturning(pointcut = "pointCut()", returning = "result")
  public void doAfterReturning(JoinPoint joinPoint, Object result){
    Signature signature = joinPoint.getSignature();
    String classMethod = signature.getName();
    // 实际项目中可以根据业务做具体的返回值增强
    log.info("方法{}执行完毕，返回参数为:{}", classMethod, result);
  }

  /**
   * 在上面定义的切面方法执行抛异常时，执行该方法
   * @param joinPoint jointPoint
   * @param ex ex
   */
  @AfterThrowing(pointcut = "pointCut()", throwing = "ex")
  public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
    Signature signature = joinPoint.getSignature();
    String method = signature.getName();
    // 处理异常的逻辑
    log.info("执行方法{}出错，异常为:{}", method, ex);
  }

}
