package tests.day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class VerifyPageTitle {

    // Test1: Verify that URL has not changed

    public static void main(String[] args) {

        WebDriver driver = BrowserFactory.getDriver("chrome");
        assert driver != null;
        driver.manage().window().maximize();

        driver.get("http://practice.cybertekschool.com/forgot_password");
        BrowserUtils.wait(3);

        // once we opened the page, we have to capture a title
        String expectedTitle = driver.getTitle();

        WebElement button = driver.findElement(By.id("form_submit"));
        BrowserUtils.wait(3);

        button.click();

        // read title again after clicking
        String actualTitle = driver.getTitle();
        // in this way, we're making sure that after clicking
        // we stay on same page

        if (expectedTitle.equals(actualTitle)) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
            System.out.println("Expected title: " + expectedTitle);
            System.out.println("Actual title: " + actualTitle);
        }

        BrowserUtils.wait(3);
        driver.quit();

    }
}
