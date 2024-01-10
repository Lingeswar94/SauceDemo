package Commonfactory;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	public static Properties properties;
	public static WebDriver driver;
	public static ExcelProvider excelProvider;

	public static ExtentReports extentReports;
	public static ExtentTest test;

	public Properties loadbrowser() throws IOException {
		FileInputStream fileInputStream = new FileInputStream("./Config/config.properties");
		properties = new Properties();
		properties.load(fileInputStream);
		return properties;
	}

	@BeforeSuite
	public void Browserchoose() throws IOException {
		// ExtentSparkReporter extentSparkReporter= new ExtentSparkReporter(new
		// File(System.getProperty("user.dir")+"/Report/Saucedemo.html"));
		ExtentHtmlReporter reports;
		reports = new ExtentHtmlReporter(new File(
				System.getProperty("user.dir") + "/Report/Saucedemo.html" + Helper.getcurrentdate() + ".html"));
		extentReports = new ExtentReports();
		extentReports.attachReporter(reports);

		excelProvider = new ExcelProvider();
		loadbrowser();
		String Browser = properties.getProperty("Browser");
		String url = properties.getProperty("URL");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		if (Browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(option);

		} else if (Browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (Browser.equalsIgnoreCase("IE")) {
			WebDriverManager.edgedriver().setup();
			driver = new InternetExplorerDriver();
		} else {
			System.out.println("Not Supporting");
		}
		driver.navigate().to(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void Teardownmethod(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {

			//Helper.getscreenshot(driver);
			test.pass("Test Pass", MediaEntityBuilder.createScreenCaptureFromPath(Helper.getscreenshot(driver)).build());
			test.log(Status.PASS, "Passed");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.getscreenshot(driver)).build());
			//Helper.getscreenshot(driver);
		} 
	}

	@AfterSuite
	public void teardown() {
		driver.quit();
		extentReports.flush();
	}
}
