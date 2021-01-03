package tests.day5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class ClassNameLocatorTests {

    public static void main(String[] args) {

        WebDriver driver = BrowserFactory.getDriver("chrome");
        assert driver != null;
        driver.manage().window().maximize();
        BrowserUtils.wait(2);

        driver.get("http://practice.cybertekschool.com/multiple_buttons");
        BrowserUtils.wait(2);

        // <h3 class="h3">Multiple buttons</h3>
        // h3 - it's a class name, or value of class attribute
        // if class has text with a space, that means there are more
        WebElement heading = driver.findElement(By.className("h3"));

        // we can read the text of the element
        System.out.println(heading.getText());


        BrowserUtils.wait(2);
        driver.quit();
    }
}
