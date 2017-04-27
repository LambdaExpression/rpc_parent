package org.tcat.parent.rpc.scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.tcat.parent.rpc.annotation.RService;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static org.springframework.util.Assert.notNull;

/**
 * Created by Lin on 2017/4/27.
 */
public abstract class RpcServiceClassPathScanner extends ClassPathBeanDefinitionScanner {


    protected final Class<? extends Annotation> ANNOTATION_CLASS = RService.class;
    protected Logger log = LoggerFactory.getLogger(RpcServiceClassPathScanner.class);
    protected ApplicationContext applicationContext;

    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator() {
        @Override
        protected String buildDefaultBeanName(BeanDefinition definition) {
            AnnotationMetadata metadata = ((ScannedGenericBeanDefinition) definition).getMetadata();
            Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ANNOTATION_CLASS.getName());
            return annotationAttributes.get("value").toString();
        }
    };

    // (spring-boot)AnnotationConfigEmbeddedWebApplicationContext
    public RpcServiceClassPathScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
        this.setBeanNameGenerator(beanNameGenerator);
        this.setIncludeAnnotationConfig(true);
        this.registerDefaultFilters();
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.setResourceLoader(applicationContext);
    }

    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        if (beanDefinitions.isEmpty()) {
            log.error(" “{}” 包中没有找到服务。 请检查您的配置!!!", Arrays.toString(basePackages));
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

    /**
     * 根据BeanName获取Sping-Bean
     *
     * @param beanNameRef
     * @return
     */
    protected Object getBeanRef(String beanNameRef) {
        notNull(beanNameRef, "需要Bean Ref .");
        try {
            return applicationContext.getBean(beanNameRef);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return (beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent());
    }

    @Override
    protected boolean checkCandidate(String beanName, BeanDefinition beanDefinition) throws IllegalStateException {
        if (super.checkCandidate(beanName, beanDefinition)) {
            return true;
        } else {
            log.warn("跳过 服务 '" + beanName + "'，因为和 '" + beanDefinition.getBeanClassName()
                    + "' 服务接口 " + ". Bean 定义同名!");
            return false;
        }
    }

    @Override
    protected void registerDefaultFilters() {
        addIncludeFilter(new AnnotationTypeFilter(ANNOTATION_CLASS));
    }


}
