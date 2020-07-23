package testClasses.E2E;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjectsClasses.PageObj_01_Login;
import resoucres.TestBase;

public class Test_01_Login extends TestBase {

	public static String url="http://automationpractice.com/index.php";
	PageObj_01_Login obj ;
	boolean user=true;

	public static Logger log=LogManager.getLogger(TestBase.class.getName());

//	@BeforeClass
//	public void setUp() throws InterruptedException{
//		simpleSleep(5000);
//		obj = new PageObj_01_Login(driver);
//
//	}

	@DataProvider
	public Object[][] getData(){
		Object[][] data=new Object[1][2];
		data[0][0]="Test123@gmail.com";
		data[0][1]="test123";
		return data;
	}

	@Test(priority = 1,description="Verify get url")
	public void getBasePage() throws Exception {
		//driver.get(prop.getProperty("url"));
		driver.get(url);
		log.info("Browser invoked and url successfully launched");
		String getTitle=driver.getTitle();
		obj = new PageObj_01_Login(driver);
		Assert.assertEquals(obj.title, getTitle);	

	}
	@Test(priority =2,dataProvider="getData",description="Verify the user is able to create login credentials if not an existing user")
	public void createLoginCredentials(String Username,String Passowrd) {
		if (user==true) {
			obj.getEmaild.sendKeys(Username);
			obj.getPassword.sendKeys(Passowrd);
			obj.getObjLogin.click();
			//System.out.println("Successfully logged in");
			log.info("Successfully logged in");

			String Alert=obj.getInvalidAlert.getText();
			if(Alert.equalsIgnoreCase("Invalid password.")) {
				//System.out.println("Account is invalid");	
				log.info("Accunt is invalid");
			}
		}
		else {
			System.out.println("Creating User Account");
			obj.getSignIn.click();
			obj.getInputBox.sendKeys(Username);
			obj.getAccount.click();
			//System.out.println("Account created successfully");
			log.info("Account created successfully");
		}
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
