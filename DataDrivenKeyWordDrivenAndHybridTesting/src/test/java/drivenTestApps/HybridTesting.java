package drivenTestApps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utility.ReadObject;
import utility.ReadingFromExcell;

public class HybridTesting {
	ReadingFromExcell objExcelFile;
	String[][] dataFromExcel;
	Properties objects;
	int numberOfRows;
	WebDriver driver;
	WebElement element;

	@DataProvider(name = "Passing Three parameters")
	public Object[][] createDataforTest3() {
		HashMap<String, String[]> hm = new HashMap<String, String[]>();
		String[] a = new String[4];
		a[0] = "11";
		a[1] = "12";
		a[2] = "13";
		a[3] = "14";

		hm.put("1", a);
		hm.put("2", a);
		hm.put("3", a);
		hm.put("4", a);

		List<String> ls = new ArrayList<String>();
		ls.add("1");
		ls.add("2");
		ls.add("3");
		ls.add("4");
		Object[][] retkeyword = { { "One", ls, hm }, { "Two", ls, hm }, { "Three", ls, hm } };
		return (retkeyword);

	}

	@Test(dataProvider = "Passing Three parameters")
	public void verifyData3(String testCase, ArrayList<String> testSteps, HashMap<String, String[]> hm) {
		System.out.println("Start Test " + testCase);
		Iterator<String> steps = testSteps.iterator();
		while (steps.hasNext()) {
			String step = (String) steps.next();
			System.out.println("Test Step :" + step);

			String keyWord = hm.get(step)[0];
			String objectType = hm.get(step)[1];
			String object = hm.get(step)[2];
			String value = hm.get(step)[3];
			System.out.println(keyWord + "--" + objectType + " --" + object + " --" + value);

		}
	}

}
