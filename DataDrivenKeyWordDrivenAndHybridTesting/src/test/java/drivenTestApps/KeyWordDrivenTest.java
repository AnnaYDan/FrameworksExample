package drivenTestApps;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utility.ReadObject;
import utility.ReadingFromExcell;
import utility.WebElementFinder;

public class KeyWordDrivenTest {

	String[][] dataFromExcel;
	int testCaseNumber;
	int testStep;
	WebDriver driver;
	WebElement element;
	Properties objects;

	@BeforeTest
	public void beforeTest() throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\testData\\TestCase.xlsx";
		dataFromExcel = ReadingFromExcell.readExcel(filePath, "testSteps", 3, 30, 1, 5);
		objects = ReadObject.getObjectRepository(utility.DriverPath.objectProperties);
		System.setProperty("webdriver.firefox.driver", utility.DriverPath.firefoxDriver);
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void test() throws Exception {
		testCaseNumber = 0;
		for (int row = 0; row < dataFromExcel.length; row++) {
			String testCase = dataFromExcel[row][0];
			String keyWord = dataFromExcel[row][1];
			String objectType = dataFromExcel[row][2];
			String object = objects.getProperty(dataFromExcel[row][3]);
			String value = dataFromExcel[row][4];
			if (!testCase.isEmpty()) {
				testCaseNumber++;
				System.out.println("Running Test Case " + testCaseNumber + " : " + testCase);
				testStep = 0;
				continue;
			}
			testStep++;
			if (object == null)
				object = dataFromExcel[row][3];
			System.out.println(";;;;;;;;;;;;;;;;;;;;;;" + object + "--------" + dataFromExcel[row][3]);

			if (keyWord.equalsIgnoreCase("Open")) {
				System.out.println("....Step " + testStep + " Opening " + value);
				driver.get(value);
			} else {
				element = WebElementFinder.findElement(driver, objectType, object);
				if (keyWord.equalsIgnoreCase("Click")) {
					System.out.println("....Step " + testStep + " Clicking " + object);
					element.click();

				} else if (keyWord.equalsIgnoreCase("Type")) {
					System.out.println("....Step " + testStep + " Typing on  " + object + " of Value = " + value);
					element.sendKeys(value);
				}
			}
		}
	}

}
