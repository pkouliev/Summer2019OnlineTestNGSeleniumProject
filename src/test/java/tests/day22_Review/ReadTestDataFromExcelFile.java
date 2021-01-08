package tests.day22_Review;

import org.testng.annotations.Test;
import utils.BrowserUtils;
import utils.ExcelUtil;

import java.util.HashMap;
import java.util.Map;

public class ReadTestDataFromExcelFile {

    @Test
    public void test1() {
        ExcelUtil cars = new ExcelUtil("cars.xlsx", "cars");
        System.out.println(cars.getDataList());

        // get(0) means get data from first row
        // get("Color") means get value of Color column from first row
        String color = cars.getDataList().get(0).get("Color");
        String driverName = cars.getDataList().get(0).get("Driver");
        System.out.println(color);
        System.out.println(driverName);

        // instead of index we use key name
        // in List we use index, in Map - key name
        // every value is referenced by key
        // key must be unique
        // we have to specify data type of key and value
        // they can be different
        // same thing like with List: List<String>
        // Map it's key=value
        // List it's index=value
        Map<String, String> row = new HashMap<>();
        row.put("License Plate", "777");
        row.put("Driver", "SDET Driver");
        System.out.println(row.get("Driver"));

        Map<Integer, String> values = new HashMap<>();
        values.put(1, "apple");

        // key=Country name, value=Country code
        Map<String, String> countryCodes = new HashMap<>();
        countryCodes.put("USA", "+001");
        countryCodes.put("Australia", "+036");
        countryCodes.put("China", "085");
        countryCodes.put("Kazakhstan", "+007");
        countryCodes.put("Ukraine", "+380");
        countryCodes.put("Turkey", "+090");
        countryCodes.put("Uzbekistan", "+998");
        countryCodes.put("Azerbaijan", "+993");

        BrowserUtils.space();

        System.out.println(countryCodes.get("USA"));


    }


}
