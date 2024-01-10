package Commonfactory;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Helper {

	public static void capturedscreenshot(WebDriver driver) {

		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(file, new File("./Screenshot/Login"+getcurrentdate()+".png"));
			System.out.println("Screenshot taken");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("unable to take screenshot"+e.getMessage());
		}
	}

	public static String getscreenshot(WebDriver driver)  {
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rectangle = new Rectangle(dimension);
		BufferedImage image = robot.createScreenCapture(rectangle);
		String screenshot = System.getProperty("user.dir") + "/Screenshot/Login" + getcurrentdate() + ".png";
		System.out.println("Screenshot taken");
		File file = new File(screenshot);
	    try {
			ImageIO.write(image, "png",file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			  return screenshot;                         
		
	}

	public static String getcurrentdate() {
		DateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss_");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
