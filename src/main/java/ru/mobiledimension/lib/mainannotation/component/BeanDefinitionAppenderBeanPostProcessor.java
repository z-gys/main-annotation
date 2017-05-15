package ru.mobiledimension.lib.mainannotation.component;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import ru.mobiledimension.lib.mainannotation.annotation.Main;

import java.util.Arrays;

/**
 * @author U.Goryntsev 12.05.2017
 */
public class BeanDefinitionAppenderBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private ConfigurableListableBeanFactory factory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Arrays.stream(factory.getBeanDefinitionNames()).parallel().forEach(name -> {
            if (hasMain(bean)) {
                BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
                if (beanDefinition.getBeanClassName() == null) {
                    beanDefinition.setBeanClassName(bean.getClass().getCanonicalName());
                }
            }
        });
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private boolean hasMain(Object bean) {
        return Arrays.stream(bean.getClass().getMethods()).anyMatch(method -> method.isAnnotationPresent(Main.class));
    }
}
