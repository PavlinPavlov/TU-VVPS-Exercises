package tu.vvps.exc;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private static final String propFileName = "config.properties";

    public static final String LOCAL_TIME_ZONE = "local.timezone";

    private final Properties properties = new Properties();

    public static Configuration getInstance() {
        return Configuration.Holder.INSTANCE;
    }

    private Configuration() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("Property file '" + propFileName + "' not found in the classpath");
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public String getProperty(String prop) {
        return properties.getProperty(prop, "");
    }

    private static class Holder {
        private static final Configuration INSTANCE = new Configuration();
    }
}
