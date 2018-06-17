package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import java.util.Map;
import atu.testng.reports.ATUReports;

public class ParentTest {

	protected WebDriver driver = null;
	String testName = this.getClass().getSimpleName();
	Properties dataProperties = null;
	static Properties configProperties = null;

	static {
		System.setProperty("atu.reporter.config", System.getProperty("user.dir")+"\\atu.properties");
		
		System.out.println("in static block");
		configProperties = new Properties();
		try {
			configProperties.load(new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\test\\Config\\CommonConfigs.properties")));
			
			// Put config data in System.setEnv
			Map<String, String> envVariables = System.getenv();
			for (String key : envVariables.keySet()) {
				System.out.println(key+" : "+envVariables.get(key));
				configProperties.setProperty(key, envVariables.get(key));
			}

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
		// Get browser type
 		String browserType = getConfig("browserType");
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
		
		ATUReports.setWebDriver(driver);
		driver.get(getConfig("baseUrl"));
	}

	public String getConfig(String key) {
		return configProperties.getProperty(key);
	}


	// to quit browser
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public String getData(String key) {
		// Get class name
		if (dataProperties == null) {
			String filePath = System.getProperty("user.dir")+"\\src\\test\\DataFiles\\"+testName+".properties";
			dataProperties = new Properties();
			try {
				dataProperties.load(new FileInputStream(new File(filePath)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dataProperties.getProperty(key);
	}


}
