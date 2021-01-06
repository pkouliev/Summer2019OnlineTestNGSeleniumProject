package tests.day20_DDT_with_excel;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Driver;
import utils.ExcelUtil;
import utils.TestBase;
import vytrack.pages.LoginPage;

public class LoginTestsWithExcel extends TestBase {


    @Test(dataProvider = "credentials1", description = "Login with different credentials - Scenario1")
    public void loginTest1(String username, String password, String firstName, String lastName, String result) {
        // extentTest is must because we will get null pointer exception
        extentTest = extentReports.createTest("Login as " + username);
        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 15);
        String expectedPageTitle = "Dashboard";
        // add here wait with condition for title matching expectedPageTitle
        wait.until(ExpectedConditions.titleIs(expectedPageTitle));
        Assert.assertEquals(Driver.getDriver().getTitle(), expectedPageTitle);
        extentTest.pass("Login test passed for user: " + username);
    }

    @Test(dataProvider = "credentials2", description = "Login with different credentials - Scenario2")
    public void loginTest2(String username, String password, String firstName, String lastName, String result) {
        // extentTest is must because we will get null pointer exception
        extentTest = extentReports.createTest("Login as " + username);
        if (username.equals("username")) {
            // will make test skip
            // it will not fail
            // because first row is dedicated to column names
            throw new SkipException("Test was skipped because it's first row!");
        } else {
            LoginPage loginPage = new LoginPage();
            loginPage.login(username, password);
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 15);
            String expectedPageTitle = "Dashboard";
            // add here wait with condition for title matching expectedPageTitle
            wait.until(ExpectedConditions.titleIs(expectedPageTitle));
            Assert.assertEquals(Driver.getDriver().getTitle(), expectedPageTitle);
            extentTest.pass("Login test passed for user: " + username);
        }
    }

    // test data supplier
    // as many sets of data it returns
    // as many times exactly same test will run
    @DataProvider(name = "credentials1")
    public static Object[][] credentials1() {
        ExcelUtil qa2 = new ExcelUtil("vytrack_testusers.xlsx", "QA2-short");
        return qa2.getDataArrayData();
    }

    @DataProvider(name = "credentials2")
    public static Object[][] credentials2() {
        ExcelUtil qa2 = new ExcelUtil("vytrack_testusers.xlsx", "QA2-short");
        return qa2.getDataArray();
    }

//    public Object[][] credentials2() {
//        ExcelUtil qa2 = new ExcelUtil("vytrack_testusers.xlsx", "QA2-short");
//        String[][] data = new String[qa2.rowCount()-1][qa2.columnCount()];
//        for(Map<String, String> map : qa2.getDataList()) {
//            String username = map.get("username");
//            String password = map.get("password");
//
//            for (int i = 0; i < qa2.rowCount(); i++) {
//                for (int j = 0; j < qa2.columnCount(); j++) {
//                    String value = qa2.getCellData(i+1, j);
//                    data[i][j] = value;
//                }
//            }
//            return data;
//    }

    /*
    public static void main(String[] args) {

        BrowserUtils.space();
        ExcelUtil qa2 = new ExcelUtil("vytrack_testusers.xlsx", "QA2-short");
        System.out.println("Row count: " + qa2.rowCount());
        BrowserUtils.space();
        System.out.println(qa2.getColumnsNames());
        BrowserUtils.space();
        System.out.println(qa2.getDataList());
        BrowserUtils.space();
        // map is a data structure
        // in map, every value is referenced by key
        // when we retrieve data from map, we don't specify index, we specify key name
        // keys are represented by column names
        // like in properties file kyy=value
        for(Map<String, String> map : qa2.getDataList()) {
            System.out.println(map);
        }
        BrowserUtils.space();
    }
    */
}
