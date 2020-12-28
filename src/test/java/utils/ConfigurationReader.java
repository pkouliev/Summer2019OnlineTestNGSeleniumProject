package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
// ConfigurationReader.configFile.getValue("browser") - direct access
// ConfigurationReader.getValue("browser")


public class ConfigurationReader {
    // this class will be responsible for loading properties file and
    // will provide access to values based on key names
    // we use this class to load custom .properties files
    private static Properties configFile;
    private static FileInputStream input;

    static {
        try {
            String path = System.getProperty("user.dir") + "/configuration.properties";
            // provides access to file
            // try/catch block stands for handling exceptions
            // if exception occurs, code inside a catch block will be executed
            // any class that is related to InputOutput produce checked exception
            // without handling checked exception, you cannot run a code
            input = new FileInputStream(path);

            // initialize properties object
            configFile = new Properties();
            // load configuration.properties file
            configFile.load(input);
            // close input stream
            input.close();

        } catch (IOException e) {
            System.out.println("Failed to load properties file!");
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return configFile.getProperty(key);
    }
}
