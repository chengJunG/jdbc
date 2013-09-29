package com.cheng.common;

import java.util.Enumeration;
import java.util.Properties;

//display java System Properties
public class SystemProperties {
	public static void main(String[] args) {
		Properties properties = System.getProperties();
		
		Enumeration<Object> enumeration = properties.keys();
		
		while(enumeration.hasMoreElements()){
			
			Object key = enumeration.nextElement();
			
			//System.out.println(key + "-->" + properties.get(key));
		}
		
		//Prints this property list out to the specified output stream.
		properties.list(System.out);
	}
}
