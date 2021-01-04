package vytrack.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ConfigurationReader;

// according to page object model design
// we have to create corresponding page class
// for each page of application
// Login page = LoginPage class
// every page class will store web elements and methods related to that page
public class LoginPage extends BasePage {

    @FindBy(id = "prependedInput") // this line will initialize web element
    public WebElement userNameInput;

    @FindBy(id = "prependedInput2") // without @FindBy, web element will be null
    public WebElement passwordInput;

    @FindBy(id = "_submit")
    public WebElement loginButton;

    @FindBy(className = "alert alert-error")
    public WebElement warningMessage;


//    public LoginPage() {
//        // it's mandatory if you want to use @FindBy annotation
//        // this means LoginPage class
//        // Driver.getDriver() return WebDriver object
//        PageFactory.initElements(driver, this);
//    }

    /**
     * reusable login method
     * just call this method to login, provide userName and password as parameters
     *
     * @param userName userName is provided within test once login method called.
     * @param password password is provided within test once login method called.
     */
    public void login(String userName, String password) {
        userNameInput.sendKeys(userName);
        // Keys.ENTER to replace login button click
        passwordInput.sendKeys(password, Keys.ENTER);
    }

    String userNameQA1 = ConfigurationReader.getValue("user_name");
    String passwordQA1 = ConfigurationReader.getValue("password");

    public void loginQA1() {
        userNameInput.sendKeys(userNameQA1);
        // Keys.ENTER to replace login button click
        passwordInput.sendKeys(passwordQA1, Keys.ENTER);
    }


}
