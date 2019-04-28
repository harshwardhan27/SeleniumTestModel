package com.model.test.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.model.test.utils.*;

public class BaseModel {

	public static WebDriver driver;
	public Properties prop;
	public final By PRELOADER = By.id("preloader");

	public BaseModel() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\HARSH\\eclipse-workspace\\Testing\\src\\main"
					+ "\\java\\com\\model\\test\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialization() {
		String browserName = prop.getProperty("browser");
		if (browserName.equals("chrome")) {
			System.out.println("Tried to " + browserName);
			System.setProperty("webdriver.chrome.driver", "G:\\Harsh_Automation_Files\\SeleniumExtentions\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "G:\\\\Harsh_Automation_Files\\\\SeleniumExtentions\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		// Browser Properties
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Utility.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Utility.IMPLICIT_WAIT, TimeUnit.SECONDS);

		// Get the Test URL
		driver.get(prop.getProperty("BASE_URL"));
		Utility.WaitUntilInvisiblityOfElement(PRELOADER);
	}

}
