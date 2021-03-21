package tu.vvps.exc.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tu.vvps.exc.Dispatcher;
import tu.vvps.exc.TestConstants;
import tu.vvps.exc.dao.StaticTimeZoneDAO;
import tu.vvps.exc.service.TimeZoneService;
import tu.vvps.exc.util.ScannerWrapper;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DispatcherTest {

    private Dispatcher dispatcher;
    private StaticTimeZoneDAO staticTimeZoneDAO;
    private TimeZoneService timeZoneService;
    private ScannerWrapper scanner;

    @BeforeEach
    private void setUp() {
        staticTimeZoneDAO = Mockito.mock(StaticTimeZoneDAO.class);
        timeZoneService = Mockito.mock(TimeZoneService.class);
        scanner = Mockito.mock(ScannerWrapper.class);

        dispatcher = new Dispatcher(timeZoneService, staticTimeZoneDAO, scanner);
    }

    @Test
    void givenPastBirthdayDate_whenCalculatingAge_shouldCalculate() {

        String expectedDay = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(Date.valueOf(TestConstants.PAST_DATE));

        // Mock
        when(scanner.read()).thenReturn(TestConstants.PAST_DATE);

        // Invoke
        String returnedDay = dispatcher.weekdayOfBirth();

        assertEquals(expectedDay, returnedDay);
    }
}
