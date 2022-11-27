package com.serverDao;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class serverPropertiesDao {
	Properties properties;

	public serverPropertiesDao() throws IOException {
		properties = new Properties();
		final String filePath = System.getProperty("user.dir") + "/server.properties";
		final InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath));
		properties.load(inputStream);
	}

	public String getProperty(final String s) {
		return properties.get(s).toString();
	}
}
