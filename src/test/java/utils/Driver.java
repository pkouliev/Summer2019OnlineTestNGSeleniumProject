package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
    private static WebDriver driver;

    // you cannot do like this, if constructor is private Driver obj = new Driver()
//    private Driver() {
//
//    }

    // if switch statement complains on String parameter
    // change java version to the latest.
    // File -> Project Structure -> Set Project language level to the latest
    public static WebDriver getDriver() {
        // if WebDriver object was not created yet
        if (driver == null) {
            // create WebDriver object based on browser value from properties
            String browser = ConfigurationReader.getValue("browser");
            // if browser type is wrong, stop tests and throw exception
            // no browser will be opened
            switch (browser) {
                case "chrome" -> {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                }
                case "firefox" -> {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
                default -> throw new RuntimeException("Wrong browser type");
            }
        }
        // if WebDriver object was created - you can use it
        return driver;
    }

    public static void close() {
        // if driver still exists
        if (driver != null) {
            // close all browsers
            driver.quit();
            // destroy driver object, ready for garbage collector (gc)
            driver = null;
        }
    }
}
