package org.tcat.parent.rpc.scanner;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;
import java.util.Set;

/**
 * Created by Lin on 2017/4/21.
 */
public class RemoteServicePathScanner extends RpcServiceClassPathScanner {

    public RemoteServicePathScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    protected ApplicationContext applicationContext;

    @Override
    public Set<BeanDefinitionHolder> scanner(Set<BeanDefinitionHolder> beanDefinitions) {
        for (BeanDefinitionHolder holder : beanDefinitions) {
            log.info("scanering = {}", holder);
            GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
            AnnotationMetadata metadata = ((ScannedGenericBeanDefinition) definition).getMetadata();
            Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ANNOTATION_CLASS.getName());
        }
        return beanDefinitions;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.setResourceLoader(applicationContext);
    }

}
