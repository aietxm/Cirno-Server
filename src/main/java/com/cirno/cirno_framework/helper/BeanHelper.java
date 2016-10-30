package com.cirno.cirno_framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.cirno.cirno_framework.utils.ReflectUtil;

/**
 * 
 * @author cirnotxm
 * @version 1.0.0
 */

public class BeanHelper {
	
	private static final Map<Class<?>, Object> BeanMap = new HashMap<Class<?>,Object>();
	//建立Bean的映射
	static {
		Set<Class<?>> beanSet = classHelper.getBeanSet();
		for(Class<?> cls : beanSet){
			BeanMap.put(cls, ReflectUtil.newInstance(cls));
		}
	}
	
	public static Map<Class<?>, Object> getBeanMap(){
		return BeanMap;
	}
}
