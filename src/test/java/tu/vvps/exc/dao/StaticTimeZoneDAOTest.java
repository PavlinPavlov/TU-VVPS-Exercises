package unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tu.vvps.exc.dao.StaticTimeZoneDAO;

import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class StaticTimeZoneDAOTest {

    private StaticTimeZoneDAO staticTimeZoneDAO;

    @BeforeEach
    private void setUp() {
        staticTimeZoneDAO = StaticTimeZoneDAO.getInstance();
    }

    @Test
    void whenGettingInstance_shouldBeTheSame() {
        StaticTimeZoneDAO otherReference = StaticTimeZoneDAO.getInstance();

        assertSame(staticTimeZoneDAO, otherReference);
    }

    @Test
    void whenSettingNewZone_shouldCache() {
        String zoneId = "Blagoevgrad";
        ZoneOffset zoneOffsetToTest = ZoneOffset.of("+02:00");

        staticTimeZoneDAO.setZone(zoneId, zoneOffsetToTest);

        ZoneOffset cachedOffset = staticTimeZoneDAO.getZoneByCity(zoneId);

        assertEquals(zoneOffsetToTest.getId(), cachedOffset.getId());
    }
}
