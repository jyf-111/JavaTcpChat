package com.module;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServerPropertiesDao {
	Properties properties;
	InputStream inputStream;

	public ServerPropertiesDao() throws IOException {
		properties = new Properties();
		// inside jar 
		// inputStream = ClassLoader.getSystemResourceAsStream("server.properties");
		// ouutside jar
		String filePath = System.getProperty("user.dir") + "/server.properties";
		inputStream = new BufferedInputStream(new FileInputStream(filePath));
		properties.load(inputStream);
	}

	public String getProperty(String s){
		return properties.get(s).toString();
	}
}
