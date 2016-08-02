package com.cirno.cirno_framework.utils;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author cirnotxm
 *
 */

public class ProperUtil {
	static Logger logger = LoggerFactory.getLogger(ProperUtil.class);
	
	/**
	 * 加载配置文件
	 * @param propertiesPath
	 * @return
	 */
	public static Properties loadProps(String propertiesPath){
		Properties properties = null;
		InputStream is = null;
		
		try{
			is = ProperUtil.class.getClassLoader().getResourceAsStream(propertiesPath);
			if (is == null){
				throw new FileNotFoundException("properties " + propertiesPath+"is not found!");
			}
			properties = new Properties();
			properties.load(is);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Fail to load properties.");
		}finally{
			if (is!=null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error("Fail to close Stream");
				}
			}
		}
		
		return properties;
	}
	
	public static String getString (Properties properties , String key){
		if(properties.containsKey(key)){
			return properties.getProperty(key);
		}
		return "";
	}
	
	public static int getInt(Properties properties, String key){
		if(properties.containsKey(key)){
			return CastUtil.castInt(properties.getProperty(key));
		}
		
		return 0;
	}

}
