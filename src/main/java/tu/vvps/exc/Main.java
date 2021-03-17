package tu.vvps.exc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tu.vvps.exc.dao.StaticTimeZoneDAO;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Logger logger = LoggerFactory.getLogger(Main.class);

        TimeZoneService timeZoneService = TimeZoneService.getInstance();
        StaticTimeZoneDAO zoneDAO = StaticTimeZoneDAO.getInstance();

        zoneDAO.setZone("Mexico_City", "America");

        System.out.println(timeZoneService.getByCity("Mexico_City"));
    }
}
