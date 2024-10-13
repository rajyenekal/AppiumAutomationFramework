package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pomPages.ApiDemoPage;
import test.base.BaseTest;
import test.base.RetryAnalyzer;

/**
 * Test case to validate interactions with the API Demo app.
 */
public class TC002 extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testApiDemoLightThemeInteraction() {
        ApiDemoPage apiDemoPage = new ApiDemoPage(driver);
        
        
        try {
            // Step 1: Navigate to Views
            apiDemoPage.clickViewsOption();
            
            // Step 2: Click on Controls
            apiDemoPage.clickControlsOption();
            
            // Step 3: Click on Light Theme
            apiDemoPage.clickLightThemeOption();
            
            // Step 4: Enter text in Light Theme input field
            String inputText = "Hello World";
            apiDemoPage.enterTextInLightTheme(inputText);
            
            // Step 5: Validate that the text was entered correctly
            String enteredText = apiDemoPage.lightThemeInputField.getText();
            Assert.assertEquals(enteredText, inputText, "The input text does not match the expected value.");
            logger.info("Text validated successfully: " + enteredText);

            // Step 6: Navigate back to Views
            driver.navigate().back(); // Navigate back to the previous screen

        } catch (Exception e) {
            logger.error("Test case failed: " + e.getMessage());
            throw e; 
        }
    }
}
