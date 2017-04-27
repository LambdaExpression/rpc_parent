package org.tcat.parent.rpc.scanner;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

/**
 * Created by Lin on 2017/4/27.
 */
public abstract class RpcServiceClassPathScanner extends ClassPathBeanDefinitionScanner {

    public RpcServiceClassPathScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }


    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

        return beanDefinitions;
    }


}
