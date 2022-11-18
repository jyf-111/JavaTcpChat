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
		//jar 内
		// inputStream = ClassLoader.getSystemResourceAsStream("server.properties");
		// jar 外
		String filePath = System.getProperty("user.dir") + "/server.properties";
		inputStream = new BufferedInputStream(new FileInputStream(filePath));
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
