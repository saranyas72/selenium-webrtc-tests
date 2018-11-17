package myPackage;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.*;
import common.WebRTCLib;

public class chooseCameraResolution extends WebRTCLib {

		String sampleURL= "https://webrtc.github.io/samples/";
		driver.navigate().to(sampleURL+"src/content/getusermedia/resolution/");
		String[] buttons= {"QVGA","VGA","HD","Full HD","4K","8K"};

		//Verify each button clicks
		for(int i=0;i<buttons.length;i++)
		{
			driver.findElement(By.xpath("//*[.=\""+buttons[i]+"\"]")).click();
		
			driver.getCurrentUrl();
			WebElement video=driver.findElement(By.id("gum-res-local"));
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.attributeToBe(video, "readyState", "4"));
			Dimension videoSize=video.getSize();
			if(buttons[i].contentEquals("QVGA")) {
				
				// Verify Dimensions for QVGA
				Assert.assertTrue("QVGA Dimentions should be ((320, 240)", videoSize.width==320 && videoSize.height==240);
				
				// Verify Width range slider and its functionality
				WebElement rangeSlider = driver.findElement(By.xpath("//input[@type=\"range\"]"));
				Assert.assertTrue("Width Range Slider should be present and visible",rangeSlider.isDisplayed());
				System.out.println(rangeSlider.getSize().getWidth());
				System.out.println(rangeSlider.getSize().getHeight());
				System.out.println(rangeSlider.getLocation());
				
				int j = 0;
				while(j < 10) {
					rangeSlider.sendKeys(Keys.ARROW_RIGHT);
					j++;
				}
				
				Dimension videoNewSize=video.getSize();
				System.out.println("Dimensions: "+videoNewSize.getWidth()+", "+videoNewSize.getHeight());
				Assert.assertTrue("QVGA Dimentions should be ((330, 240)", videoNewSize.getWidth()==330 && videoNewSize.getHeight()==248);
				
				// Verify Lock Video & Aspect ratio options 
				Assert.assertTrue("Lock Video Size Checkbox should be present and visible", driver.findElement(By.id("sizelock")).isDisplayed());
				Assert.assertTrue("Lock aspect ratio Checkbox should be present and visible", driver.findElement(By.id("aspectlock")).isDisplayed());
						
			}
			else if (buttons[i].contentEquals("VGA")) {
				// Verify Dimensions for VGA
				Assert.assertTrue("QVGA Dimentions should be ((640, 480)", videoSize.width==640 && videoSize.height==480);
				
				// Verify Width range slider and its functionality
				WebElement rangeSlider = driver.findElement(By.xpath("//input[@type=\"range\"]"));
				Assert.assertTrue("Width Range Slider should be present and visible",rangeSlider.isDisplayed());
				System.out.println(rangeSlider.getSize().getWidth());
				System.out.println(rangeSlider.getSize().getHeight());
				System.out.println(rangeSlider.getLocation());
				
				int j = 0;
				while(j < 10) {
					rangeSlider.sendKeys(Keys.ARROW_RIGHT);
					j++;
				}
				
				Dimension videoNewSize=video.getSize();
				System.out.println("Dimensions: "+videoNewSize.getWidth()+", "+videoNewSize.getHeight());
				Assert.assertTrue("QVGA Dimentions should be ((330, 240)", videoNewSize.getWidth()==330 && videoNewSize.getHeight()==248);
				
				// Verify Lock Video & Aspect ratio options 
				Assert.assertTrue("Lock Video Size Checkbox should be present and visible", driver.findElement(By.id("sizelock")).isDisplayed());
				Assert.assertTrue("Lock aspect ratio Checkbox should be present and visible", driver.findElement(By.id("aspectlock")).isDisplayed());
	
			}
			else if (buttons[i].contentEquals("HD")) {
				
			}
			else if (buttons[i].contentEquals("Full HD")) {
	
			}
			else if (buttons[i].contentEquals("4K")) {
				
			}
			else if (buttons[i].contentEquals("8K")) {
				
			}
		}
//		driver.quit();		
	}
}