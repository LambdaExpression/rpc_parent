package org.tcat.parent.rpc.scanner;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by Lin on 2017/4/27.
 */
@Aspect
@Component
public class RpcServiceAspect {

    @Pointcut("@annotation(org.tcat.parent.rpc.annotation.RpcService)")
    public void annotationPointCut() {
    }

    @Before("annotationPointCut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        Method method = sign.getMethod();
        System.out.print("接受方法：" + method.getName() + " 前置日志");
    }

}
