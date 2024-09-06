package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landinpage;

	public WebDriver initializeDriver() throws IOException {

		// properties class

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//rahulshettyacademy//resources//GlobalData.properties");
		prop.load(fis);

		/**
		 * Java Terenary Operator to decide from where to get the browser value, from
		 * maven command line(system property) or global properties
		 * 
		 * If condition is true , the second argument will get executed and if condition
		 * is false third argument will get executed.
		 **/
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		// prop.getProperty("browser");
		if (browserName.contains("chrome")) {

			ChromeOptions options = new ChromeOptions();
			if (browserName.contains("headless"))
				options.addArguments("headless");

			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900)); // full screen

		} else if (browserName.contains("firefox")) {

			System.setProperty("webdriver.gecko.driver", "C:/Softwares/gecko/geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browserName.contains("edge")) {

			// Edge
			System.setProperty("webdriver.edge.driver", "edge.exe");
			WebDriver driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String, String>> getJsonDatatoMap(String filePath) throws IOException {

		// read json to string
		String JsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// String to Hashmap Jackson databind

		ObjectMapper mapper = new ObjectMapper();

		/**
		 * 
		 * 
		 * Multiple Hashmaps will be created based on the datasets in json and it will
		 * be returned as List
		 **/

		List<HashMap<String, String>> data = mapper.readValue(JsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landinpage = new LandingPage(driver);
		landinpage.goTo();
		return landinpage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}
