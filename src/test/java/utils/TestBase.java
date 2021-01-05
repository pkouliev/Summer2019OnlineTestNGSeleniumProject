package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

// this class will be a test foundation for all test classes
// we will put here only before and after parts
// In this way before and after methods will be the same
// Every test class will extend TestBase class
public abstract class TestBase {


    /*
    ExtentReports itself does not build any reports, but allows reporters to access information,
    which in turn build the said reports. An example of building an HTML report and adding information to ExtentX:
    ExtentHtmlReporter html = new ExtentHtmlReporter("Extent.html");
    ExtentXReporter extentx = new ExtentXReporter("localhost");
     */
    protected static ExtentReports extentReports;
    // The ExtentSparkReporter creates a rich standalone spark file.
    protected static ExtentSparkReporter extentSparkReporter;
    // Defines a test. You can add logs, snapshots, assign author and categories to a test and its children.
    protected static ExtentTest extentTest;

    @BeforeTest
    public void beforeTest() {
        // location of report
        // it's gonna be next to target folder, test-output folder
        String filePath = System.getProperty("user.dir") + "/test-output/report.html";
        extentReports = new ExtentReports();
        extentSparkReporter = new ExtentSparkReporter(filePath);
        extentReports.attachReporter(extentSparkReporter);
        extentSparkReporter.config().setReportName("Vytrack Test Results");
        extentReports.setSystemInfo("Environment", "QA1");
        extentReports.setSystemInfo("Browser", ConfigurationReader.getValue("browser"));
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
    }

    @AfterTest
    public void afterTest() {
        // Writes test information from the started reporters to their output view
        extentReports.flush();
    }


    @BeforeMethod
    public void setup() {
        String url = ConfigurationReader.getValue("url");
        Driver.getDriver().get(url);
        Driver.getDriver().manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    // ITestResult class describes the result of a test. (in TestNG)
    public void teardown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.fail(result.getName());
            extentTest.fail(result.getThrowable());
            try {
                // getScreenshot(result.getName() - takes screenshot and returns location of that screenshot
                // this method throws IOException (which is checked exception)
                // any checked eception must be handled immediately
                extentTest.addScreenCaptureFromPath(BrowserUtils.getScreenshot(result.getName()));
            } catch (IOException e) {
                // print error info
                e.printStackTrace();
            }
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.skip("Test case was skipped: " + result.getName());
        }

        Driver.close();
    }
}
