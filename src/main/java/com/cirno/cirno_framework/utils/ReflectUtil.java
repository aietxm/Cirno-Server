package com.cirno.cirno_framework.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author cirnotxm
 * @version 1.0.0
 */

public class ReflectUtil {
	
	private static Logger logger = LoggerFactory.getLogger(ReflectUtil.class);
	
	//获得实例
	public static Object newInstance(Class<?> cls){
		Object instance;
		try {
			instance = cls.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("Reflect newinstance error");
			throw new RuntimeException(e);
		}
		return instance;
	}
	
	//调用方法
	public static Object invokeMethod(Object object, Method method, Object ...args){
		Object result;
		method.setAccessible(true);
		try {
			result = method.invoke(object, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			logger.error("Reflect invoke error");
			throw new RuntimeException(e);
		}
		
		return result;
	}
	
	//设置成员变量
	public static void setField(Object object, Field field, Object value){
		try{
			field.setAccessible(true);
			field.set(object, value);
		}catch(Exception e){
			logger.error("Reflect setField error");
			throw new RuntimeException(e);
		}
	}

}
