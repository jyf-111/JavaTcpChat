package com.module;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServerPropertiesDao {
	Properties properties;
	InputStream inputStream;

	public ServerPropertiesDao() throws IOException {
		properties = new Properties();
		inputStream = ClassLoader.getSystemResourceAsStream("server.properties");
		properties.load(inputStream);
	}

	public String getProperty(String s){
		return properties.get(s).toString();
	}

	public static void main(String[] args) {
		var path = System.getProperty("user.dir");
		System.out.println(path);
		try {
			new ServerPropertiesDao();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
