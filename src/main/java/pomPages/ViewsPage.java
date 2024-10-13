package pomPages;

import io.appium.java_client.android.AndroidDriver;
import utilities.WaitUtility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewsPage extends BaseScreen {
    
    private WaitUtility wait;

    // Constructor
    public ViewsPage(AndroidDriver driver) {
        super(driver);
        this.wait = new WaitUtility(driver); // Initialize WaitUtility with the driver
        PageFactory.initElements(driver, this); // Initialize elements with PageFactory
    }
    
    public void navigateBack(int times) {
        for (int i = 0; i < times; i++) {
            driver.navigate().back();
        }
    }
    
    // Using @FindBy annotation to locate elements
    @FindBy(xpath = "//android.widget.TextView[@content-desc='Views']")
    private WebElement viewsOption;

    @FindBy(xpath = "//android.widget.TextView[@content-desc='Animation']")
    private WebElement animationOption;

    @FindBy(xpath = "//android.widget.TextView[@text='Push']")
    private WebElement pushOption;

    @FindBy(xpath = "//android.widget.TextView[@content-desc='Layout Animations']")
    private WebElement layoutAnimationsOption;

    @FindBy(xpath = "//android.widget.TextView[@content-desc='Controls']")
    private WebElement controlsOption;

    @FindBy(xpath = "//android.widget.TextView[@content-desc='1. Light Theme']")
    private WebElement lightThemeOption;

    @FindBy(id = "io.appium.android.apis:id/edit")
    private WebElement lightThemeInputField;

    // Method to click the Views option
    public void clickViewsOption() {
        try {
            wait.waitTillClickable(viewsOption);
            viewsOption.click();
            logger.info("Clicked on 'Views'");
        } catch (Exception e) {
            logger.error("Failed to click on 'Views': " + e.getMessage());
            throw e; // Propagate the exception
        }
    }

    // Method to click the Animation option
    public void clickAnimationOption() {
        try {
            wait.waitTillClickable(animationOption);
            animationOption.click();
            logger.info("Clicked on 'Animation'");
        } catch (Exception e) {
            logger.error("Failed to click on 'Animation': " + e.getMessage());
            throw e; // Propagate the exception
        }
    }

    // Method to click the Push option
    public void clickPushOption() {
        try {
            wait.waitTillClickable(pushOption);
            pushOption.click();
            logger.info("Clicked on 'Push'");
        } catch (Exception e) {
            logger.error("Failed to click on 'Push': " + e.getMessage());
            throw e; // Propagate the exception
        }
    }

    // Method to click the Layout Animations option
    public void clickLayoutAnimationsOption() {
        try {
            wait.waitTillClickable(layoutAnimationsOption);
            layoutAnimationsOption.click();
            logger.info("Clicked on 'Layout Animations'");
        } catch (Exception e) {
            logger.error("Failed to click on 'Layout Animations': " + e.getMessage());
            throw e; // Propagate the exception
        }
    }

    // Method to click the Controls option
    public void clickControlsOption() {
        try {
            wait.waitTillClickable(controlsOption);
            controlsOption.click();
            logger.info("Clicked on 'Controls'");
        } catch (Exception e) {
            logger.error("Failed to click on 'Controls': " + e.getMessage());
            throw e; // Propagate the exception
        }
    }

    // Method to click the Light Theme option
    public void clickLightThemeOption() {
        try {
            wait.waitTillClickable(lightThemeOption);
            lightThemeOption.click();
            logger.info("Clicked on 'Light Theme' under Controls");
        } catch (Exception e) {
            logger.error("Failed to click on 'Light Theme': " + e.getMessage());
            throw e; // Propagate the exception
        }
    }

    // Method to enter text into the Light Theme input field
    public void enterTextInLightTheme(String text) {
        try {
            wait.waitTillVisible(lightThemeInputField);
            lightThemeInputField.sendKeys(text);
            logger.info("Entered text into the Light Theme input field: " + text);
        } catch (Exception e) {
            logger.error("Failed to enter text into Light Theme input field: " + e.getMessage());
            throw e; // Propagate the exception
        }
    }
}
