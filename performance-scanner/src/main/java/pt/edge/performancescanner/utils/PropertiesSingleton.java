package pt.edge.performancescanner.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import pt.edge.performancescanner.model.PerformanceScanner;

public class PropertiesSingleton {
	private static PropertiesSingleton instance = null;

	HashMap<String, String> appSettings = new HashMap<>();
	HashMap<String, String> msgSettings = new HashMap<>();

	protected PropertiesSingleton() {
		loadProperties("performancescanner.properties", appSettings);
		loadProperties("messages.properties", msgSettings);
	}

	public static PropertiesSingleton getInstance() {
		if (instance == null) {
			instance = new PropertiesSingleton();
		}
		return instance;
	}

	private void loadProperties(String filename, HashMap<String, String> map) {
		try {
			Properties properties = new Properties();
			InputStream input = PerformanceScanner.class.getClassLoader().getResourceAsStream(filename);
			properties.load(input);
			for (final String name : properties.stringPropertyNames())
				map.put(name, properties.getProperty(name));
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getAppProperty(String key) {
		return appSettings.get(key);
	}

	public String getMsgProperty(String key) {
		return msgSettings.get(key);
	}
}
