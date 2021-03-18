package tu.vvps.exc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

public class StaticTimeZoneDAO {

    private static final Logger logger = LoggerFactory.getLogger(StaticTimeZoneDAO.class);

    private final Map<String, ZoneOffset> offsetCache = new HashMap<>();

    private StaticTimeZoneDAO() {
    }

    public static StaticTimeZoneDAO getInstance() {
        return StaticTimeZoneDAO.LazyHolder.INSTANCE;
    }

    public ZoneOffset getZoneByCity(String city) {
        return offsetCache.getOrDefault(city, null);
    }

    public void setZone(String city, ZoneOffset zone) {
        offsetCache.put(city, zone);
        logger.info("Caching offset for city {}: {}", city, zone);
    }

    private static class LazyHolder {
        private static final StaticTimeZoneDAO INSTANCE = new StaticTimeZoneDAO();
    }

}
