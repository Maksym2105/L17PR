package app.featureService;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionSupplier {

    private final static String HOST_URL = "db.host_url";
    private final static String USER = "db.username";
    private final static String PASSWORD = "db.password";

    private ConnectionSupplier () {
    }

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(
                    PropertiesLoader.getProperties(HOST_URL),
                    PropertiesLoader.getProperties(USER),
                    PropertiesLoader.getProperties(PASSWORD)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}