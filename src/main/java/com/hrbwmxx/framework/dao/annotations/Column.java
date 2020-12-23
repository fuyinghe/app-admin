package com.hrbwmxx.framework.dao.annotations;

import java.lang.annotation.ElementType; 
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;    

@Target(ElementType.FIELD)    
@Retention(RetentionPolicy.RUNTIME)  
public @interface Column {
	String mappedName() default "";
	boolean insertable() default true;
	boolean updateable() default true;
	boolean updateKey() default false;
	String propertyName() default "";
}
