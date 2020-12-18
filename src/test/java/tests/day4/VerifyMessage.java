package tests.day4;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class VerifyMessage {

    /*
    Test3: Verify that confirmation message is displayed
	1. Go to "http://practice.cybertekschool.com/forgot_password"
	2. Enter any valid email
	3. Click on retrieve password button
	4. Verify that message "Your e-mail's been sent!" is displayed
     */

    public static void main(String[] args) {

        // as, we are starting from driver setup
        WebDriver driver = BrowserFactory.getDriver("chrome");
        assert driver != null;
        driver.manage().window().maximize();
        driver.get("http://practice.cybertekschool.com/forgot_password");
        BrowserUtils.wait(2);


        WebElement emailBox = driver.findElement(By.name("email"));

        // emailBox.sendKeys("random@email.com");
        // WebElement passwordButton = driver.findElement(By.id("form_submit"));
        // passwordButton.click();

        // We can also use Keys.ENTER which will simulate ENTER button press
        // instead of locating passwordButton element like above and clicking
        emailBox.sendKeys("random@email.com", Keys.ENTER);
        BrowserUtils.wait(2);

        WebElement message = driver.findElement(By.name("confirmation_message"));
        String expectedMessage = "Your e-mail's been sent!";
        String actualMessage = message.getText();
        // to get the text from the element
        BrowserUtils.wait(2);

        if (actualMessage.equals(expectedMessage)) {
            System.out.println("Message verified");
        } else {
            System.out.println("Message not verified");
            System.out.println("Expected message: " + expectedMessage);
            System.out.println("Actual message: " + actualMessage);
        }

        driver.quit();


    }
}
