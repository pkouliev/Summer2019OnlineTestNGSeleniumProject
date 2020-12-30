package vytrack.tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import vytrack.pages.LoginPage;

// we write extends TestBase to inherit @BeforeMethod and @AfterMethod
// this class will be dedicated to tests related to Login page only
// we don't have to find elements here
// we should find elements in page classes only
public class LoginTests extends LoginPage {

    @Test(description = "Verify that page title is 'Dashboard'")
    public void test1() {
        // create page object
        //LoginPage loginPage = new LoginPage();

        // provide userName and password
        login("storemanager85", "UserUser123");

        // this is an explicit wait
        // it waits until title is 'Dashboard'
        wait.until(ExpectedConditions.titleIs("Dashboard"));
        // verification stage
        // Driver.get() = driver same things
        // Driver.get() = return WebDriver object
        String title = driver.getTitle();
        Assert.assertEquals(title, "Dashboard", "Test failed. Title doesn't match");
        System.out.println("Title: " + title + " matches. Test passed");

    }
}
