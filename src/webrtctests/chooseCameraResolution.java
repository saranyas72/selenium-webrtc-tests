package webrtctests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChooseCameraResolution{

	private static final String TEST_PAGE = WebRTCUtils.BASE_URL + "src/content/getusermedia/resolution/";
	// get selenium web driver
	private static WebDriver driver;
	

	public static void main(String[] args) {
		WebRTCUtils.setUp();
		
		// get selenium web driver
		driver = WebRTCUtils.getDriver();
		
		verifyVideoResolutions();
		
		WebRTCUtils.tearDown();
	}
	
	public static void verifyVideoResolutions() {
		driver.navigate().to(TEST_PAGE);	
		String[] buttons= {"QVGA","VGA","HD"};
		//,"Full HD","4K","8K"};

		//Verify each button clicks
		for(int i=0;i<buttons.length;i++)
		{
			driver.findElement(By.xpath("//*[.=\""+buttons[i]+"\"]")).click();
			switch(buttons[i]){
				case "QVGA":{
					testVideoDimensions(buttons[i],320,240);
//					moveSlider();
//					testVideoDimensions(buttons[i],330,248);
					break;
				}
				case "VGA":{
					testVideoDimensions(buttons[i],640,480);
					break;
				}
				case "HD":{
					testVideoDimensions(buttons[i],1280,720);
					break;
				}
//				case "Full HD":{
//					testVideoDimensions(buttons[i],1920,1080);
//					break;
//				}
//				case "4K":{
//					testVideoDimensions(buttons[i],4096,2160);
//					break;
//				}
//				case "8K":{
//					testVideoDimensions(buttons[i],7680,4320);
//					break;
//				}
				default :{
					break;
				}		
			}
		}
		
		// Verify Range Slider
		testRangeSlider();
				
		// Verify Lock Video & Aspect ratio options 
		Assert.assertTrue("Lock Video Size Checkbox should be present and visible", driver.findElement(By.id("sizelock")).isDisplayed());
		Assert.assertTrue("Lock aspect ratio Checkbox should be present and visible", driver.findElement(By.id("aspectlock")).isDisplayed());
	}
	
	public static void testRangeSlider() {
		WebElement QVGAButton = driver.findElement(By.xpath("//*[.=\"QVGA\"]")); 
		QVGAButton.click();
		testVideoDimensions("QVGA",320,240);
		moveSlider();
		testVideoDimensions("QVGA",330,248);
	}
	
	public static void testVideoDimensions(String buttonName, int width, int height) {	
		driver.getCurrentUrl();
		
		WebElement video=driver.findElement(By.id("gum-res-local"));
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.attributeToBe(video, "readyState", "4"));
		Dimension videoSize=video.getSize();
		
		// Verify Dimensions
		Assert.assertTrue(buttonName+" Dimentions should be (("+width+", "+height+")", videoSize.width==width && videoSize.height==height);
	}
	
	public static void moveSlider() {
			// Verify Width range slider and its functionality
			WebElement rangeSlider = driver.findElement(By.xpath("//input[@type=\"range\"]"));
			Assert.assertTrue("Width Range Slider should be present and visible",rangeSlider.isDisplayed());
				  
			int j = 0;
			while(j < 10) {
				rangeSlider.sendKeys(Keys.ARROW_RIGHT);
					j++;
				}
	}			
}