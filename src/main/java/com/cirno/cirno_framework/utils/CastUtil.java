package com.cirno.cirno_framework.utils;

/**
 * 
 * @author cirnotxm
 *
 */

public class CastUtil {
	
	public static String castString(Object object){
		return castString(object,"");
	}
	
	public static String castString(Object object, String defalutValue){
		
		return  object !=null ? String.valueOf(object):defalutValue;
		
	}
	
	public static int castInt(Object object){
		return castInt(object,0);
	}
	public static int castInt(Object object,int defaultValue){
		if (object!=null){
			String temp = castString(object, "");
			if(!temp.isEmpty()){
				return Integer.parseInt(temp);
			}
		}
		return defaultValue;
	}

}
