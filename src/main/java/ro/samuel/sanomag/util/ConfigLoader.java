package ro.samuel.sanomag.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static Properties props;

    private static void loadPropertiesFile() {
        props = new Properties();
        try (InputStream config = ConfigLoader.class.getClassLoader().getResourceAsStream("application.properties")) {
            props.load(config);
            System.out.println("application.properties file loaded succesfully");
        } catch (IOException ex) {
            System.out.println("Error loading properties file " + ex.getMessage());
        }
    }

    public static String getProperty(String key) {
        if (props == null) {
            loadPropertiesFile();
        }
        return props.getProperty(key);
    }
}