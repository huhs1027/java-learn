package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface KeyValue {

    String key();

    String value();

    //是否更新版本，用于批量更新
    boolean updateVersion() default false;

}
