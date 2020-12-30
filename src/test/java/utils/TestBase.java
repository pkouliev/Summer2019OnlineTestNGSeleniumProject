package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

// this class will be a test foundation for all test classes
// we will put here only before and after parts
// In this way before and after methods will be the same
// Every test class will extend TestBase class
public class TestBase extends Driver {
    public WebDriver driver = Driver.getDriver();
    public WebDriverWait wait = new WebDriverWait(driver, 10);


    @BeforeMethod
    public void setup() {
        String url = ConfigurationReader.getValue("url");
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void teardown() {
        Driver.close();
    }
}
