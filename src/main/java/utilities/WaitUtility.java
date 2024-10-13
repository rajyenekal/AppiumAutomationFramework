package utilities;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class WaitUtility {
	
	WebDriverWait wait;

	public WaitUtility(AndroidDriver driver) {
		 wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		 
	}
	
	public void waitTillVisible(WebElement elem) {
		wait.until(ExpectedConditions.visibilityOf(elem));
	}
	
	public void waitTillClickable(WebElement elem) {
		wait.until(ExpectedConditions.elementToBeClickable(elem));

	}

	public void waitTillInvisible(WebElement elem) {
		wait.until(ExpectedConditions.invisibilityOf(elem));
	}

	
}
