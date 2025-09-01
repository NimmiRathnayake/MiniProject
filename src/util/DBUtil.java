package util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Simple DB utility: reads config.properties and provides Connections.
 * Put config.properties in the project root (or working directory) with keys:
 *   db.url, db.user, db.password
 */
public class DBUtil {
    private static final String CONFIG_FILE = "config.properties";
    private static String url;
    private static String user;
    private static String password;

    static {
        try (FileInputStream in = new FileInputStream(CONFIG_FILE)) {
            Properties props = new Properties();
            props.load(in);
            url = props.getProperty("db.url").trim();
            user = props.getProperty("db.user").trim();
            password = props.getProperty("db.password").trim();

            // optional on modern JVMs, but safe to keep
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load DB config from " + CONFIG_FILE, e);
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, user, password);
    }
}
