package tests.day3;

import org.openqa.selenium.WebDriver;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class NavigationPractice {

    public static void main(String[] args) {

        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize(); // to maximize browser window

        driver.get("http://google.com");

        // wait for 3 seconds
        // this is our custom method
        // since method is static, we use class name to call the method
        // as a parameter, we provide number of seconds (time in seconds)
        BrowserUtils.wait(3);

        // Print Page Title
        System.out.println(driver.getTitle());

        driver.navigate().to("http://amazon.com");
        BrowserUtils.wait(3);

        // navigate back to google
        driver.navigate().back();
        BrowserUtils.wait(3);

        // move forward to amazon again
        driver.navigate().forward();
        BrowserUtils.wait(3);

        // to refresh/reloaf webpage/website
        driver.navigate().refresh();
        BrowserUtils.wait(3);
        driver.getTitle(); // <title> .... </title>

        driver.quit();

        // what will happen if I call driver again after quit?
        //driver.get("http://google.com"); // Can't call driver after quit(),
        // will get an Exception
        // after close(), if there is only one tab, will shutdown browser as well
        // we cannot use driver anymore, we have to recreate object of WebDriver


    }
}
