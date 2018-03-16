package utility;

public class DriverPath {
	public static final String currentDir = System.getProperty("user.dir");
	public static final String chromeDriver = currentDir + "\\src\\test\\java\\utility\\chromedriver.exe";
	public static final String firefoxDriver = currentDir + "\\src\\test\\java\\utility\\geckodriver.exe";
	public static final String excelPath = currentDir + "\\src\\test\\java\\testData\\TestCase.xlsx";
	public static final  String objectProperties = currentDir + "\\src\\test\\java\\testData\\object.properties";
}
