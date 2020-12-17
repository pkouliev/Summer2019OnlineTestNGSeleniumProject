package tests.day5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserUtils;

public class IDLocatorTests {

    public static void main(String[] args) {

        WebDriver driver = BrowserUtils.getDriver("chrome");
        assert driver != null;
        driver.manage().window().maximize();
        BrowserUtils.wait(2);

        driver.get("http://practice.cybertekschool.com/multiple_buttons");
        BrowserUtils.wait(2);

        WebElement specialButton = driver.findElement(By.id("disappearing_button"));
        specialButton.click();
        BrowserUtils.wait(2);

        WebElement result = driver.findElement(By.id("result"));
        System.out.println(result.getText());
        BrowserUtils.wait(2);

        driver.quit();


    }
}
