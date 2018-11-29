package webrtctests;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebRTCUtils {

	private static final String BASE_URL = "https://webrtc.github.io/samples/";

	protected static WebDriver driver;
   
	@Before
	public static void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver","src/resources/chromedriver");
		driver = new ChromeDriver(getChromeOptions());
	}

	@After
	public static void tearDown() throws Exception {
		driver.quit();
	}

	
	public static ChromeOptions getChromeOptions() {
		ChromeOptions chromeOptions = new ChromeOptions();
		ArrayList<String> options = new ArrayList<String>();
			options.add("--use-fake-ui-for-media-stream");
			options.add("--use-fake-device-for-media-stream");
		chromeOptions.addArguments(options);
		return chromeOptions;
	}
	
	public static WebDriver getDriver() {
		return driver;
	}

	public static String getBaseUrl() {
		return BASE_URL;
	}

}
