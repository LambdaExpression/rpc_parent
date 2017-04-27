package org.tcat.parent.rpc.scanner;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.util.Set;

/**
 * Created by Lin on 2017/4/21.
 */
public class RemoteServicePathScanner extends RpcServiceClassPathScanner {

    public RemoteServicePathScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public Set<BeanDefinitionHolder> scanner(Set<BeanDefinitionHolder> beanDefinitions) {
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitions) {
            log.info("scanering = {}", beanDefinitionHolder);

        }
        return beanDefinitions;
    }

}
