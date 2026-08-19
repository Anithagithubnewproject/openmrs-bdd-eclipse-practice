package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream in = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (in == null) {
                throw new RuntimeException("config.properties not found on classpath.");
            }
            PROPERTIES.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        String override = System.getProperty(key);
        return override != null ? override : PROPERTIES.getProperty(key);
    }

    public static String baseUiUrl() {
        return get("base.ui.url");
    }

    public static String baseApiUrl() {
        return get("base.api.url");
    }

    public static String username() {
        return get("username");
    }

    public static String password() {
        return get("password");
    }

    public static boolean headless() {
        return Boolean.parseBoolean(get("headless"));
    }

    public static int explicitWaitSeconds() {
        return Integer.parseInt(get("explicit.wait.seconds"));
    }
}
