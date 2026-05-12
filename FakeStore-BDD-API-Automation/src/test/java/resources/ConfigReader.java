package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private static Properties prop;
	static {
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/global.properties");
			prop = new Properties();
			prop.load(fis);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load property file");
		}
	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}

}
