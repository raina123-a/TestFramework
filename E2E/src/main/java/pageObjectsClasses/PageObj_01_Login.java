package pageObjectsClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resoucres.TestBase;


public class PageObj_01_Login extends TestBase  {

	public WebDriver driver;
	
	public PageObj_01_Login(WebDriver driver){ 
		TestBase.driver = driver;
	}

	public final String title="My Store";

	public final  WebElement getSignIn=driver.findElement(By.className("login"));
	public final  WebElement getInputBox=driver.findElement(By.id("email_create"));
	public final  WebElement getAccount=driver.findElement(By.xpath("//div[@class='submit']//span[1]"));
	public final  WebElement getEmaild=driver.findElement(By.id("email"));
	public final  WebElement getPassword=driver.findElement(By.id("passwd"));
	public final  WebElement getObjLogin=driver.findElement(By.xpath("//p[@class='submit']//span[1]"));
	public final  WebElement getInvalidAlert=driver.findElement(By.xpath("//*[text()='Invalid password.']"));
}
