package tests.day14;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigurationReader;

public class ConfigReaderTest {

    @Test
    public void test1() {
        String expectedBrowser = "chrome";
        // we write keys in "key" as a string
        // as return, we will get value
        // key=value
        // property name = value
        // we don't change property names, we change values
        // the idea is change in one place, and affect entire framework
        // let's say every class will read browser type from properties file
        // and to perform cross-browsing testing, we can easily change value of browser property
        String actualBrowser = ConfigurationReader.getValue("browser");
        Assert.assertEquals(actualBrowser, expectedBrowser);

        // read value of url property
        System.out.println("URL: " + ConfigurationReader.getValue("url"));

        // read value of credentials
        // read value of user_name propert
        System.out.println("Username: " + ConfigurationReader.getValue("user_name"));
        // read value of password property
        String password = ConfigurationReader.getValue("password");
        System.out.println("Password: " + password);
    }
}
