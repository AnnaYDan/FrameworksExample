package drivenTestApps;

import org.testng.annotations.Test;

import utility.ReadingFromExcell;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

public class DataDrivenTestingUsingExcel {
	// this program is to do data driven testing for travel portal

	WebDriver driver;

	@BeforeTest
	public void beforeClass() {
		// open and set crhome driver
		System.setProperty("webdriver.chrome.driver", utility.DriverPath.chromeDriver);
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // wait will be applied for any element not
																			// available for the first try
		driver.get("http://newtours.demoaut.com"); // opening a website

	}

	@AfterTest
	public void afterTest() {
		// close the drive after completion
		driver.quit();
	}

	@Test(dataProvider = "dp")
	public void f(String n, String s) {
		// actual test for login logout
		System.out.println(n + " ----" + s);
		driver.findElement(By.name("userName")).sendKeys(n);
		driver.findElement(By.name("password")).sendKeys(s);
		driver.findElement(By.name("login")).click();
		Assert.assertTrue(driver.findElement(By.linkText("SIGN-ON")).isDisplayed());
		driver.findElement(By.linkText("SIGN-ON")).click();

	}

	@DataProvider
	public Object[][] dp() throws IOException {
		// Prepare the path of excel file
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\testData\\TestCase.xlsx";
		// Call read file method of the class to read data
		String[][] dataFromExcel = ReadingFromExcell.readExcel(filePath, "dataForLogin", 2, 5, 1, 2);
		return dataFromExcel;
	}
}