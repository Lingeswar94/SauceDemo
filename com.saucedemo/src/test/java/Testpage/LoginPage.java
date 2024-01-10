package Testpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	 WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	@FindBy(id="user-name")
	public static WebElement Username;
	
	@FindBy(id="password")
	public static WebElement Password;
	
	@FindBy(id="login-button")
	public static WebElement Loginbutton;
	
	
	public void getlogindata(String uname,String pword) {
		Username.sendKeys(uname);
		Password.sendKeys(pword);
		Loginbutton.click();
	}
}
