package com.qa.base;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {
	
	public Properties prop;
	
	public TestBase() {
	try{
		prop = new Properties();
	    FileInputStream ip = new FileInputStream("./src/main/java/com/qa/config/config.properties");
		prop.load(ip);
	
	}catch(Exception e){
		
	}
		
		
	}
	

}