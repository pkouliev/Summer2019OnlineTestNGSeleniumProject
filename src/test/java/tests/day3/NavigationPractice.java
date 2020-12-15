package tests.day3;

import org.openqa.selenium.WebDriver;
import utils.BrowserFactory;

public class NavigationPractice {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize(); // to maximize browser window

        driver.get("http://google.com");
        Thread.sleep(5000);

        driver.navigate().to("http://amazon.com");
        Thread.sleep(5000);

        // navigate back to google
        driver.navigate().back();
        Thread.sleep(5000);

        // move forward to amazon again
        driver.navigate().forward();
        Thread.sleep(5000);

        // to refresh/reloaf webpage/website
        driver.navigate().refresh();
        Thread.sleep(5000);

        driver.quit();


    }
}
