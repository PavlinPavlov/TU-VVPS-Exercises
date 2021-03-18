package tu.vvps.exc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private static final Logger logger = LoggerFactory.getLogger(Configuration.class);
    private static final String propFileName = "config.properties";

    public static final String LOCAL_TIME_ZONE = "local.offset";

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
            logger.error("Exception while configuring!", e);
        }
    }

    public String getProperty(String prop) {
        return properties.getProperty(prop, "");
    }

    private static class Holder {
        private static final Configuration INSTANCE = new Configuration();
    }
}
