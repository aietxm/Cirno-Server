package com.cirno.cirno_framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author tongxuemin_sx
 *
 */

public final class ClassUtil {
	private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);
	
	//获取类加载器
	public static ClassLoader getClassLoader(){
		return Thread.currentThread().getContextClassLoader();
	}
	
	//加载类
	public static Class<?> loadClass(String className, boolean isInitialized){
		Class<?> cls;
		try{
			cls = Class.forName(className, isInitialized, getClassLoader());
		} catch(ClassNotFoundException e){
			logger.error("load class failure",e);
			throw new RuntimeException(e);
		}
		return cls;
	}
}
