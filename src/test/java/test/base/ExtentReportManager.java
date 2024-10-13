package test.base;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.TimeZone;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

    private ExtentSparkReporter sparkReporter;
    private ExtentReports extent;
    private ExtentTest test;
    private String repName;
    private String timeStamp;

    @Override
    public void onStart(ITestContext testContext) {
        initializeReport();
        setSystemInfo();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        createTestNode(result, Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        createTestNode(result, Status.FAIL, "Test Failed");
        logFailureDetails(result);
        handleRetry(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        createTestNode(result, Status.SKIP, "Test Skipped");
        test.log(Status.SKIP, result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extent.flush();
    }

    /** 
     * Initialize the ExtentReports and SparkReporter.
     */
    private void initializeReport() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh.mm.ss a.dd.MM.yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        timeStamp = sdf.format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
        sparkReporter.config().setDocumentTitle("ApiDemos Automation Project");
        sparkReporter.config().setReportName("ApiDemos Automation Test Report");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    /**
     * Set system-level information for the report.
     */
    private void setSystemInfo() {
        extent.setSystemInfo("Application", "Demo Apis");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", "Rajaneesh");
    }

    /**
     * Create a test node in the Extent report with the given status and message.
     */
    private void createTestNode(ITestResult result, Status status, String message) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(status, message);
    }

    /**
     * Log the failure details, including the screenshot.
     */
    private void logFailureDetails(ITestResult result) {
        test.log(Status.FAIL, result.getThrowable().getMessage());

        try {
            String screenshotPath = BaseTest.captureScreenshot(result.getName());
            File screenshotFile = new File(screenshotPath);

            if (screenshotFile.exists()) {
                String base64Screenshot = Base64.getEncoder()
                        .encodeToString(Files.readAllBytes(screenshotFile.toPath()));
                test.addScreenCaptureFromBase64String(base64Screenshot);
            } else {
                System.out.println("Screenshot file not found: " + screenshotPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle test retries by logging a warning if the test is being retried.
     */
    private void handleRetry(ITestResult result) {
        Object retryAnalyzer = result.getAttribute("retryAnalyzer");
        if (retryAnalyzer instanceof RetryAnalyzer) {
            RetryAnalyzer analyzer = (RetryAnalyzer) retryAnalyzer;
            if (analyzer.retry(result)) {
                test.log(Status.WARNING, "Retrying the failed test...");
            }
        }
    }
}
