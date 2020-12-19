package tests.day7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserUtils;

import java.util.List;

public class CssSelectorPractice {

    public static void main(String[] args) {

        WebDriver driver = BrowserUtils.getDriver("chrome");
        assert driver != null;
        driver.manage().window().maximize();
        BrowserUtils.wait(1);

        driver.get("http://practice.cybertekschool.com/multiple_buttons");
        BrowserUtils.wait(1);

        // let's find all buttons, and click on them one by one
        // put . instead of space, because it's 2 class names
        // . also means class
        // # means id
        // in this case, we will find all buttons that have same class name
        List<WebElement> buttons = driver.findElements(By.cssSelector(".btn.btn-primary"));

        //alternative solution for className Css selector:
        //List<WebElement> buttons2 = driver.findElements(By.cssSelector("[class='btn btn-primary']"));

        // loop through all buttons
        for (WebElement button : buttons) {
            // and click on each button one by one
            button.click();
            BrowserUtils.wait(1);

            // get the result after click
            WebElement result = driver.findElement(By.cssSelector("#result"));
            // print a text of that result
            System.out.println(result.getText());
        }

        // find element with tag name h3, that has a parent element,
        // with class name container
        WebElement header = driver.findElement(By.cssSelector(".container > h3"));

        // alternative solution for css selector
        //WebElement header2 = driver.findElement(By.cssSelector("[class='container']>h3"));

        System.out.println(header.getText());

        WebElement p = driver.findElement(By.cssSelector("[class='container']>p"));
        System.out.println(p.getText());

        BrowserUtils.wait(1);
        driver.quit();

    }
}
