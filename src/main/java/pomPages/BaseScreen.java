package pomPages;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.appium.java_client.android.AndroidDriver;

public class BaseScreen {
    protected AndroidDriver driver;
    protected static Logger logger  = LogManager.getLogger(BaseScreen.class);

    public BaseScreen(AndroidDriver driver) {
        this.driver = driver; 
    }
    
    
}
