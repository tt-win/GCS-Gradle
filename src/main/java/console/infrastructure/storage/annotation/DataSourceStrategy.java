package console.infrastructure.storage.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

import console.infrastructure.storage.enums.DataSourceType;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceStrategy {
    String key();
    DataSourceType[] order() default {DataSourceType.REDIS, DataSourceType.ORACLE};
    int timeout() default 3;
    TimeUnit timeUnit() default TimeUnit.MINUTES;
}
