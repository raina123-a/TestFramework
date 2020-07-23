package resoucres;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestBase {

	public static WebDriver driver;
	public Properties prop;

	@Parameters({"browser"})
	@BeforeTest
	public WebDriver initializeDriver(@Optional( "Chrome") String browser) throws Exception {

		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32_83\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.26.0-win64\\geckodriver.exe");;
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette",true);
			driver= new FirefoxDriver(capabilities);

		}
		else if(browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", "C:\\IEDriverServer_Win32_3.150.1\\IEDriverServer.exe");
			driver = new InternetExplorerDriver(); 

		}
		else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		return driver;
	}

	public void simpleSleep(int x) 
	{
		try{
			Thread.sleep(x);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}


	public void getPropFile() throws IOException {
		prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\hp\\eclipse-workspace\\E2E\\src\\main\\java\\resoucres\\Data.properties");
		prop.load(fis);		
	}

	public void getScreenshotpath(String getTestMethod,WebDriver driver) throws IOException {
		TakesScreenshot ts= (TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);//virtual
		String destinationFile=System.getProperty("user.dir")+"\\reports\\"+getTestMethod+".png";
		FileUtils.copyFile(source,new File(destinationFile));
	}
}

