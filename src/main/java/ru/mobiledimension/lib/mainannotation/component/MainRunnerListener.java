package ru.mobiledimension.lib.mainannotation.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import ru.mobiledimension.lib.mainannotation.annotation.Main;
import ru.mobiledimension.lib.mainannotation.exception.MainMethodInvocationException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author U.Goryntsev 12.05.2017
 */
@Component
public class MainRunnerListener {

    @Autowired
    private  ConfigurableListableBeanFactory factory;

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            BeanDefinition beanDefinition = factory.getBeanDefinition(name);
            String beanClassName = beanDefinition.getBeanClassName();
            if (beanClassName == null)
                continue;

            Class<?> beanClass = ClassUtils.resolveClassName(beanClassName,null);
            Method[] methods = beanClass.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Main.class)) {
                    Object bean = context.getBean(name);
                    try {
                        method.invoke(bean);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new MainMethodInvocationException(e);
                    }
                }
            }
        }
    }
}
