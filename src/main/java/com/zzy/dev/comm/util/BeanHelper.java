
package com.zzy.dev.comm.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;


/** Java Bean �����ִ࣬�� Java Bean �������ʡ����ز��� */
public class BeanHelper
{
	/** ���������ͼ��� */
	public static final Set<Class<?>> SMIPLE_CLASS_SET	= new HashSet<Class<?>>(18);
	/** �������Ͱ�װ�༯�� */
	public static final Set<Class<?>> WRAPPER_CLASS_SET	= new HashSet<Class<?>>(8);

	private static final String STRING_DELIMITERS = " ,;|\t\n\r\f";
	
	static
	{
		SMIPLE_CLASS_SET.add(int.class);
		SMIPLE_CLASS_SET.add(long.class);
		SMIPLE_CLASS_SET.add(float.class);
		SMIPLE_CLASS_SET.add(double.class);
		SMIPLE_CLASS_SET.add(byte.class);
		SMIPLE_CLASS_SET.add(char.class);
		SMIPLE_CLASS_SET.add(short.class);
		SMIPLE_CLASS_SET.add(boolean.class);
		SMIPLE_CLASS_SET.add(Integer.class);
		SMIPLE_CLASS_SET.add(Long.class);
		SMIPLE_CLASS_SET.add(Float.class);
		SMIPLE_CLASS_SET.add(Double.class);
		SMIPLE_CLASS_SET.add(Byte.class);
		SMIPLE_CLASS_SET.add(Character.class);
		SMIPLE_CLASS_SET.add(Short.class);
		SMIPLE_CLASS_SET.add(Boolean.class);
		SMIPLE_CLASS_SET.add(String.class);
		SMIPLE_CLASS_SET.add(Date.class);
		
		WRAPPER_CLASS_SET.add(Integer.class);
		WRAPPER_CLASS_SET.add(Long.class);
		WRAPPER_CLASS_SET.add(Float.class);
		WRAPPER_CLASS_SET.add(Double.class);
		WRAPPER_CLASS_SET.add(Byte.class);
		WRAPPER_CLASS_SET.add(Character.class);
		WRAPPER_CLASS_SET.add(Short.class);
		WRAPPER_CLASS_SET.add(Boolean.class);
	}

	/** ����Ƿ�Ϊ�ǳ��󹫹�ʵ������ */
	public static final boolean isInstanceField(Field field)
	{
		int flag = field.getModifiers();
		return (!Modifier.isStatic(flag));
	}
	
	/** ����Ƿ�Ϊ�ǳ��󹫹�ʵ������ */
	public static final boolean isInstanceNotFinalField(Field field)
	{
		int flag = field.getModifiers();
		return (!Modifier.isStatic(flag) && !Modifier.isFinal(flag));
	}
	
	/** ����Ƿ�Ϊ�ǳ��󹫹�ʵ������ */
	public static final boolean isPublicInstanceMethod(Method method)
	{
		int flag = method.getModifiers();
		return (!Modifier.isStatic(flag) && !Modifier.isAbstract(flag) && Modifier.isPublic(flag));
	}
	
	/** ����Ƿ�Ϊ�ǽӿڷǳ����� */
	public static final boolean isPublicClass(Class<?> clazz)
	{
		int flag = clazz.getModifiers();
		return (!Modifier.isInterface(flag) && Modifier.isPublic(flag));
	}

	/** ����Ƿ�Ϊ�ǽӿڷǳ��󹫹��� */
	public static final boolean isPublicNotAbstractClass(Class<?> clazz)
	{
		int flag = clazz.getModifiers();
		return (!Modifier.isInterface(flag) && !Modifier.isAbstract(flag) && Modifier.isPublic(flag));
	}

	/** ��� clazz �Ƿ�Ϊ���������� */
	public final static boolean isSimpleType(Class<?> clazz)
	{
		return SMIPLE_CLASS_SET.contains(clazz);
	}

	/** ��� clazz �Ƿ�Ϊ�������Ͱ�װ�� */
	public final static boolean isWrapperType(Class<?> clazz)
	{
		return WRAPPER_CLASS_SET.contains(clazz);
	}

	/** ����װ��ͻ��������Ƿ�ƥ�� */
	public final static boolean isWrapperAndPrimitiveMatch(Class<?> wrapperClazz, Class<?> primitiveClass)
	{
		if(!primitiveClass.isPrimitive())	return false;
		if(!isWrapperType(wrapperClazz))	return false;
		
		try
		{
			Field f = wrapperClazz.getDeclaredField("TYPE");
			return f.get(null) == primitiveClass;
		}
		catch(Exception e)
		{
			
		}
		
		return false;
	}
	
	/** ���Դ�����Ƿ����Ŀ������ */
	public static final boolean isCompatibleType(Class<?> srcClazz,Class<?> destClazz)
	{
		return	(
					destClazz.isAssignableFrom(srcClazz)			||
					isWrapperAndPrimitiveMatch(destClazz, srcClazz)	||
					isWrapperAndPrimitiveMatch(srcClazz, destClazz)
				);
	}
	
	/** ���Դ�����Ԫ�������Ƿ����Ŀ�������Ԫ������ */
	public static final boolean isCompatibleArray(Class<?> srcClazz, Class<?> destClazz)
	{
		if(srcClazz.isArray() && destClazz.isArray())
		{
			Class<?> srcComponentType = srcClazz.getComponentType();
			Class<?> destComponentType = destClazz.getComponentType();
		
			return	isCompatibleType(srcComponentType, destComponentType);
		}
		
		return false;
	}
	
	/** ����ָ�����͵� Java Bean��������������Ի��Ա����
	 * 
	 *  @param clazz		: Bean ����<br>
	 *  @param properties	: ���Ի��Ա������ / ֵӳ��<br>
	 *  					  ��������Ϊ {@link String} ���ͣ������Ի��Ա��������һ��<br>
	 *  					  ���Ի��Ա����ֵ����Ϊ���� 3 �����ͣ�<br>
	 *  					  &nbsp; &nbsp; 1) ���Ի��Ա������ʵ�����ͣ�ֱ�Ӷ����Ի��Ա������ֵ<br>
	 *  					  &nbsp; &nbsp; 2) {@link String} ���ͣ���ִ���Զ�����ת���ٶ����Ի��Ա������ֵ<br>
	 *  					  &nbsp; &nbsp; 3) {@link String}[] ���ͣ���ִ���Զ�����ת���ٶ����Ի��Ա������ֵ<br> 
	 *  @return				: ���ɵ� Bean ʵ��
	 */
	public static final <B, T> B createBean(Class<B> clazz, Map<String, T> properties)
	{
		return createBean(clazz, properties, null);
	}
	
