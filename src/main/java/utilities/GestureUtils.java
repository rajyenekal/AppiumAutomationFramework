package utilities;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.android.AndroidDriver;

public class GestureUtils {

    private final AndroidDriver driver;

    // Constructor to initialize the driver
    public GestureUtils(AndroidDriver driver) {
        this.driver = driver;
    }

    /**
     * Scrolls until the WebElement is visible on the screen.
     * @param by The locator of the element to scroll to.
     * @param maxScrolls Maximum number of scroll attempts.
     * @return WebElement if found, otherwise throws an exception.
     */
    /**
     * Scrolls until the WebElement is visible on the screen.
     * @param by The locator of the element to scroll to.
     * @param maxScrolls Maximum number of scroll attempts.
     * @return WebElement if found, otherwise throws an exception.
     */
    public WebElement scrollToElement(By by, int maxScrolls) {
        int scrollCount = 0;
        while (scrollCount < maxScrolls) {
            try {
                WebElement element = driver.findElement(by);
                if (element.isDisplayed()) {
                    // Element is visible, return it
                    return element;
                }
            } catch (Exception e) {
                // Element not found yet, perform a vertical scroll
                performVerticalScroll();
            }
            scrollCount++;
        }
        throw new RuntimeException("Element not found after " + maxScrolls + " scrolls.");
    }


    /**
     * Performs a vertical scroll down gesture (from bottom to top).
     */
    public void performVerticalScroll() {
        performSwipe(driver.manage().window().getSize().width / 2,
                     (int) (driver.manage().window().getSize().height * 0.8),
                     driver.manage().window().getSize().width / 2,
                     (int) (driver.manage().window().getSize().height * 0.2),
                     700);
    }

    /**
     * Generic swipe gesture method.
     * @param startX Start X-coordinate.
     * @param startY Start Y-coordinate.
     * @param endX End X-coordinate.
     * @param endY End Y-coordinate.
     * @param duration Duration in milliseconds for the swipe.
     */
    public void performSwipe(int startX, int startY, int endX, int endY, int duration) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(duration), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(swipe));
    }

    /**
     * Performs a horizontal swipe gesture.
     */
    public void swipeHorizontal(int startX, int endX, int y, int duration) {
        performSwipe(startX, y, endX, y, duration);
    }

    public void swipe(int startX, int startY, int endX, int endY, int durationMillis) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(durationMillis), 
                PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }

    /**
     * Method to perform a scroll gesture.
     * 
     * @param startY Start Y coordinate. The vertical position on the screen 
     *                where the scroll will begin. A higher value indicates a 
     *                point closer to the bottom of the screen.
     * @param endY End Y coordinate. The vertical position on the screen where 
     *              the scroll will end. A lower value indicates a point closer 
     *              to the top of the screen.
     * @param durationMillis Duration of the scroll in milliseconds. This 
     *                       controls the speed of the scroll gesture; a 
     *                       longer duration results in a slower scroll.
     * 
     * Example usage:
     * 
     * // Scroll down from the bottom of the screen to the middle.
     * actions.scroll(1500, 800, 700);  // Scroll from Y: 1500 to Y: 800 over 700ms
     * 
     * // Scroll up from the middle of the screen to the top.
     * actions.scroll(800, 300, 500);  // Scroll from Y: 800 to Y: 300 over 500ms
     * 
     * // Perform a short scroll down.
     * actions.scroll(1200, 1000, 300);  // Scroll from Y: 1200 to Y: 1000 over 300ms
     */
    public void scroll(int startY, int endY, int durationMillis) {
        int startX = driver.manage().window().getSize().width / 2;  // Middle of the screen
        swipe(startX, startY, startX, endY, durationMillis);
    }

    
    
    /**
     * Performs a tap on the specified WebElement.
     * @param element The element to be tapped.
     */
    public void tap(WebElement element) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);

        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 
                                               element.getLocation().getX(), element.getLocation().getY()));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(tap));
    }

    /**
     * Performs a pinch gesture to zoom out on a WebElement.
     * @param element The element to be pinched.
     */
    public void pinch(WebElement element) {
        int centerX = element.getLocation().getX() + (element.getSize().getWidth() / 2);
        int centerY = element.getLocation().getY() + (element.getSize().getHeight() / 2);

        performSwipe(centerX, centerY - 100, centerX, centerY - 400, 500);  // Upper finger moves upward
        performSwipe(centerX, centerY + 100, centerX, centerY + 400, 500);  // Lower finger moves downward
    }

    /**
     * Performs a zoom gesture to zoom in on a WebElement.
     * @param element The element to be zoomed.
     */
    public void zoom(WebElement element) {
        int centerX = element.getLocation().getX() + (element.getSize().getWidth() / 2);
        int centerY = element.getLocation().getY() + (element.getSize().getHeight() / 2);

        performSwipe(centerX, centerY - 400, centerX, centerY - 100, 500);  // Upper finger moves downward
        performSwipe(centerX, centerY + 400, centerX, centerY + 100, 500);  // Lower finger moves upward
    }
}
