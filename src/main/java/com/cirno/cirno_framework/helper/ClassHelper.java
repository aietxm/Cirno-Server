package com.cirno.cirno_framework.helper;

import java.util.HashSet;
import java.util.Set;

import com.cirno.cirno_framework.annotation.Controller;
import com.cirno.cirno_framework.annotation.Service;
import com.cirno.cirno_framework.utils.ClassUtil;
/**
 * 
 * @author cirnotxm
 * @version 1.0.0
 */

public final class classHelper {
	
	private static final Set<Class<?>> Class_set;
	
	static{
		String packageName = configHelper.getBasePackage();
		Class_set = ClassUtil.getClassSet(packageName);
	}
	
	public static Set<Class<?>> getAllClass(){
		return Class_set;
	}
	
	//   获得所有的Service类
	public static Set<Class<?>> getServiceSet(){
		Set<Class<?>> serviceSet = new HashSet<Class<?>>();
		for(Class<?> cls: Class_set){
			if(cls.isAnnotationPresent(Service.class)){
				serviceSet.add(cls);
			}
		}
		return serviceSet;
		
	}
	
	public static Set<Class<?>> getControllerSet(){
		Set<Class<?>> controllerSet = new HashSet<Class<?>>();
		for(Class<?> cls : Class_set){
			if(cls.isAnnotationPresent(Controller.class)){
				controllerSet.add(cls);
			}
		}
		return controllerSet;
	}
	
	public static Set<Class<?>> getBeanSet(){
		Set<Class<?>> beanSet = new HashSet<Class<?>>();
		beanSet.addAll(getServiceSet());
		beanSet.addAll(getControllerSet());
		return beanSet;
 	}
	

}
