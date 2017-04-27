package org.tcat.parent.rpc.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 远程服务注解
 * Created by Lin on 2017/4/27.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RService {

    /**
     * 远程服务的默认相对路径
     */
    String value();

}
