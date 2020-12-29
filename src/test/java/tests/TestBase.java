package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigurationReader;
import utils.Driver;

// this class will be a test foundation for all test classes
// we will put here only before and after parts
// In this way before and after methods will be the same
// Every test class will extend TestBase class
public abstract class TestBase {

    @BeforeMethod
    public void setup() {
        String url = ConfigurationReader.getValue("url");
        Driver.getDriver().get(url);
    }

    @AfterMethod
    public void teardown() {
        Driver.close();
    }
}
