package drivenTestApps;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
// test comment 
import utility.ReadObject;
import utility.ReadingFromExcell;
import utility.WebElementFinder;

public class KeyWordDrivenTestAnotherFile {
	ReadingFromExcell objExcelFile;
	String[][] dataFromExcel;
	Properties objects;
	int numberOfRows;
	WebDriver driver;
	WebElement element;

	@BeforeTest
	public void beforeClass() throws IOException {

		// Create a object of ReadExcelFile class

		// this is jus a comment
		// Prepare the path of excel file

		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\testData\\TestCase.xlsx";
		String objectFilepath = System.getProperty("user.dir") + "\\src\\test\\java\\testData\\object.properties";
		// reading keyword driven data from excel file
		dataFromExcel = ReadingFromExcell.readExcel(filePath, "testSteps", 2, 11, 1, 5);
		// reading object from repository file
		objects = ReadObject.getObjectRepository(objectFilepath);

		System.setProperty("webdriver.chrome.driver", utility.DriverPath.chromeDriver);
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // wait will be applied for any element not
																		// available for the first try

	}

	@Test
	public void f() throws Exception {
		int testCaseNumber = 0;
		int testStep = 0;
		for (int row = 0; row < dataFromExcel.length; row++) {

			String testCase = dataFromExcel[row][0];
			String keyWord = dataFromExcel[row][1];
			String objectType = dataFromExcel[row][2];
			String object = objects.getProperty(dataFromExcel[row][3]);
			String value = dataFromExcel[row][4];

			if (!testCase.isEmpty()) {
				testCaseNumber++;
				testStep = 0;
				System.out.println("Starting TestCase " + testCaseNumber + ": " + testCase);
				continue;
			}
			testStep++;

			if (keyWord.equals("Open")) {
				System.out.println("....Step-" + testStep + " Opening : " + value);
				driver.get(value);
			} else {

				element = WebElementFinder.findElement(driver, objectType, object);

				if (keyWord.equals("Type")) {
					System.out.println("....Step-" + testStep + " Typing value= " + value + "  to " + object);
					element.sendKeys(value);
				} else if (keyWord.equals("Click")) {
					System.out.println("....Step-" + testStep + " Clicking " + object);

					element.click();
				} else if (keyWord.equals("Assert")) {
					Assert.assertTrue(element.getText().contains(value));
				} else {
				}

			}

		}

	}

}
