package com.zzy.study;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 访问控制注解 该注解保留到运行时，针对方法使用
 * 
 * @version 0.1
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Access {
	/**
	 * 
	 * @Title: value
	 * @Description: (权限值的名称)
	 * @return
	 */
	String value() default "";
	/**
	 * 
	 * @Title: isPass
	 * @Description: false:没有权限访问||true:要有权限才有访问
	 * @return
	 */
	boolean isPass() default false;
	/**
	 * 
	 * @Title: hasAccess
	 * @Description: true:有权限值
	 * @return
	 */
	boolean hasAccess() default true;
	/**
	 * 
	 * @Title: isJson
	 * @Description: 返回的格式
	 * @return
	 */
	boolean isJson() default false;
}