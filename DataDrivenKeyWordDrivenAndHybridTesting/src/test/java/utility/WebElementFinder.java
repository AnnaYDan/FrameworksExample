package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebElementFinder {
	public static WebElement findElement(WebDriver driver, String objectType, String object) throws Exception {
		// find element from the page and returns it

		if (objectType.equalsIgnoreCase("id")) {
			return driver.findElement(By.id(object));

		} else if (objectType.equalsIgnoreCase("name")) {
			return driver.findElement(By.name(object));

		} else if (objectType.equalsIgnoreCase("XPATH")) {
			return driver.findElement(By.xpath(object));

		} else if (objectType.equalsIgnoreCase("link")) {
			return driver.findElement(By.linkText(object));

		} else if (objectType.equalsIgnoreCase("PartialLink")) {
			return driver.findElement(By.partialLinkText(object));

		} else if (objectType.equalsIgnoreCase("CSS")) {
			return driver.findElement(By.cssSelector(object));
		} else if (objectType.equalsIgnoreCase("CLASSNAME")) {
			return driver.findElement(By.className(object));
		} else {
			throw new Exception("Wrong object type");

		}

	}
}
