package test.base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 1;
    protected static Logger logger = LogManager.getLogger(RetryAnalyzer.class);

    @Override
    public boolean retry(ITestResult result) {
        // Log the current test status before retrying
        logger.info("Attempting retry for test: " + result.getMethod().getMethodName() +
                    ", Current retry count: " + retryCount +
                    ", Test status: " + getResultStatusName(result.getStatus()));

        if (retryCount < maxRetryCount) {
            retryCount++;
            logger.info("Retrying test: " + result.getMethod().getMethodName() +
                        " for the " + retryCount + " time.");
            return true;
        }

        // Log when retry is not performed
        logger.info("Test: " + result.getMethod().getMethodName() + 
                    " reached max retry count. Marking as failed.");
        return false;
    }

    // Helper method to convert test result status to a readable format
    private String getResultStatusName(int status) {
        switch (status) {
            case ITestResult.SUCCESS:
                return "SUCCESS";
            case ITestResult.FAILURE:
                return "FAILURE";
            case ITestResult.SKIP:
                return "SKIPPED";
            default:
                return "UNKNOWN";
        }
    }
}
