package base;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class Base {
	private static ThreadLocal<WebDriver> driver= new ThreadLocal<WebDriver>();
	private static Properties prop;

	static {
		try {
			// String propFilePath=System.
			// getProperty("D:\\Playwright_Course\\Topic-JAVA\\Workspace\\Cucumber_BDD_Automation_Assignment\\src\\main\\java\\data.properties");
			String propFilePath = System.getProperty("user.dir") + "\\src\\main\\java\\data.properties";
			FileInputStream fis = new FileInputStream(propFilePath);
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException("Properties not loaded", e);
		}
	}

	public static String getData(String key) {
		return prop.getProperty(key);
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public void setDriver(WebDriver driver) {
		Base.driver.set(driver);;

	}
}
