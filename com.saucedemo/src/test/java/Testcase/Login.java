package Testcase;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import Commonfactory.BrowserFactory;
import Commonfactory.ExcelProvider;
import Testpage.LoginPage;

public class Login extends BrowserFactory {

	@Test
	public void Logincase() throws IOException {
		test=extentReports.createTest("Login Sauce");
		
	//	System.out.println("Driver :" + driver);
		 excelProvider = new ExcelProvider();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test.info("Starting Application");
		loginPage.getlogindata(excelProvider.getstringdata("Login", 0, 1), excelProvider.getstringdata("Login", 1, 1));
		test.pass("Login done successfully");
	}
}
