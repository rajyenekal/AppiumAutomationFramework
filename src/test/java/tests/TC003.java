package tests;



import org.openqa.selenium.By;
import org.testng.annotations.Test;

import pomPages.ViewsPage;
import test.base.BaseTest;
import utilities.GestureUtils;

public class TC003 extends BaseTest{
	
	@Test
	public void launchchrome() throws InterruptedException {
        ViewsPage viewsPage = new ViewsPage(driver);
        GestureUtils actions = new GestureUtils(driver);

        viewsPage.clickViewsOption();
        
        actions.scrollToElement(By.xpath("//android.widget.TextView[@content-desc='Tabs']"), 5).click();
        viewsPage.navigateBack(1);
        actions.performVerticalScroll();
        logger.info("performed vertical scroll");
        actions.scroll(1200, 800, 300);  // From Y: 1200 to Y: 800 in 300ms
        
        
	}

}
