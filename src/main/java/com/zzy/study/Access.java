package com.zzy.study;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ���ʿ���ע�� ��ע�Ᵽ��������ʱ����Է���ʹ��
 * 
 * @version 0.1
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Access {
	/**
	 * 
	 * @Title: value
	 * @Description: (Ȩ��ֵ������)
	 * @return
	 */
	String value() default "";
	/**
	 * 
	 * @Title: isPass
	 * @Description: false:û��Ȩ�޷���||true:Ҫ��Ȩ�޲��з���
	 * @return
	 */
	boolean isPass() default false;
	/**
	 * 
	 * @Title: hasAccess
	 * @Description: true:��Ȩ��ֵ
	 * @return
	 */
	boolean hasAccess() default true;
	/**
	 * 
	 * @Title: isJson
	 * @Description: ���صĸ�ʽ
	 * @return
	 */
	boolean isJson() default false;
}