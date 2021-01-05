package vytrack.tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BrowserUtils;
import utils.ConfigurationReader;
import utils.Driver;
import utils.TestBase;
import vytrack.pages.LoginPage;

//we write extends tests.TestBase to inherits @before and @after methods
//this class will be dedicated to tests related to login page only
//we don't have to find elements here
//we should find elements in page classes only
public class LoginTests extends TestBase {

    @Test(description = "Verify that page title is a 'Dashboard'")
    public void test1() {
        //create page object
        LoginPage loginpage = new LoginPage();
        //call login method
        //provide username and password
        loginpage.login(ConfigurationReader.getValue("user_name"), ConfigurationReader.getValue("password"));
        /*
        WebDriverWait wait = new WebDriverWait(Driver.get(),10);
        //this is an explicit wait
        //it waits until title is ''Dashboard
        wait.until(ExpectedConditions.titleIs("Dashboard"));
        */
        //verification page
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 15);
        wait.until(ExpectedConditions.titleIs("Dashboard"));
        Assert.assertEquals(Driver.getDriver().getTitle(), "Dashboard");
    }
}
