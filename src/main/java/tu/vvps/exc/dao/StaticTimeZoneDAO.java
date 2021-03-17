package tu.vvps.exc.dao;

import java.util.HashMap;
import java.util.Map;

public class StaticTimeZoneDAO {

    private final Map<String, String> timezoneCache = new HashMap<>();

    private StaticTimeZoneDAO() {
    }

    public static StaticTimeZoneDAO getInstance() {
        return StaticTimeZoneDAO.LazyHolder.INSTANCE;
    }

    public String getZoneByCity(String city) {
        return timezoneCache.getOrDefault(city, "");
    }

    public void setZone(String city, String zone) {
        timezoneCache.put(city, zone);
    }

    private static class LazyHolder {
        private static final StaticTimeZoneDAO INSTANCE = new StaticTimeZoneDAO();
    }

}
