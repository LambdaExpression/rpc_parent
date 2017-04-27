package org.tcat.parent.rpc.scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Arrays;
import java.util.Set;

/**
 * Created by Lin on 2017/4/27.
 */
public abstract class RpcServiceClassPathScanner extends ClassPathBeanDefinitionScanner {

    protected Logger log = LoggerFactory.getLogger(RpcServiceClassPathScanner.class);

    public RpcServiceClassPathScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        if (beanDefinitions.isEmpty()) {
            log.error("No RService was found in '{}' package. Please check your configuration.",
                    Arrays.toString(basePackages));
        } else {
            // scanner class path
            scanner(beanDefinitions);
        }
        return beanDefinitions;
    }


    /**
     * 需要子类去实现的扫描注册的细节
     */
    public abstract Set<BeanDefinitionHolder> scanner(Set<BeanDefinitionHolder> beanDefinitions);


}
