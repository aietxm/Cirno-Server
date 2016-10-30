package com.cirno.cirno_framework.utils;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.lang3.StringUtils;
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
	
	//获取指定包名下的所有类
	public static Set<Class<?>> getClassSet(String packageName){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		try{
			Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
			while(urls.hasMoreElements()){
				URL url = urls.nextElement();
				if(url!=null){
					String protocol = url.getProtocol();
					if(protocol.equals("file")){//class文件加载
						String path = url.getPath().replaceAll("%20", " ");
						addClass(classSet,path,packageName);
					}else if(protocol.equals("jar")){  //  Jar文件读取
						JarURLConnection connect = (JarURLConnection)url.openConnection();
						if(connect!=null){
							JarFile jarFile = connect.getJarFile();
							if(jarFile!=null){
								Enumeration<JarEntry> jarEntries = jarFile.entries();
								while(jarEntries.hasMoreElements()){
									JarEntry item = jarEntries.nextElement();
									String EntryName = item.getName();
									if(EntryName.endsWith(".class")){
										String className = EntryName.substring(0, EntryName.lastIndexOf(".")).replaceAll("/", ".");
										add(classSet,className);
									}
								}
							}
						}
					}
				}
			}
		}catch(Exception e){
			logger.error("get classSet failure!",e);
			throw new RuntimeException(e);
		}
		
		return classSet;
	}
	
	private static void addClass(Set<Class<?>> classSet, String path, String packageName){
		File[] files = new File(path).listFiles(new FileFilter() {
			
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				return (pathname.isFile()&&pathname.getName().endsWith(".class")) || pathname.isDirectory();
			}
		});
		
		for(File item : files){
			String fileName = item.getName();
			if(item.isFile()){
				String className = fileName.substring(0, fileName.lastIndexOf("."));
				if(StringUtils.isNoneEmpty(packageName)){
					className = packageName+"."+className;
				}
				add(classSet,className);
			}else{
				String subPath = fileName;
				if(StringUtils.isNoneEmpty(packageName)){
					subPath = packageName+"/"+subPath;
				}
				String subName = fileName;
				if(StringUtils.isNoneEmpty(packageName)){
					subName = packageName+"."+subName;
				}
				addClass(classSet,subPath,subName);
			}
		}
	}
	
	private static void add(Set<Class<?>> classSet, String className){
		Class<?> cl = loadClass(className,false);
		classSet.add(cl);
	}
}
