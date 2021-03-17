package tu.vvps.exc.dao;

import java.util.HashMap;
import java.util.Map;

public class StaticTimeZoneDAO {

    private final Map<String, String> timezones = new HashMap<>();

    private StaticTimeZoneDAO() {
    }

    public static StaticTimeZoneDAO getInstance() {
        return StaticTimeZoneDAO.LazyHolder.INSTANCE;
    }

    public String getZoneByCity(String city) {
        return timezones.getOrDefault(city, "");
    }

    public void setZone(String city, String zone) {
        timezones.put(city, zone);
    }

    private static class LazyHolder {
        private static final StaticTimeZoneDAO INSTANCE = new StaticTimeZoneDAO();
    }

}
