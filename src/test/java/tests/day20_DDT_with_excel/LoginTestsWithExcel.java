package tests.day20_DDT_with_excel;

import org.testng.annotations.DataProvider;
import utils.ExcelUtil;
import utils.TestBase;

public class LoginTestsWithExcel extends TestBase {

    // test data supplier
    // as many sets of data it returns
    // as many times exactly same test will run
    @DataProvider(name = "credentials")
    public static Object[][] credentials() {
        ExcelUtil qa2 = new ExcelUtil("vytrack_testusers.xlsx", "QA2-short");
        return qa2.getDataArray();
    }

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
