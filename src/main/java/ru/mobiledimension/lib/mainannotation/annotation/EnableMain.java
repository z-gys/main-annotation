package ru.mobiledimension.lib.mainannotation.annotation;

import org.springframework.context.annotation.Import;
import ru.mobiledimension.lib.mainannotation.config.Config;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Zmey 16.05.2017.
 */
@Retention(RUNTIME)
@Import(Config.class)
public @interface EnableMain {
}
