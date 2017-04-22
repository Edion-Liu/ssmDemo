package com.eliteams.quick4j.web.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheWrite {
	String key() default "";
	int validTime() default 10;
	String [] fields() default "";
}



