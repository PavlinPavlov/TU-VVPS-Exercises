package tu.vvps.exc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tu.vvps.exc.Configuration;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class DateGenerator {

    private static final Logger logger = LoggerFactory.getLogger(DateGenerator.class);

    private static String userEnteredOffset = "";

    public static OffsetDateTime getCurrentDateTime() {
        String offset = "".equals(userEnteredOffset)
                ? Configuration.getInstance().getProperty(Configuration.LOCAL_TIME_ZONE)
                : userEnteredOffset;

        return OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.of(offset));
    }

    public static void setTimeZone(String offset) {
        userEnteredOffset = offset;
        logger.info("Setting app offset to: {}", offset);
    }
}
