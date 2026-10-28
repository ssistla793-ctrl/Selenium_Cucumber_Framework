package hooks;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.java.*;
import io.qameta.allure.Allure;
import base.Base;

public class ScenarioHooks extends Base {

	@Before
	public void setup() {
		try {
			WebDriver driver;
			String browser = getData("browser");
			System.out.println("java -jar src/main/resources/selenium-server-4.51.0.jar standalone");
			if (browser.equals("chrome")) {
				//driver = new ChromeDriver();
				ChromeOptions options=new ChromeOptions();
				options.setCapability("platformName", "Windows");
				
				driver = new RemoteWebDriver(new URL("http://localhost:4444"),options);
			} else if (browser.equals("edge")) {
				//driver = new EdgeDriver();
				EdgeOptions options=new EdgeOptions();
				options.setCapability("platformName", "Windows");
				
				driver = new RemoteWebDriver(new URL("http://localhost:4444"),options);
			} else {
				//driver = new FirefoxDriver();
				FirefoxOptions options=new FirefoxOptions();
				options.setCapability("platformName", "Windows");
				
				driver = new RemoteWebDriver(new URL("http://localhost:4444"),options);
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			setDriver(driver);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@After
	public void tearDown(Scenario scenario) {
		System.out.println(scenario.getName());
		System.out.println(scenario.isFailed());
		if (!scenario.isFailed()) {
			byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);

			Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
		}
		getDriver().quit();
	}
}
