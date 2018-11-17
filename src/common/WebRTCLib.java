package common;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebRTCLib {
	
	public static final String BASE_URL = "https://webrtc.github.io/samples/";

	private static WebDriver driver;

	public static void init() {
		System.setProperty("webdriver.chrome.driver","/Users/raghu/Downloads/chromedriver");
		driver = new ChromeDriver(getChromeOptions());
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
	
	public static void shutdown() {
		driver.quit();
	}
}
