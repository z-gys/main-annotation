package ru.mobiledimension.lib.mainannotation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mobiledimension.lib.mainannotation.component.BeanDefinitionAppenderBeanPostProcessor;
import ru.mobiledimension.lib.mainannotation.component.MainRunnerListener;

/**
 * @author U.Goryntsev 12.05.2017
 */
@Configuration
public class Config {

    @Bean
    public MainRunnerListener mainRunnerListener() {
        return new MainRunnerListener();
    }

    @Bean
    public BeanDefinitionAppenderBeanPostProcessor beanDefinitionAppenderBeanPostProcessor() {
        return new BeanDefinitionAppenderBeanPostProcessor();
    }
}
