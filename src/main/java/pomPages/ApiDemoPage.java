package pomPages;

import io.appium.java_client.android.AndroidDriver;
import utilities.WaitUtility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object class representing the API Demo screen.
 */
public class ApiDemoPage extends BaseScreen {
    
    private WaitUtility wait;

    // Constructor to initialize the driver and elements
    public ApiDemoPage(AndroidDriver driver) {
        super(driver);
        this.wait = new WaitUtility(driver);
        PageFactory.initElements(driver, this);
    }

    // Element locators using @FindBy annotation
    @FindBy(xpath = "//android.widget.TextView[@content-desc='Views']")
    private WebElement viewsOption;

    @FindBy(xpath = "//android.widget.TextView[@content-desc='Controls']")
    private WebElement controlsOption;

    @FindBy(xpath = "//android.widget.TextView[@content-desc='1. Light Theme']")
    private WebElement lightThemeOption;

    @FindBy(id = "io.appium.android.apis:id/edit")
	public WebElement lightThemeInputField;

    @FindBy(xpath = "//android.widget.TextView[@content-desc='1. Dark Theme']")
    private WebElement darkThemeOption;

    /**
     * Clicks on the Views option.
     */
    public void clickViewsOption() {
        waitAndClick(viewsOption, "Views");
    }

    /**
     * Clicks on the Controls option.
     */
    public void clickControlsOption() {
        waitAndClick(controlsOption, "Controls");
    }

    /**
     * Clicks on the Light Theme option.
     */
    public void clickLightThemeOption() {
        waitAndClick(lightThemeOption, "Light Theme");
    }

    /**
     * Enters text into the Light Theme input field.
     * @param text The text to be entered.
     */
    public void enterTextInLightTheme(String text) {
        wait.waitTillVisible(lightThemeInputField);
        lightThemeInputField.sendKeys(text);
        logger.info("Entered text in Light Theme input field: " + text);
    }

    /**
     * A reusable method for waiting until an element is clickable and then clicking it.
     * @param element The WebElement to click.
     * @param elementName The name of the element for logging.
     */
    private void waitAndClick(WebElement element, String elementName) {
        try {
            wait.waitTillClickable(element);
            element.click();
            logger.info("Clicked on '" + elementName + "'");
        } catch (Exception e) {
            logger.error("Failed to click on '" + elementName + "': " + e.getMessage());
            throw e;
        }
    }
}
