package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import atu.testng.reports.ATUReports;

public class ParentTest {

	protected WebDriver driver = null;
	String testName = this.getClass().getSimpleName();
	Properties dataProperties = null;
	static Properties configProperties = null;

	static {
		System.out.println("in static block");
		System.setProperty("atu.reporter.config", System.getProperty("user.dir")+"\\src\\test\\Config\\atu.properties");
		configProperties = new Properties();
		try {
			configProperties.load(new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\test\\Config\\CommonConfigs.properties")));
			// Put config data in System.setEnv
			Map<String, String> envVariables = System.getenv();
			for (String key : envVariables.keySet()) {
				System.out.println(key+" : "+envVariables.get(key));
				configProperties.setProperty(key, envVariables.get(key));
			}
			System.out.println("Config: "+configProperties);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	// to launch browser
	@BeforeClass
	public void setUp() {
		// Get Test data
		System.out.println("In set up");
		String filePath = System.getProperty("user.dir")+"\\src\\test\\DataFiles\\"+testName+".properties";
		System.out.println("Data file path:"+filePath);
		dataProperties = new Properties();
		System.out.println("Data file object created.");
		
		try {
			dataProperties.load(new FileInputStream(new File(filePath)));
			System.out.println("Data file loaded");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Get browser type
		String browserType = getConfig("BROWSER_TYPE");
		
		System.out.println("Selected browser: "+browserType);
		
		switch (browserType) {
		case "FF":
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "CHROME":
			System.out.println("in chrome");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "IE":
			break;
		default:
			System.out.println("Incorrect browser selected.");
			System.exit(0);
		}

		System.out.println("In Setup");
		// Set driver
		ATUReports.setWebDriver(driver);
		System.out.println("Set driver for ATU");

		driver.get(getConfig("BASEURL"));
		System.out.println(getConfig("BASEURL"));
	}

	public static String getConfig(String key) {
		System.out.println("Required key: "+key);
		System.out.println("Value: "+configProperties.getProperty(key));
		return configProperties.getProperty(key);
	}


	// to quit browser
	@AfterClass
	public void tearDown() {
		System.out.println("In tearDown");
//		driver.quit();
	}

	public String getData(String key) {
		return dataProperties.getProperty(key);
	}


}
