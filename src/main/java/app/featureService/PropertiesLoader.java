package app.featureService;

import app.exceptions.CannotLoadPropertiesException;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    private final static Properties properties = new Properties();

    static {
        loadProperties();
    }

    public static String getProperties(String key) {
        return properties.getProperty(key);
    }

    public static void loadProperties() {
        try(InputStream inputStream = PropertiesLoader.class.getClassLoader().getResourceAsStream("config.properties")){
            if (inputStream == null) {
                throw new CannotLoadPropertiesException("Could not load properties file");
            }
            properties.load(inputStream);
        }catch (Exception e){
            throw new CannotLoadPropertiesException("Could not load properties file");
        }
    }
}