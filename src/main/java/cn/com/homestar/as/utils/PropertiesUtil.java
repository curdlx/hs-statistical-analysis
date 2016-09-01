package cn.com.homestar.as.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	 private static String fileName = "common.properties";
	
	 private static Properties pros = new Properties();//Properties Object  
     
	 static { 
	        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
	        try {  
	            pros.load(in);//Load the file Properties to flow to the object  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }  
	      
	    /** 
	     * Through the attribute name to get attribute values 
	     * @param key 
	     * @return 
	     */  
	    public static String get(String key){  
	        return pros.getProperty(key);//Through the key specific get attribute value  
	    }
	    
		/**
		 * 获取逻辑开关属性
		 */
		public static boolean getBool(String name, boolean defVal) {
			try {
				return Boolean.parseBoolean(get(name));
			}catch(Exception e) {
				return defVal;
			}
		}
}
