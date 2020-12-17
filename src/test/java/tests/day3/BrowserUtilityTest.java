package tests.day3;

import org.openqa.selenium.WebDriver;

import static utils.BrowserFactory.*;

public class BrowserUtilityTest {

    public static void main(String[] args) throws InterruptedException {

        // getting WebDriver object (driver) using getDriver() method from BrowserFactory class and
        // providing reference variable/parameter (browser name)
        WebDriver driver = getDriver("chrome");
        assert driver != null;

        driver.get("http://practice.cybertekschool.com");

        // print a source code of the page
        System.out.println(driver.getPageSource());
        // to finish test execution

        Thread.sleep(5000);

        // shutdown browser
        driver.quit();


    }
}
