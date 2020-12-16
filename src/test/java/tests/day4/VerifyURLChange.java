package tests.day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class VerifyURLChange {

    /*
    Test 2: Verify that URL address changed
	1. Go to "http://practice.cybertekschool.com/forgot_password"
	2. Enter any valid email
	3. Click on retrieve password button
	4. Verify that URL equals to "http://practice.cybertekschool.com/email_sent"
     */

    public static void main(String[] args) {

        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
        BrowserUtils.wait(2);

        driver.get("http://practice.cybertekschool.com/forgot_password");
        BrowserUtils.wait(2);

        WebElement emailBox = driver.findElement(By.name("email"));
        emailBox.sendKeys("random@email.com");

        String expectedURL = "http://practice.cybertekschool.com/email_sent";

        WebElement retrievePassword = driver.findElement(By.id("form_submit"));
        retrievePassword.click();

        String actualURL = driver.getCurrentUrl();
        BrowserUtils.wait(2);

        if (actualURL.equals(expectedURL)) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
            System.out.println("Expected URL: " + expectedURL);
            System.out.println("Actual URL: " + actualURL);
        }

        driver.quit();


    }
}
