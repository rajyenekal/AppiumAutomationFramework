package test.base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;



public class BaseTest {

    public static AndroidDriver driver;
    AppiumDriverLocalService service;
    protected static Logger logger ;

   // protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    boolean startServer = true;  


    @BeforeMethod
    public void launchApp() throws MalformedURLException {
    	logger = LogManager.getLogger(BaseTest.class);
    	
        logger.info("Preparing to launch the app...");
        if (startServer) {
            service = AppiumDriverLocalService.buildDefaultService();
            service.start();
            logger.info("Appium Server started programmatically...");
        } else {
        	logger.error("Appium Server not started programmatically...");
        }

        // Define Appium options
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("SM-M145F")
                .setPlatformName("Android")
                .setUdid("192.168.1.4:43445")
                .setAutomationName("uiautomator2")
                .setAppPackage("io.appium.android.apis")
                .setAppActivity("io.appium.android.apis.ApiDemos");

        // Initialize the AndroidDriver with Appium
        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723/"), options);

        logger.info("Launching app with sessionId : " + driver.getSessionId());
    }

    @AfterMethod
    public void tearDown() {
    	logger.info("Quitting the session and closing app...");
        if (driver != null) {
            driver.quit();
        }
        // Stop the server only if it was started programmatically
        if (startServer && service != null && service.isRunning()) {
            service.stop();
            logger.info("Appium Server stopped.");
        }
    }
    
    public static String captureScreenshot(String methodName) throws IOException {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);

            String timeStamp = new SimpleDateFormat("dd-MM-yyyy_hh.mm.ss a").format(new Date());
            timeStamp = timeStamp.replace("/", "_");

            String destFilePath = "Screenshots/" + methodName + "_" + timeStamp + ".png";
            File destination = new File(destFilePath);
            org.apache.commons.io.FileUtils.copyFile(source, destination);
            logger.info("Screenshot taken: " + destFilePath);

            return destFilePath;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
