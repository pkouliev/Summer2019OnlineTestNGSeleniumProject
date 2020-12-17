package tests.day5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BrowserUtils;

public class LinkTextLocatorTests {

    public static void main(String[] args) {

        WebDriver driver = BrowserUtils.getDriver("chrome");
        assert driver != null;
        driver.manage().window().maximize();
        BrowserUtils.wait(2);

        driver.get("http://practice.cybertekschool.com/");
        BrowserUtils.wait(2);

        // <a href="/autocomplete">Autocomplete</a>
        // Autocomplete - it's the text that you see in the link
        // place where it will navigate you specified in href
        // it works only if tag name is <a>
        // if you want to use linkText or partialLinkText locator strategy
        // element must start with <a>
        // this is how we specify links in HTML
        driver.findElement(By.linkText("Autocomplete"));
        BrowserUtils.wait(2);

        driver.quit();


    }
}
