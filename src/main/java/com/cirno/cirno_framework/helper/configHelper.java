package com.cirno.cirno_framework.helper;

import java.util.Properties;

import com.cirno.cirno_framework.utils.ProperUtil;
import com.cirno.cirno_framework.utils.constants;

/**
 * 
 * @author cirnotxm
 *
 */

public class configHelper {
	
	private static Properties properties = ProperUtil.loadProps(constants.CONFIG_FILE_NAME);
	
	public static String getJDBCDriver(){
		return ProperUtil.getString(properties, constants.JDBC_DRIVER_CONFIG);
	}
	

}
