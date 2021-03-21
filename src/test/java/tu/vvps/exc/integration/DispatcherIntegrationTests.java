package tu.vvps.exc.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tu.vvps.exc.Dispatcher;
import tu.vvps.exc.TestConstants;
import tu.vvps.exc.dao.StaticTimeZoneDAO;
import tu.vvps.exc.service.TimeZoneService;
import tu.vvps.exc.util.DateGenerator;
import tu.vvps.exc.util.ScannerWrapper;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DispatcherIntegrationTests {


    private Dispatcher dispatcher;
    private ScannerWrapper scannerMock;
    private StaticTimeZoneDAO staticTimeZoneDAO;

    @BeforeEach
    private void setUp() {
        scannerMock = Mockito.mock(ScannerWrapper.class);

        dispatcher = new Dispatcher(TimeZoneService.getInstance(), StaticTimeZoneDAO.getInstance(), scannerMock);
        staticTimeZoneDAO = StaticTimeZoneDAO.getInstance();

        staticTimeZoneDAO.clearCache();
    }

    @Test
    void givenCityAndTimezone_whenSearching_shouldFind() {
        when(scannerMock.read()).thenReturn(TestConstants.TEST_CITY_1 + " " + TestConstants.TEST_OFFSET_1);

        dispatcher.inputCityAndTimeZone();

        ZoneOffset oklahoma = staticTimeZoneDAO.getZoneByCity(TestConstants.TEST_CITY_1);
        assertEquals(TestConstants.TEST_OFFSET_1, oklahoma.getId());
    }

    @Test
    void givenTwoCities_whenCalculatingDifference_shouldCalculate() {

        // Set up
        staticTimeZoneDAO.setZone(TestConstants.TEST_CITY_1, ZoneOffset.of(TestConstants.TEST_OFFSET_1));
        staticTimeZoneDAO.setZone(TestConstants.TEST_CITY_2, ZoneOffset.of(TestConstants.TEST_OFFSET_2));

        // Mock
        when(scannerMock.read()).thenReturn(TestConstants.TEST_CITY_1 + " " + TestConstants.TEST_CITY_2);

        // Invoke
        long days = dispatcher.calculateDifference();

        assertEquals(TestConstants.MINUTES_DIFF, days);
    }

    @Test
    void givenPastDate_whenCalculatingTime_shouldCalculate() {
        long expectedDays = ChronoUnit.DAYS.between(LocalDate.parse(TestConstants.PAST_DATE), LocalDate.now());

        // Mock
        when(scannerMock.read()).thenReturn(TestConstants.PAST_DATE);

        // Invoke
        long days = dispatcher.calculatePast();

        assertEquals(expectedDays, days);
    }

    @Test
    void givenPastBirthdayDate_whenCalculatingAge_shouldCalculate() {
        long expectedDays = ChronoUnit.YEARS.between(LocalDate.parse(TestConstants.PAST_DATE), LocalDate.now());

        // Mock
        when(scannerMock.read()).thenReturn(TestConstants.PAST_DATE);

        // Invoke
        long years = dispatcher.calculateAge();

        assertEquals(expectedDays, years);
    }

    @Test
    void givenPastBirthdayDate_whenFindingDayOgBirth_shouldFind() {
        String expectedDay = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(Date.valueOf(TestConstants.PAST_DATE));

        // Mock
        when(scannerMock.read()).thenReturn(TestConstants.PAST_DATE);

        // Invoke
        String returnedDay = dispatcher.weekdayOfBirth();

        assertEquals(expectedDay, returnedDay);
    }

    @Test
    void givenOffset_whenChangingSystemOffset_shouldChange() {
        // Mock
        when(scannerMock.read()).thenReturn(TestConstants.TEST_OFFSET_1);

        // Invoke
        dispatcher.changeTimeZone();

        ZoneOffset currentZoneOffset = DateGenerator.getCurrentDateTime().getOffset();
        ZoneOffset expectedZoneOffset = ZoneOffset.of(TestConstants.TEST_OFFSET_1);

        assertEquals(expectedZoneOffset.getId(), currentZoneOffset.getId());
    }
}
