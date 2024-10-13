package tests;

import org.testng.annotations.Test;
import pomPages.ViewsPage;
import test.base.BaseTest;
import test.base.RetryAnalyzer;

public class TC001 extends BaseTest {
    
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testNavigateToViewsAndInteract() {
        ViewsPage viewsPage = new ViewsPage(driver);
        
        try {
            // Navigate to Views and perform actions
            viewsPage.clickViewsOption();
            viewsPage.clickAnimationOption();  
            viewsPage.clickPushOption();
            viewsPage.navigateBack(3);
            viewsPage.clickAnimationOption();
            viewsPage.clickLayoutAnimationsOption();

            // Go back to Views
            viewsPage.navigateBack(2);
            viewsPage.clickViewsOption();
            
            // Click on Controls
            viewsPage.clickControlsOption();
            viewsPage.clickLightThemeOption();
            
            // Enter text in Light Theme
            viewsPage.enterTextInLightTheme("Hello World");
        } catch (Exception e) {
            logger.error("Test case failed: " + e.getMessage());
            throw e; // This will mark the test as failed in TestNG
        }
    }
}
