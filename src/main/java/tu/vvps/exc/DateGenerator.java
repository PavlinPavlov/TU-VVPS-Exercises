package tu.vvps.exc;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public class DateGenerator {

    private static String userEnteredTimeZone = "";

    public static OffsetDateTime getCurrentDateTime() {
        String zone = "".equals(userEnteredTimeZone)
                ? Configuration.getInstance().getProperty(Configuration.LOCAL_TIME_ZONE)
                : userEnteredTimeZone;

        LocalDateTime localDateTimeNow = LocalDateTime.now();
        return OffsetDateTime.of(localDateTimeNow, ZoneId.of(zone).getRules().getOffset(localDateTimeNow));
    }

    public static void setTimeZone(String timeZone) {
        userEnteredTimeZone = timeZone;
    }
}
