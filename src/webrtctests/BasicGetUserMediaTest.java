package webrtctests;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicGetUserMediaTest extends WebRTCUtils{
	private static final String TEST_PAGE = WebRTCUtils.getBaseUrl() + "src/content/getusermedia/gum/";
	// get selenium web driver
	private static WebDriver driver;
	

	public static void main(String[] args) throws Exception {
	try {
		WebRTCUtils.setUp();
		
		// get selenium web driver
		driver = WebRTCUtils.getDriver();
		
		test_videoElm();
		
		WebRTCUtils.tearDown();	
			
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public static void test_videoElm() throws Exception {
						
		driver.navigate().to(TEST_PAGE);

		// get video element
		WebElement videoElement =  driver.findElement(By.id("gum-local"));
		
		// before 'Open camera' button click
		Assert.assertEquals(videoElement.getAttribute("paused"),"true"); // -- Verify video is paused and not playing.
		
		// click 'Open camera' button
		driver.findElement(By.id("showVideo")).click(); 
		
		// wait for video to be loaded
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.attributeToBe(videoElement, "readyState", "4"));
		
		// check if video is playing
			Assert.assertNull(videoElement.getAttribute("paused"));  // -- Verify video is playing and not paused.
		
	}
	
	
}