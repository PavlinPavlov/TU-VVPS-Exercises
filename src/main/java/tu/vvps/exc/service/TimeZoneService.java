package tu.vvps.exc.service;

import tu.vvps.exc.dao.StaticTimeZoneDAO;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.zone.ZoneRulesException;

public class TimeZoneService {

    private final StaticTimeZoneDAO staticTimeZoneDAO;

    private TimeZoneService() {
        staticTimeZoneDAO = StaticTimeZoneDAO.getInstance();
    }

    public static TimeZoneService getInstance() {
        return LazyHolder.INSTANCE;
    }

    public ZoneOffset getOffsetByCity(String city) {
        try {
            return ZoneId.of(city).getRules().getOffset(Instant.now());
        } catch (ZoneRulesException e) {
            ZoneOffset cachedOffset = staticTimeZoneDAO.getZoneByCity(city);
            return cachedOffset;
        }
    }

    public long calculateDifferenceInMinutes(String firstCity, String secondCity) {
        ZoneOffset firstCityOffset = getOffsetByCity(firstCity);
        ZoneOffset secondCityOffset = getOffsetByCity(secondCity);

        LocalDateTime referenceTime = LocalDateTime.now();

        OffsetDateTime firstCityTime = OffsetDateTime.of(referenceTime, firstCityOffset);
        OffsetDateTime secondCityTime = OffsetDateTime.of(referenceTime, secondCityOffset);

        return ChronoUnit.MINUTES.between(firstCityTime, secondCityTime);
    }

    private static class LazyHolder {
        private static final TimeZoneService INSTANCE = new TimeZoneService();
    }
}
