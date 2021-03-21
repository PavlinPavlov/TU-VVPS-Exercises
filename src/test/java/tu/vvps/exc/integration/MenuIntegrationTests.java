package tu.vvps.exc.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tu.vvps.exc.Dispatcher;
import tu.vvps.exc.dao.StaticTimeZoneDAO;
import tu.vvps.exc.service.TimeZoneService;
import tu.vvps.exc.util.ScannerWrapper;
import tu.vvps.exc.view.Menu;

import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class IntegrationTests {

    private static final String TEST_OFFSET_1 = "-04:00";
    private static final String TEST_OFFSET_2 = "+02:00";
    private static final String TEST_CITY_1 = "Oklahoma";
    private static final String TEST_CITY_2 = "Gabrovo";

    private Dispatcher dispatcher;
    private Menu menu;
    private ScannerWrapper scannerMock;
    private StaticTimeZoneDAO staticTimeZoneDAO;
    private TimeZoneService timeZoneService;

    @BeforeEach
    private void setUp() {
        scannerMock = Mockito.mock(ScannerWrapper.class);

        dispatcher = new Dispatcher(TimeZoneService.getInstance(), StaticTimeZoneDAO.getInstance(), scannerMock);
        menu = new Menu(dispatcher, scannerMock);
        staticTimeZoneDAO = StaticTimeZoneDAO.getInstance();
        timeZoneService = TimeZoneService.getInstance();

        staticTimeZoneDAO.clearCache();
    }

    @Test
    void givenCityAndTimezone_whenSearching_shouldFind() {
        when(scannerMock.readInt()).thenReturn(1);
        when(scannerMock.read()).thenReturn(TEST_CITY_1 + " " + TEST_OFFSET_1);

        menu.executeChoice();

        ZoneOffset oklahoma = staticTimeZoneDAO.getZoneByCity(TEST_CITY_1);
        assertEquals(TEST_OFFSET_1, oklahoma.getId());
    }

    @Test
    void givenTwoCities_whenCalculatingDifference_shouldCalculate() {

//        // Set up
//        staticTimeZoneDAO.setZone(TEST_CITY_1, ZoneOffset.of(TEST_OFFSET_1));
//        staticTimeZoneDAO.setZone(TEST_CITY_2, ZoneOffset.of(TEST_OFFSET_2));
//
//        // Mock
//        when(scannerMock.readInt()).thenReturn(2);
//        when(scannerMock.read()).thenReturn(TEST_CITY_1 + " " + TEST_CITY_2);
//
//        menu.executeChoice();
//
//
//        assertEquals(TEST_OFFSET_1, oklahoma.getId());
    }


}
