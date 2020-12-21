package tests.day9_Review;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BrowserUtils;

public class DataProviderTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        BrowserUtils.space();
        driver = BrowserUtils.getDriver("chrome");
        BrowserUtils.wait(1);
        assert driver != null;
        driver.manage().window().maximize();
    }

    // data provider can supply test with a test data.
    // It allows to do Data Driven Testing.
    // What is this? It's when same test runs multiple times
    // with different test data set
    @DataProvider(name = "testData")
    public static Object[][] testData() {
        return new Object[][]{{"https://www.apple.com/", "Apple"}, // 1st set of data
                {"http://google.com", "Google"}}; // 2nd set of data
    }

    // data provider must return 2D Array of Object
    // 1st parameter = 1st object in the inner array
    // url = https://www.apple.com/, title = Apple
    // 2nd parameter = 2nd object in the inner array
    // url = http://google.com, title = Google
    // we can have as many sets of data as we want
    @Test(dataProvider = "testData") // this test will run twice,
    // because we have 2 sets of data
    public void testWithDataProvider(String url, String title) {
        driver.get(url);
        Assert.assertEquals(driver.getTitle(), title);
    }

    @AfterMethod
    public void teardown() {
        BrowserUtils.wait(1);
        driver.quit();
    }
}
