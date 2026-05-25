package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Base {
	public static WebDriver driver;
	public Properties prop;
	String applicationUrl;

	public WebDriver launchBrowser(String propertyFileName, String browserParam) throws IOException {
		prop = new Properties();
		InputStream is = getClass().getClassLoader().getResourceAsStream(propertyFileName);
		if (is == null) {
			throw new RuntimeException("Property file not found: " + propertyFileName);
		}
		prop.load(is);
		String browser = browserParam != null ? browserParam : prop.getProperty("browser");

		if (browser == null) {
			browser = "chrome"; // fallback
		}

		if ("chrome".equalsIgnoreCase(browser)) {
			driver = new ChromeDriver();
		} else if ("edge".equalsIgnoreCase(browser)) {
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Unsupported browser: " + browser);
		}

		driver.manage().window().maximize();

		applicationUrl = prop.getProperty("applicationURL");
		driver.get(applicationUrl);
		return driver;
	}

}
