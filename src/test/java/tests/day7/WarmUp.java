package tests.day7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserUtils;

import java.util.List;

public class WarmUp {
    // how to find all links?
    // every link has a tag name <a>

    public static void main(String[] args) {

        WebDriver driver = BrowserUtils.getDriver("chrome");
        assert driver != null;
        driver.manage().window().maximize();
        BrowserUtils.wait(2);

        int numLinks = 0;

        driver.get("https://www.cybertekschool.com/");
        BrowserUtils.wait(2);


        List<WebElement> linkList = driver.findElements(By.xpath("//a"));

        // size of the linkList = number of links
        System.out.println("Number of links: " + linkList.size());

        // or by using forEach loop it can be counted as well

        for (WebElement eachLink : linkList) {
            numLinks++;
        }

        System.out.println("Number of links: " + numLinks);

        // print text of all links, one by one
        for (WebElement eachLink : linkList) {
            // print the text of every link
            // if text is there
            if (!eachLink.getText().isEmpty()) {
                System.out.println(eachLink.getText());
            }
        }

        BrowserUtils.wait(2);
        driver.quit();
    }
}
