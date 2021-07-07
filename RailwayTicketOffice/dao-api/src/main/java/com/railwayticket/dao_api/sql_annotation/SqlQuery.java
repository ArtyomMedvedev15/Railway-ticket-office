package com.railwayticket.dao_api.sql_annotation;

import org.springframework.stereotype.Component;
import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface SqlQuery {
    String sqlfilename() default "";
}