	/** ����ָ�����͵� Java Bean��������������Ի��Ա����
	 * 
	 *  @param clazz		: Bean ����<br>
	 *  @param valueMap		: ���Ի��Ա������ / ֵӳ��<br>
	 *  					  ��������Ϊ {@link String} ���ͣ������Ի��Ա�������ƿ���һֱҲ���ܲ�һ��<br>
	 *  					  ���Ի��Ա����ֵ����Ϊ���� 3 �����ͣ�<br>
	 *  					  &nbsp; &nbsp; 1) ���Ե�ʵ�����ͣ�ֱ�Ӷ����Ի��Ա������ֵ<br>
	 *  					  &nbsp; &nbsp; 2) {@link String} ���ͣ���ִ���Զ�����ת���ٶ����Ի��Ա������ֵ<br>
	 *  					  &nbsp; &nbsp; 3) {@link String}[] ���ͣ���ִ���Զ�����ת���ٶ����Ի��Ա������ֵ<br>
	 *  @param keyMap		: properties.key / Bean ���Ի��Ա������ӳ�䣬�� properties �� key �����Ի��Ա����������Ӧʱ��
	 *  					  �� keyMap �����ǹ�������
	 *  @return				  ���ɵ� Bean ʵ��  
	 */
	public static final <B, T> B createBean(Class<B> clazz, Map<String, T> valueMap, Map<String, String> keyMap)
	{
		B bean = null;
		
		try
		{
			bean = clazz.newInstance();
			setPropertiesOrFieldValues(bean, valueMap, keyMap);
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		
		return bean;
	}
	
	/** ����ָ�����͵� Java Bean���������������
	 * 
	 *  @param clazz		: Bean ����<br>
	 *  @param properties	: ������ / ֵӳ��<br>
	 *  					  ��������Ϊ {@link String} ���ͣ�����������һ��<br>
	 *  					  ����ֵ����Ϊ���� 3 �����ͣ�<br>
	 *  					  &nbsp; &nbsp; 1) ���Ե�ʵ�����ͣ�ֱ�Ӷ����Ը�ֵ<br>
	 *  					  &nbsp; &nbsp; 2) {@link String} ���ͣ���ִ���Զ�����ת���ٶ����Ը�ֵ<br>
	 *  					  &nbsp; &nbsp; 3) {@link String}[] ���ͣ���ִ���Զ�����ת���ٶ����Ը�ֵ<br> 
	 *  @return				: ���ɵ� Bean ʵ��
	 */
	public static final <B, T> B createBeanByProperties(Class<B> clazz, Map<String, T> properties)
	{
		return createBeanByProperties(clazz, properties, null);
	}
	
	/** ����ָ�����͵� Java Bean���������������
	 * 
	 *  @param clazz		: Bean ����<br>
	 *  @param properties	: ������ / ֵӳ��<br>
	 *  					  ��������Ϊ {@link String} ���ͣ����������ƿ���һֱҲ���ܲ�һ��<br>
	 *  					  ����ֵ����Ϊ���� 3 �����ͣ�<br>
	 *  					  &nbsp; &nbsp; 1) ���Ե�ʵ�����ͣ�ֱ�Ӷ����Ը�ֵ<br>
	 *  					  &nbsp; &nbsp; 2) {@link String} ���ͣ���ִ���Զ�����ת���ٶ����Ը�ֵ<br>
	 *  					  &nbsp; &nbsp; 3) {@link String}[] ���ͣ���ִ���Զ�����ת���ٶ����Ը�ֵ<br>
	 *  @param keyMap		: properties.key / Bean ������ӳ�䣬�� properties �� key ������������Ӧʱ��
	 *  					  �� keyMap �����ǹ�������
	 *  @return				  ���ɵ� Bean ʵ��  
	 */
	public static final <B, T> B createBeanByProperties(Class<B> clazz, Map<String, T> properties, Map<String, String> keyMap)
	{
		B bean = null;
		
		try
		{
			bean = clazz.newInstance();
			setProperties(bean, properties, keyMap);
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		
		return bean;
	}
	
	/** ����ָ�����͵� Java Bean���������������
	 * 
	 *  @param clazz		: Bean ����<br>
	 *  @param values		: ��Ա������ / ֵӳ��<br>
	 *  					  ��������Ϊ {@link String} ���ͣ����Ա�������ƿ���һֱҲ���ܲ�һ��<br>
	 *  					  ��Ա����ֵ����Ϊ���� 3 �����ͣ�<br>
	 *  					  &nbsp; &nbsp; 1) ��Ա������ʵ�����ͣ�ֱ�ӶԳ�Ա������ֵ<br>
	 *  					  &nbsp; &nbsp; 2) {@link String} ���ͣ���ִ���Զ�����ת���ٶԳ�Ա����ֵ<br>
	 *  					  &nbsp; &nbsp; 3) {@link String}[] ���ͣ���ִ���Զ�����ת���ٶԳ�Ա����ֵ<br>
	 *  @return				  ���ɵ� Bean ʵ��  
	 */
	public static final <B, T> B createBeanByFieldValues(Class<B> clazz, Map<String, T> values)
	{
		return createBeanByFieldValues(clazz, values, null);
	}
	
	/** ����ָ�����͵� Java Bean���������������
	 * 
	 *  @param clazz		: Bean ����<br>
	 *  @param values		: ��Ա������ / ֵӳ��<br>
	 *  					  ��������Ϊ {@link String} ���ͣ����Ա�������ƿ���һֱҲ���ܲ�һ��<br>
	 *  					  ��Ա����ֵ����Ϊ���� 3 �����ͣ�<br>
	 *  					  &nbsp; &nbsp; 1) ��Ա������ʵ�����ͣ�ֱ�ӶԳ�Ա������ֵ<br>
	 *  					  &nbsp; &nbsp; 2) {@link String} ���ͣ���ִ���Զ�����ת���ٶԳ�Ա����ֵ<br>
	 *  					  &nbsp; &nbsp; 3) {@link String}[] ���ͣ���ִ���Զ�����ת���ٶԳ�Ա����ֵ<br>
	 *  @param keyMap		: values.key / Bean ��Ա������ӳ�䣬�� values �� key ���Ա����������Ӧʱ��
	 *  					  �� keyMap �����ǹ�������
	 *  @return				  ���ɵ� Bean ʵ��  
	 */
	public static final <B, T> B createBeanByFieldValues(Class<B> clazz, Map<String, T> values, Map<String, String> keyMap)
	{
		B bean = null;
		
		try
		{
			bean = clazz.newInstance();
			setFieldValues(bean, values, keyMap);
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		
		return bean;
	}
	
	/** ���� Java Bean ������
	 * 
	 *  @param bean			: Bean ʵ��<br>
	 *  @param properties	: ������ / ֵӳ��<br>
	 *  					  ��������Ϊ {@link String} ���ͣ�����������һ��<br>
	 *  					  ����ֵ����Ϊ���� 3 �����ͣ�<br>
	 *  					  &nbsp; &nbsp; 1) ���Ե�ʵ�����ͣ�ֱ�Ӷ����Ը�ֵ<br>
	 *  					  &nbsp; &nbsp; 2) {@link String} ���ͣ���ִ���Զ�����ת���ٶ����Ը�ֵ<br>
	 *  					  &nbsp; &nbsp; 3) {@link String}[] ���ͣ���ִ���Զ�����ת���ٶ����Ը�ֵ<br> 
	 */
	public static final <T> void setProperties(Object bean, Map<String, T> properties)
	{
		setProperties(bean, properties, null);
	}
	
	/** ���� Java Bean ������
	 * 
	 *  @param bean			: Bean ʵ��<br>
	 *  @param properties	: ������ / ֵӳ��<br>
	 *  					  ��������Ϊ {@link String} ���ͣ����������ƿ���һֱҲ���ܲ�һ��<br>
	 *  					  ����ֵ����Ϊ���� 3 �����ͣ�<br>
	 *  					  &nbsp; &nbsp; 1) ���Ե�ʵ�����ͣ�ֱ�Ӷ����Ը�ֵ<br>
	 *  					  &nbsp; &nbsp; 2) {@link String} ���ͣ���ִ���Զ�����ת���ٶ����Ը�ֵ<br>
	 *  					  &nbsp; &nbsp; 3) {@link String}[] ���ͣ���ִ���Զ�����ת���ٶ����Ը�ֵ<br>
	 *  @param keyMap		: properties.key / Bean ������ӳ�䣬�� properties �� key ������������Ӧʱ��
	 *  					  �� keyMap �����ǹ�������  
	 */
	public static final <T> void setProperties(Object bean, Map<String, T> properties, Map<String, String> keyMap)
	{
		if(properties == null || properties.isEmpty())
			return;
		
		Map<String, PropertyDescriptor> pps = getPropDescMap(bean.getClass());
		Map<String, T> params				= translateKVMap(properties, keyMap);
		Set<Map.Entry<String, T>> set		= params.entrySet();
		
		for(Map.Entry<String, T> e : set)
		{
			String key	= e.getKey();
			T value		= e.getValue();
		
			PropertyDescriptor pd = pps.get(key);
			setProperty(bean, pd, value);
		}
	}

	/** ���� Java Bean ������
	 * 
	 *  @param bean			: Bean ʵ��<br>
	 *  @param values		: ��Ա������ / ֵӳ��<br>
	 *  					  ��������Ϊ {@link String} ���ͣ����Ա�������ƿ���һֱҲ���ܲ�һ��<br>
	 *  					  ��Ա����ֵ����Ϊ���� 3 �����ͣ�<br>
	 *  					  &nbsp; &nbsp; 1) ��Ա������ʵ�����ͣ�ֱ�ӶԳ�Ա������ֵ<br>
	 *  					  &nbsp; &nbsp; 2) {@link String} ���ͣ���ִ���Զ�����ת���ٳ�Ա������ֵ<br>
	 *  					  &nbsp; &nbsp; 3) {@link String}[] ���ͣ���ִ���Զ�����ת���ٳ�Ա������ֵ<br>
	 */
	public static final <T> void setFieldValues(Object bean, Map<String, T> values)
	{
		setFieldValues(bean, values, null);
	}
	
	/** ���� Java Bean ������
	 * 
	 *  @param bean			: Bean ʵ��<br>
	 *  @param values		: ��Ա������ / ֵӳ��<br>
	 *  					  ��������Ϊ {@link String} ���ͣ����Ա�������ƿ���һֱҲ���ܲ�һ��<br>
	 *  					  ��Ա����ֵ����Ϊ���� 3 �����ͣ�<br>
	 *  					  &nbsp; &nbsp; 1) ��Ա������ʵ�����ͣ�ֱ�ӶԳ�Ա������ֵ<br>
	 *  					  &nbsp; &nbsp; 2) {@link String} ���ͣ���ִ���Զ�����ת���ٳ�Ա������ֵ<br>
	 *  					  &nbsp; &nbsp; 3) {@link String}[] ���ͣ���ִ���Զ�����ת���ٳ�Ա������ֵ<br>
	 *  @param keyMap		: properties.key / Bean ��Ա������ӳ�䣬�� properties �� key ���Ա����������Ӧʱ��
	 *  					  �� keyMap �����ǹ�������  
	 */
	public static final <T> void setFieldValues(Object bean, Map<String, T> values, Map<String, String> keyMap)
	{
		if(values == null || values.isEmpty())
			return;
		
		Map<String, Field> fms			= getInstanceFieldMap(bean.getClass());
		Map<String, T> params			= translateKVMap(values, keyMap);
		Set<Map.Entry<String, T>> set	= params.entrySet();
		
		for(Map.Entry<String, T> e : set)
		{
			String key	= e.getKey();
			T value		= e.getValue();
		
			Field f = fms.get(key);
			setFieldValue(bean, f, value);
		}
	}
	
	/** ���� Java Bean ������
	 * 
	 *  @param bean			: Bean ʵ��<br>
	 *  @param valueMap		: ���Ի��Ա������ / ֵӳ��<br>
	 *  					  ��������Ϊ {@link String} ���ͣ������Ի��Ա�������ƿ���һֱҲ���ܲ�һ��<br>
	 *  					  ���Ի��Ա����ֵ����Ϊ���� 3 �����ͣ�<br>
	 *  					  &nbsp; &nbsp; 1) ���Ի��Ա������ʵ�����ͣ�ֱ�Ӷ����Ի��Ա������ֵ<br>
	 *  					  &nbsp; &nbsp; 2) {@link String} ���ͣ���ִ���Զ�����ת���ٶ������Ա������ֵ<br>
	 *  					  &nbsp; &nbsp; 3) {@link String}[] ���ͣ���ִ���Զ�����ת���ٶ��������Ա����ֵ<br>
	 */
	public static final <T> void setPropertiesOrFieldValues(Object bean, Map<String, T> valueMap)
	{
		setPropertiesOrFieldValues(bean, valueMap, null);
	}
	
	/** ���� Java Bean ������
	 * 
	 *  @param bean			: Bean ʵ��<br>
	 *  @param valueMap		: ���Ի��Ա������ / ֵӳ��<br>
	 *  					  ��������Ϊ {@link String} ���ͣ������Ի��Ա�������ƿ���һֱҲ���ܲ�һ��<br>
	 *  					  ���Ի��Ա����ֵ����Ϊ���� 3 �����ͣ�<br>
	 *  					  &nbsp; &nbsp; 1) ���Ի��Ա������ʵ�����ͣ�ֱ�Ӷ����Ի��Ա������ֵ<br>
	 *  					  &nbsp; &nbsp; 2) {@link String} ���ͣ���ִ���Զ�����ת���ٶ������Ա������ֵ<br>
	 *  					  &nbsp; &nbsp; 3) {@link String}[] ���ͣ���ִ���Զ�����ת���ٶ��������Ա����ֵ<br>
	 *  @param keyMap		: properties.key / Bean ���Ի��Ա������ӳ�䣬�� properties �� key �����Ի��Ա����������Ӧʱ��
	 *  					  �� keyMap �����ǹ�������  
	 */
	public static final <T> void setPropertiesOrFieldValues(Object bean, Map<String, T> valueMap, Map<String, String> keyMap)
	{
		if(valueMap == null || valueMap.isEmpty())
			return;
		
		Map<String, PropertyDescriptor> pps = getPropDescMap(bean.getClass());
		Map<String, T> params				= translateKVMap(valueMap, keyMap);
		Map<String, T> failParams			= new HashMap<String, T>();
		Set<Map.Entry<String, T>> set		= params.entrySet();
		
		for(Map.Entry<String, T> e : set)
		{
			String key	= e.getKey();
			T value		= e.getValue();
		
			PropertyDescriptor pd = pps.get(key);
			if(!setProperty(bean, pd, value))
				failParams.put(key, value);
		}
		
		if(!failParams.isEmpty())
			setFieldValues(bean, failParams);
	}

	private static final <T> Map<String, T> translateKVMap(Map<String, T> valueMap, Map<String, String> keyMap)
	{
		if(keyMap == null || keyMap.isEmpty())
			return valueMap;
		
		Map<String, T> resultMap		= new HashMap<String, T>();
		Set<Map.Entry<String, T>> set	= valueMap.entrySet();
		
		for(Map.Entry<String, T> e : set)
		{
			String key	= e.getKey();
			String name	= key;
		
			if(keyMap.containsKey(key))
				name = keyMap.get(key);

			resultMap.put(name, e.getValue());
		}
		
		return resultMap;
	}

	/** ���� Java Bean ������
	 * 
	 *  @param bean		: Bean ʵ��<br>
	 *  @param pd		: ����������<br>
	 *  @param value	: ����ֵ������Ϊ���� 3 �����ͣ�<br>
	 *  					  &nbsp; &nbsp; 1) ���Ե�ʵ�����ͣ�ֱ�Ӷ����Ը�ֵ<br>
	 *  					  &nbsp; &nbsp; 2) {@link String} ���ͣ���ִ���Զ�����ת���ٶ����Ը�ֵ<br>
	 *  					  &nbsp; &nbsp; 3) {@link String}[] ���ͣ���ִ���Զ�����ת���ٶ����Ը�ֵ<br>  
	 */
	public static final <T> boolean setProperty(Object bean, PropertyDescriptor pd, T value)
	{
		if(pd != null)
		{
			Method method = getPropertyWriteMethod(pd);
			
			if(method != null)
			{
				Type genericType = null;
				Class<?> clazz	 = pd.getPropertyType();
			
				if	(
						Collection.class.isAssignableFrom(clazz)	&&
						value != null								&& 
						!clazz.isAssignableFrom(value.getClass())
					)
				{
					Type[] types = method.getGenericParameterTypes();
					
					if(types.length > 0)
						genericType = types[0];
				}
				
				Result<Boolean, Object> result = parseValue(value, clazz, genericType);
	
				if(result.getFlag())
				{
					invokeMethod(bean, method, result.getValue());
					return true;
				}
			}
		}

		return false;
	}
	
	/** ���� Java Bean ������
	 * 
	 *  @param bean		: Bean ʵ��<br>
	 *  @param field	: ��Ա���� {@link Field} ����<br>
	 *  @param value	: ��Ա����ֵ������Ϊ���� 3 �����ͣ�<br>
	 *  					  &nbsp; &nbsp; 1) ��Ա������ʵ�����ͣ�ֱ�ӶԳ�Ա������ֵ<br>
	 *  					  &nbsp; &nbsp; 2) {@link String} ���ͣ���ִ���Զ�����ת���ٶԳ�Ա����ֵ<br>
	 *  					  &nbsp; &nbsp; 3) {@link String}[] ���ͣ���ִ���Զ�����ת���ٳ�Ա������ֵ<br>  
	 */
	public static final <T> boolean setFieldValue(Object bean, Field field, T value)
	{
		if(field != null && isInstanceNotFinalField(field))
		{
			Class<?> clazz	 = field.getType();
			Type genericType = field.getGenericType();
			
			Result<Boolean, Object> result = parseValue(value, clazz, genericType);
			
			if(result.getFlag())
			{
				invokeSetFieldValue(bean, field, result.getValue());
				return true;
			}
		}
		
		return false;
	}

	/** �Ѷ���ת��ΪĿ������
	 * 
	 *  @param value		: ��ת���Ĳ���������Ϊ���� 3 �����ͣ�<br>
	 *  					  &nbsp; &nbsp; 1) ������������Ŀ�����ͼ��ݣ�����ת��<br>
	 *  					  &nbsp; &nbsp; 2) {@link String} ���ͣ�����ִ���Զ�����ת��<br>
	 *  					  &nbsp; &nbsp; 3) {@link String}[] ���ͣ�����ִ���Զ�����ת��<br>  
	 *  @param clazz		: Ŀ������<br>
	 *  @param genericType	: Ŀ�����͵�Ԫ������ (ֻ��Ŀ�������� {@link List} �� {@link Set} �ȼ�������ʱ����Ҫ)<br>
	 */
	@SuppressWarnings("unchecked")
	public static final <T> Result<Boolean, Object> parseValue(T value, Class<?> clazz, Type genericType)
	{
		Result<Boolean, Object> result = Result.initialBoolean();
		
		if(value == null)
		{
			if(clazz.isPrimitive())
				result.set(Boolean.TRUE, GeneralHelper.str2Object(clazz, (String)value));
			else
				result.set(Boolean.TRUE, value);
		}
		else if(clazz.isAssignableFrom(value.getClass()))
			result.set(Boolean.TRUE, value);
		else if(isWrapperAndPrimitiveMatch(value.getClass(), clazz))
			result.set(Boolean.TRUE, value);
		else if(isCompatibleArray(value.getClass(), clazz))
			getArrayValue(value, clazz.getComponentType(), result);
		else if(Collection.class.isAssignableFrom(clazz))
			getCollectionValue(value, (Class<? extends Collection<?>>)clazz, genericType, result);
		else
			getSimpleValue(value, clazz, result);
		
		return result;
	}
	
	private static final <T> void getArrayValue( T value, Class<?> clazz, Result<Boolean, Object> result)
	{
		int length = Array.getLength(value);
		Object array = Array.newInstance(clazz, length);
		
		for(int i = 0;i < length; i++)
			Array.set(array, i, Array.get(value, i));
		
		result.set(Boolean.TRUE, array);
	}

	private static final <T> void getCollectionValue(T value, Class<? extends Collection<?>> colClazz, Type genericType, Result<Boolean, Object> result)
	{
		if(genericType instanceof ParameterizedType)
		{
			Class<?> paramClazz = (Class<?>)(((ParameterizedType)genericType).getActualTypeArguments()[0]);
			getCollectionValue(value, colClazz, paramClazz, result);
		}
	}

	private static final <T> void getCollectionValue(T value, Class<? extends Collection<?>> clazz, Class<?> paramClazz, Result<Boolean, Object> result)
	{
		Class<?> valueType		= value.getClass();
		Class<?> valueComType	= valueType.getComponentType();
		
		if	(
				isSimpleType(paramClazz)					&&
				(	
					(valueType.equals(String.class))		|| 
					(valueType.isArray() && valueComType.equals(String.class))
				)

			)
		{
			Collection<?> col = parseCollectionParameter(clazz, paramClazz, value);
			result.set(Boolean.TRUE, col);
		}
	}
	
	private static final <T> void getSimpleValue(T value, Class<?> clazz, Result<Boolean, Object> result)
	{
		Class<?> valueType		= value.getClass();
		Class<?> valueComType	= valueType.getComponentType();
		Class<?> clazzComType	= clazz.getComponentType();

		if	(
				(	
					(valueType.equals(String.class))		|| 
					(valueType.isArray() && valueComType.equals(String.class))
				)
				&&
				(	
					(isSimpleType(clazz))		|| 
					(clazz.isArray() && isSimpleType(clazzComType))
				)
			)
		{
			Object param = parseParameter(clazz, value);
			result.set(Boolean.TRUE, param);
		}
	}
	
	private static final <T> Collection<?> parseCollectionParameter(Class<? extends Collection<?>> clazz, Class<?> paramClazz, T obj)
	{
		Collection<Object> col = getRealCollectionClass(clazz);
		
		if(col != null)
		{
			Class<?> valueType	= obj.getClass();
			String[] value		= null;
			
			if(valueType.isArray())
				value	= (String[])obj;
			else
			{
				String str	= (String)obj;
				StringTokenizer st = new StringTokenizer(str, STRING_DELIMITERS);
				value	= new String[st.countTokens()];

				for(int i = 0; st.hasMoreTokens(); i++)
					value[i] = st.nextToken();
			}

			for(int i = 0; i < value.length; i++)
			{
				String v = value[i];
				Object p = GeneralHelper.str2Object(paramClazz, v);
				col.add(p);
			}
		}
		
		return col;
	}

	@SuppressWarnings("unchecked")
	private static final Collection<Object> getRealCollectionClass(Class<? extends Collection<?>> clazz)
	{
		Collection<?> col	= null;
		Class<?> realClazz	= null;
		
		if(isPublicNotAbstractClass(clazz))
			realClazz = clazz;
		else if(Set.class.isAssignableFrom(clazz))
			realClazz = HashSet.class;
		else if(Collection.class.isAssignableFrom(clazz))
			realClazz = ArrayList.class;

		if(realClazz != null)
		{
			try
			{
				col = (Collection<?>)realClazz.newInstance();
			}
			catch(Exception e)
			{

			}
		}
		
		return (Collection<Object>)col;
	}

	/** ͨ��������Ƶ��÷�����ʧ�����׳��쳣 */
	@SuppressWarnings("unchecked")
	public static final <T> T invokeMethod(Object bean, Method method, Object ... param)
	{
		try
		{
			method.setAccessible(true);
			return (T)method.invoke(bean, param);
		}
		catch(Exception e)
		{
			if(e instanceof InvocationTargetException)
			{
				Exception cause = (Exception)e.getCause();
				if(cause != null) e = cause;
			}
			
			throw new RuntimeException(e);
		}
	}
	
	/** ͨ��������Ƶ��÷�����ִ�н���� {@link Result} ��ʶ�����׳��쳣 */
	public static final <T> Result<Boolean, T> tryInvokeMethod(Object bean, Method method, Object ... param)
	{
		Result<Boolean, T> result = Result.initialBoolean();
		
		try
		{
			T value = invokeMethod(bean, method, param);
			result.set(Boolean.TRUE, value);
		}
		catch (Exception e)
		{
		}
		
		return result;
	}
	
	/** ͨ��������ƻ�ȡ��Ա����ֵ��ʧ�����׳��쳣 */
	@SuppressWarnings("unchecked")
	public static final <T> T invokeGetFieldValue(Object bean, Field field)
	{	
		try
		{
			field.setAccessible(true);
			return (T)field.get(bean);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	/** ͨ��������ƻ�ȡ��Ա����ֵ��ִ�н���� {@link Result} ��ʶ�����׳��쳣 */
	public static final <T> Result<Boolean, T> tryInvokeGetFieldValue(Object bean, Field field)
	{
		Result<Boolean, T> result = Result.initialBoolean();
		
		try
		{
			T value = invokeGetFieldValue(bean, field);
			result.set(Boolean.TRUE, value);
		}
		catch (Exception e)
		{
		}
		
		return result;
	}

	/** ͨ������������ó�Ա����ֵ��ʧ�����׳��쳣 */
	public static final void invokeSetFieldValue(Object bean, Field field, Object value)
	{
		try
		{
			field.setAccessible(true);
			field.set(bean, value);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}		
	}

	/** ͨ������������ó�Ա����ֵ��ʧ���򷵻� false�����׳��쳣 */
	public static final boolean tryInvokeSetFieldValue(Object bean, Field field, Object value)
	{
		boolean isOK = true;
		
		try
		{
			invokeSetFieldValue(bean, field, value);
		}
		catch (Exception e)
		{
			isOK = false;
		}
		
		return isOK;
	}

	private static final <T> Object parseParameter(Class<?> clazz, T obj)
	{
		Object param		= null;
		Class<?> valueType	= obj.getClass();
		
		if(clazz.isArray())
		{
			String[] value = null;
			
			if(valueType.isArray())
				value	= (String[])obj;
			else
			{
				String str	= (String)obj;
				StringTokenizer st = new StringTokenizer(str, STRING_DELIMITERS);
				value	= new String[st.countTokens()];

				for(int i = 0; st.hasMoreTokens(); i++)
					value[i] = st.nextToken();
			}
			
			int length		= value.length;
			Class<?> type	= clazz.getComponentType();
			param			= Array.newInstance(type, length);

			for(int i = 0; i < length; i++)
			{
				String v = value[i];
				Object p = GeneralHelper.str2Object(type, v);
				Array.set(param, i, p);
			}
		}
		else
		{
			String value = null;
			
			if(valueType.isArray())
			{
				String[] array	= (String[])obj;
				if(array.length > 0)
					value = array[0];
			}
			else
				value = (String)obj;
			
			param = GeneralHelper.str2Object(clazz, value);
		}
		
		return param;
	}
	
	/** ��ȡָ������ Java Bean �������������������� Object ���������и��ඨ������ԣ�
	 * 
	 *  @param startClass	: Bean ����
	 *  @return				  ������ / ��������ӳ��  
	 */
	public static final Map<String, PropertyDescriptor> getPropDescMap(Class<?> startClass)
	{
		return getPropDescMap(startClass, Object.class);
	}
	
	/** ��ȡָ������ Java Bean �������������������� stopClass �����߲㸸���������и��ඨ������ԣ�
	 * 
	 *  @param startClass	: Bean ����
	 *  @param stopClass	: ��ֹ���ҵĸ�������
	 *  @return				  ������ / ��������ӳ��  
	 */
	public static final Map<String, PropertyDescriptor> getPropDescMap(Class<?> startClass, Class<?> stopClass)
	{
		Map<String, PropertyDescriptor> map = new HashMap<String, PropertyDescriptor>();
		
		try
		{
			BeanInfo info = Introspector.getBeanInfo(startClass, stopClass);
			PropertyDescriptor[] pds = info.getPropertyDescriptors();
			
			for(PropertyDescriptor pd : pds)
				map.put(pd.getName(), pd);
		}
		catch(IntrospectionException e)
		{
			throw new RuntimeException(e);
		}
		
		return map;
	}
	
	/** ��ȡ Java Bean ������
	 * 
	 *  @param bean	: Bean ʵ��
	 *  @return		: Bean ������ / ֵӳ��  
	 */
	public static final Map<String, Object> getProperties(Object bean)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, PropertyDescriptor> pps = getPropDescMap(bean.getClass());
		Set<Map.Entry<String, PropertyDescriptor>> set = pps.entrySet();
		
		for(Map.Entry<String, PropertyDescriptor> o : set)
		{
			String key = o.getKey();
			PropertyDescriptor pd = o.getValue();
			Method method = getPropertyReadMethod(pd);
			
			if(method != null)
			{
				Object obj = invokeMethod(bean, method);
				result.put(key, obj);
			}
		}
		
		return result;
	}

	/** ��ȡָ������ Java Bean ������Ϊ name ��������������
	 * 
	 *  @param startClass	: Bean ����
	 *  @param name			: ��������
	 *  @return				  ��������ӳ�䣬�Ҳ��������򷵻� null  
	 */
	public static final PropertyDescriptor getPropDescByName(Class<?> startClass, String name)
	{
		return getPropDescByName(startClass, Object.class, name);
	}
	
	/** ��ȡָ������ Java Bean ������Ϊ name ��������������
	 * 
	 *  @param startClass	: Bean ����
	 *  @param stopClass	: ��ֹ���ҵĸ�������
	 *  @param name			: ��������
	 *  @return				  ��������ӳ�䣬�Ҳ��������򷵻� null  
	 */
	public static final PropertyDescriptor getPropDescByName(Class<?> startClass, Class<?> stopClass, String name)
	{
		try
		{
			BeanInfo info = Introspector.getBeanInfo(startClass, stopClass);
			PropertyDescriptor[] pds = info.getPropertyDescriptors();
			
			for(PropertyDescriptor pd : pds)
			{
				if(pd.getName().equals(name))
					return pd;
			}
		}
		catch(IntrospectionException e)
		{
			throw new RuntimeException(e);
		}
		
		return null;
	}

	/** ��ȡ���Ե� getter ������ {@link Method} ����
	 * 
	 *  @param startClass	: Bean ����
	 *  @param property		: ��������
	 *  @return				  ��������ӳ�䣬�Ҳ��������򷵻� null  
	 */
	public static final Method getPropertyReadMethod(Class<?> startClass, String property)
	{
		return getPropertyReadMethod(startClass, null, property);
	}
	
	/** ��ȡ���Ե� getter ������ {@link Method} ����
	 * 
	 *  @param startClass	: Bean ����
	 *  @param stopClass	: ��ֹ���ҵĸ�������
	 *  @param property		: ��������
	 *  @return				  ��������ӳ�䣬�Ҳ��������򷵻� null  
	 */
	public static final Method getPropertyReadMethod(Class<?> startClass, Class<?> stopClass, String property)
	{
		PropertyDescriptor pd = getPropDescByName(startClass, stopClass, property);
		return getPropertyReadMethod(pd);
	}

	/** ��ȡ���Ե� getter ������ {@link Method} ����
	 * 
	 *  @param pd			: ���Ե� {@link PropertyDescriptor} ������
	 *  @return				  {@link Method} �����Ҳ����򷵻� null  
	 */
	public static final Method getPropertyReadMethod(PropertyDescriptor pd)
	{
		return getPropertyMethod(pd, true);
	}
	
	/** ��ȡ���Ե� setter ������ {@link Method} ����
	 * 
	 *  @param startClass	: Bean ����
	 *  @param property		: ��������
	 *  @return				  ��������ӳ�䣬�Ҳ��������򷵻� null  
	 */
	public static final Method getPropertyWriteMethod(Class<?> startClass, String property)
	{
		return getPropertyWriteMethod(startClass, null, property);
	}
	
	/** ��ȡ���Ե� setter ������ {@link Method} ����
	 * 
	 *  @param startClass	: Bean ����
	 *  @param stopClass	: ��ֹ���ҵĸ�������
	 *  @param property		: ��������
	 *  @return				  ��������ӳ�䣬�Ҳ��������򷵻� null  
	 */
	public static final Method getPropertyWriteMethod(Class<?> startClass, Class<?> stopClass, String property)
	{
		PropertyDescriptor pd = getPropDescByName(startClass, stopClass, property);
		return getPropertyWriteMethod(pd);
	}

	/** ��ȡ���Ե� setter ������ {@link Method} ����
	 * 
	 *  @param pd			: ���Ե� {@link PropertyDescriptor} ������
	 *  @return				  {@link Method} �����Ҳ����򷵻� null  
	 */
	public static final Method getPropertyWriteMethod(PropertyDescriptor pd)
	{
		return getPropertyMethod(pd, false);
	}

	/** ��ȡ���Ե� getter �� setter ������ {@link Method} ����
	 * 
	 *  @param pd			: ���Ե� {@link PropertyDescriptor} ������
	 *  @param readOrWrite	: ��ʶ��true -> getter, false -> setter
	 *  @return				  {@link Method} �����Ҳ����򷵻� null  
	 */
	public static final Method getPropertyMethod(PropertyDescriptor pd, boolean readOrWrite)
	{
		if(pd != null)
		{
			Method method = readOrWrite ? pd.getReadMethod() : pd.getWriteMethod();
			if(method != null && isPublicInstanceMethod(method))
				return method;
		}
		
		return null;
	}

	/** ���� Java Bean ������Ϊ name ������ֵ
	 * 
	 *  @param bean			: Bean ʵ��
	 *  @param name			: ��������
	 *  @param value		: ����ֵ����Ϊ���� 3 �����ͣ�<br>
	 *  					  &nbsp; &nbsp; 1) ���Ե�ʵ�����ͣ�ֱ�Ӷ����Ը�ֵ<br>
	 *  					  &nbsp; &nbsp; 2) {@link String} ���ͣ���ִ���Զ�����ת���ٶ����Ը�ֵ<br>
	 *  					  &nbsp; &nbsp; 3) {@link String}[] ���ͣ���ִ���Զ�����ת���ٶ����Ը�ֵ<br> 
	 */
	public static final <T> boolean setProperty(Object bean, String name, T value)
	{
		PropertyDescriptor pd = getPropDescByName(bean.getClass(), name);
		return setProperty(bean, pd, value);
	}
	
	/** ��ȡ Java Bean ������Ϊ name ������ֵ
	 * 
	 *  @param bean			: Bean ʵ��
	 *  @param name			: ��������
	 *  @throws				  RuntimeException ʧ�����׳���Ӧ���������쳣
	 */
	public static final <T> Result<Boolean, T> getProperty(Object bean, String name)
	{
		PropertyDescriptor pd = getPropDescByName(bean.getClass(), name);
		return getProperty(bean, pd);
	}

	/** ��ȡ Java Bean ������Ϊ name ������ֵ
	 * 
	 *  @param bean			: Bean ʵ��
	 *  @param pd			: ����������
	 *  @throws				  RuntimeException ʧ�����׳���Ӧ���������쳣
	 */
	public static final <T> Result<Boolean, T> getProperty(Object bean, PropertyDescriptor pd)
	{
		Result<Boolean, T> result = Result.initialBoolean();
		Method method = getPropertyReadMethod(pd);
		
		if(method != null)
		{
			T value = invokeMethod(bean, method);
			result.set(Boolean.TRUE, value);
		}
		
		return result;
	}

	/** ���� Java Bean ������Ϊ name �ĳ�Ա����ֵ
	 * 
	 *  @param bean			: Bean ʵ��
	 *  @param name			: ��Ա��������
	 *  @param value		: ��Ա����ֵ����Ϊ���� 3 �����ͣ�<br>
	 *  					  &nbsp; &nbsp; 1) ��Ա������ʵ�����ͣ�ֱ�ӶԳ�Ա������ֵ<br>
	 *  					  &nbsp; &nbsp; 2) {@link String} ���ͣ���ִ���Զ�����ת���ٶԳ�Ա������ֵ<br>
	 *  					  &nbsp; &nbsp; 3) {@link String}[] ���ͣ���ִ���Զ�����ת���ٶԳ�Ա������ֵ<br> 
	 */
	public static <T> boolean setFieldValue(Object bean, String name, T value)
	{
		Field field = getInstanceFiledByName(bean.getClass(), name);
		return setFieldValue(bean, field, value);
	}
	
	/** ��ȡ Java Bean ������Ϊ name �ĳ�Աֵ
	 * 
	 *  @param bean			: Bean ʵ��
	 *  @param name			: ��Ա��������
	 *  @throws				  RuntimeException ʧ�����׳���Ӧ���������쳣
	 */
	public static final <T> Result<Boolean, T> getFieldValue(Object bean, String name)
	{
		Field field = getInstanceFiledByName(bean.getClass(), name);
		return getFieldValue(bean, field);
	}

	/** ��ȡ Java Bean ������Ϊ name �ĳ�Աֵ
	 * 
	 *  @param bean			: Bean ʵ��
	 *  @param field		: ��Ա���� {@link Field} ����
	 *  @throws				  RuntimeException ʧ�����׳���Ӧ���������쳣
	 */
	public static final <T> Result<Boolean, T> getFieldValue(Object bean, Field field)
	{
		Result<Boolean, T> result = Result.initialBoolean();
		
		if(field != null && isInstanceField(field))
		{
			T value = invokeGetFieldValue(bean, field);
			result.set(Boolean.TRUE, value);
		}
			
		return result;
	}

	/** ���� Java Bean ������Ϊ name �����Ի��Ա����ֵ����� setter ���������ڣ�����ֱ���޸ĳ�Ա����
	 * 
	 *  @param bean			: Bean ʵ��
	 *  @param name			: �������ƻ��Ա��������
	 *  @param value		: ����ֵ���Ա��������Ϊ���� 3 �����ͣ�<br>
	 *  					  &nbsp; &nbsp; 1) ���Ի��Ա������ʵ�����ͣ�ֱ�Ӷ����Ի��Ա������ֵ<br>
	 *  					  &nbsp; &nbsp; 2) {@link String} ���ͣ���ִ���Զ�����ת���ٶ����Ի��Ա������ֵ<br>
	 *  					  &nbsp; &nbsp; 3) {@link String}[] ���ͣ���ִ���Զ�����ת���ٶ����Ի��Ա������ֵ<br> 
	 */
	public static final <T> boolean setPropertyOrFieldValue(Object bean, String name, T value)
	{
		return setProperty(bean, name, value) || setFieldValue(bean, name, value);
	}

	/** ���� Java Bean ������Ϊ name ������ֵ����� getter ���������ڣ�����ֱ�ӻ�ȡ��Ա������ֵ
	 * 
	 *  @param bean			: Bean ʵ��
	 *  @param name			: �������ƻ��Ա��������
	 */
	public static final <T> Result<Boolean, T> getPropertyOrFieldValue(Object bean, String name)
	{
		Result<Boolean, T> result = getProperty(bean, name);
		
		if(!result.getFlag())
			result = getFieldValue(bean, name);
		
		return result;
	}

	/** ��ȡĳ�������г�Ա������ {@link Field} ����
	 * 
	 *  @param clazz		: Ҫ���ҵ���
	 *  @return				: ʧ�ܷ��ؿռ���
	 */	
	public static final Set<Field> getAllFields(Class<?> clazz)
	{
		return getAllFields(clazz, null);
	}
	
	/** ��ȡĳ�������г�Ա������ {@link Field} ����
	 * 
	 *  @param clazz		: Ҫ���ҵ���
	 *  @param stopClazz	: ��ֹ���ҵ��ࣨ�����ĳ�Ա����Ҳ������ȡ��
	 *  @return				: ʧ�ܷ��ؿռ���
	 */
	public static final Set<Field> getAllFields(Class<?> clazz, Class<?> stopClazz)
	{
		Set<Field> fields = new HashSet<Field>();
		
		while(clazz != null && clazz != stopClazz)
		{
			Field[] fs = clazz.getDeclaredFields();
			Collections.addAll(fields, fs);
			
			clazz = clazz.getSuperclass();
			
		}
		
		return fields;
	}
	
	/** ��ȡĳ�������г�Ա��Ա������ {@link Field} ����
	 * 
	 *  @param clazz		: Ҫ���ҵ���
	 *  @return				: ʧ�ܷ��ؿռ���
	 */	
	public static final Set<Field> getInstanceFields(Class<?> clazz)
	{
		return getInstanceFields(clazz, null);
	}
	
	/** ��ȡĳ�������г�Ա��Ա������ {@link Field} ����
	 * 
	 *  @param clazz		: Ҫ���ҵ���
	 *  @param stopClazz	: ��ֹ���ҵ��ࣨ�����ĳ�Ա����Ҳ������ȡ��
	 *  @return				: ʧ�ܷ��ؿռ���
	 */
	public static final Set<Field> getInstanceFields(Class<?> clazz, Class<?> stopClazz)
	{
		Set<Field> fields = new HashSet<Field>();
		
		while(clazz != null && clazz != stopClazz)
		{
			Field[] fs = clazz.getDeclaredFields();
			for(Field f : fs)
			{
				if(isInstanceField(f))
					fields.add(f);
			}
			
			clazz = clazz.getSuperclass();
		}
		
		return fields;
	}
	
	/** ��ȡָ������ Java Bean �����г�Ա��Ա������ {@link Field} ���󣨰��� stopClass �����߲㸸���������и��ඨ��ĳ�Ա������
	 * ���÷��������θ����ͬ����Ա����
	 *  @return				  ��Ա������ / ��������ӳ��  
	 */
	public static final Map<String, Field> getInstanceFieldMap(Class<?> clazz)
	{
		return getInstanceFieldMap(clazz, null);
	}
	
	/** ��ȡָ������ Java Bean �����г�Ա������ {@link Field} ���󣨰��� stopClass �����߲㸸���������и��ඨ��ĳ�Ա������
	 * ���÷��������θ����ͬ����Ա����
	 *  @param clazz		: Bean ����
	 *  @param stopClazz	: ��ֹ���ҵĸ�������
	 *  @return				  ��Ա������ / ��������ӳ��  
	 */
	public static final Map<String, Field> getInstanceFieldMap(Class<?> clazz, Class<?> stopClazz)
	{
		Map<String, Field> map = new HashMap<String, Field>();
		
		while(clazz != null && clazz != stopClazz)
		{
			Field[] fs = clazz.getDeclaredFields();
			for(Field f : fs)
			{
				String name = f.getName();
				if(isInstanceField(f) && !map.containsKey(name))
					map.put(name, f);
			}
			
			clazz = clazz.getSuperclass();
		}
		
		return map;
	}
	
	/** ��ȡ Java Bean �ĳ�Ա����ֵ
	 * 
	 *  @param bean	: Bean ʵ��
	 *  @return		: Bean ��Ա������ / ֵӳ��  
	 */
	public static final Map<String, Object> getFieldValues(Object bean)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Field> fms = getInstanceFieldMap(bean.getClass());
		Set<Map.Entry<String, Field>> set = fms.entrySet();
		
		for(Map.Entry<String, Field> o : set)
		{
			String key	= o.getKey();
			Field field	= o.getValue();
			
			if(field != null && isInstanceField(field))
			{
				Object obj = invokeGetFieldValue(bean, field);
				result.put(key, obj);
			}
		}
		
		return result;
	}

	/** ��ȡĳ����������Ϊ name �ĳ�Ա������ {@link Field} ����
	 * 
	 *  @param clazz		: Ҫ���ҵ���
	 *  @param name			: ��������
	 *  @return				: ʧ�ܷ��� null
	 */
	public static final Field getFiledByName(Class<?> clazz,String name)
	{
		return getFiledByName(clazz, null, name);
	}

	/** ��ȡĳ����������Ϊ name �ĳ�Ա������ {@link Field} ����
	 * 
	 *  @param clazz		: Ҫ���ҵ���
	 *  @param stopClazz	: ��ֹ���ҵ��ࣨ�����ĳ�Ա����Ҳ������ȡ��
	 *  @param name			: ��������
	 *  @return				: ʧ�ܷ��� null
	 */
	public static final Field getFiledByName(Class<?> clazz, Class<?> stopClazz, String name)
	{
		Field f = null;
		
		do
		{
			try
			{
				f = clazz.getDeclaredField(name);
			}
			catch(NoSuchFieldException e)
			{
				clazz = clazz.getSuperclass();
			}
		} while(f == null && clazz != null && clazz != stopClazz);
		
		return f;
	}
	
	/** ��ȡĳ����������Ϊ name �ĳ�Ա��Ա������ {@link Field} ����
	 * 
	 *  @param clazz		: Ҫ���ҵ���
	 *  @param name			: ��������
	 *  @return				: ʧ�ܷ��� null
	 */
	public static final Field getInstanceFiledByName(Class<?> clazz,String name)
	{
		return getInstanceFiledByName(clazz, null, name);
	}

	/** ��ȡĳ����������Ϊ name �ĳ�Ա������ {@link Field} ����
	 * 
	 *  @param clazz		: Ҫ���ҵ���
	 *  @param stopClazz	: ��ֹ���ҵ��ࣨ�����ĳ�Ա����Ҳ������ȡ��
	 *  @param name			: ��������
	 *  @return				: ʧ�ܷ��� null
	 */
	public static final Field getInstanceFiledByName(Class<?> clazz, Class<?> stopClazz, String name)
	{
		Field f = null;
		
		do
		{
			try
			{
				Field f2 = clazz.getDeclaredField(name);
				
				if(isInstanceField(f2))
				{
					f = f2;
					break;
				}
				else
					clazz = clazz.getSuperclass();
					
			}
			catch(NoSuchFieldException e)
			{
				clazz = clazz.getSuperclass();
			}
		} while(clazz != null && clazz != stopClazz);
		
		return f;
	}
	
	/** ��ȡĳ�������з����� {@link Method} ����
	 * 
	 *  @param clazz		: Ҫ���ҵ���
	 *  @return				: ʧ�ܷ��ؿռ���
	 */
	public static final Set<Method> getAllMethods(Class<?> clazz)
	{
		return getAllMethods(clazz, null);
	}
	
	/** ��ȡĳ�������з����� {@link Method} ����
	 * 
	 *  @param clazz		: Ҫ���ҵ���
	 *  @param stopClazz	: ��ֹ���ҵ��ࣨ�����ķ���Ҳ������ȡ��
	 *  @return				: ʧ�ܷ��ؿռ���
	 */
	public static final Set<Method> getAllMethods(Class<?> clazz, Class<?> stopClazz)
	{
		Set<Method> methods = new HashSet<Method>();
		
		while(clazz != null && clazz != stopClazz)
		{
			Method[] m = clazz.getDeclaredMethods();
			Collections.addAll(methods, m);
			
			clazz = clazz.getSuperclass();
			
		}
		
		return methods;
	}
	
	/** ��ȡĳ����������Ϊ name������Ϊ parameterTypes �ķ����� {@link Method} ����
	 * 
	 *  @param clazz			: Ҫ���ҵ���
	 *  @param name				: ��������
	 *  @param parameterTypes	: ��������
	 *  @return					: ʧ�ܷ��� null
	 */
	public static final Method getMethodByName(Class<?> clazz, String name, Class<?>... parameterTypes)
	{
		return getMethodByName(clazz, null, name, parameterTypes);
	}
	
	/** ��ȡĳ����������Ϊ name������Ϊ parameterTypes �ķ����� {@link Method} ����
	 * 
	 *  @param clazz			: Ҫ���ҵ���
	 *  @param stopClazz		: ��ֹ���ҵ��ࣨ�����ķ���Ҳ������ȡ��
	 *  @param name				: ��������
	 *  @param parameterTypes	: ��������
	 *  @return					: ʧ�ܷ��� null
	 */
	public static final Method getMethodByName(Class<?> clazz, Class<?> stopClazz, String name, Class<?>... parameterTypes)
	{
		Method m = null;
		
		do
		{
			try
			{
				m = clazz.getDeclaredMethod(name, parameterTypes);
			}
			catch(NoSuchMethodException e)
			{
				clazz = clazz.getSuperclass();
			}
		} while(m == null && clazz != null && clazz != stopClazz);
		
		return m;
	}
	
	/** �� {@linkplain Class#getMethod(String, Class...)} ��ȡ {@link Method} ����
	 * 
	 *  @param clazz			: Ҫ���ҵ���
	 *  @param name				: ��������
	 *  @param parameterTypes	: ��������
	 *  @return					: ʧ�ܷ��� null
	 */
	public static final Method getMethod(Class<?> clazz, String name, Class<?>... parameterTypes)
	{
		Method m = null;
		
		try
		{
			m = clazz.getMethod(name, parameterTypes);
		}
		catch(NoSuchMethodException e)
		{
		}
		
		return m;
	}
}