package tu.vvps.exc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;
import tu.vvps.exc.dao.StaticTimeZoneDAO;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.zone.ZoneRulesException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class TimeZoneServiceTest {

    private TimeZoneService timeZoneService;
    private StaticTimeZoneDAO staticTimeZoneDAO;

    @BeforeEach
    private void setUp() throws NoSuchFieldException {
        timeZoneService = TimeZoneService.getInstance();
        staticTimeZoneDAO = Mockito.mock(StaticTimeZoneDAO.class);

        new FieldSetter(timeZoneService, timeZoneService.getClass().getDeclaredField("staticTimeZoneDAO")).set(staticTimeZoneDAO);
    }

    @Test
    void whenGettingInstance_shouldBeTheSame() {
        TimeZoneService otherReference = TimeZoneService.getInstance();

        assertSame(timeZoneService, otherReference);
    }

    @ParameterizedTest
    @CsvSource({"Europe/London, Europe/Sofia, 120",
            "Europe/Sofia, Europe/London, 120",
            "Asia/Tehran, Europe/Sofia, 90",
    })
    void whenGivenTwoCities_shouldCalculateMinuteDifference(String city1, String city2, long expectedMinutes) {
        long computedMinutes = timeZoneService.calculateDifferenceInMinutes(city1, city2);

        assertEquals(expectedMinutes, computedMinutes);
    }

    @Test
    void givenValidZone_whenRetrievingOffset_shouldReturnAndNotCallCache() {
        String city = "Europe/Sofia";
        ZoneOffset expectedOffset = ZoneId.of(city).getRules().getOffset(Instant.now());

        ZoneOffset returnedOffset = timeZoneService.getOffsetByCity(city);

        assertEquals(expectedOffset.getId(), returnedOffset.getId());
        Mockito.verifyNoMoreInteractions(staticTimeZoneDAO);
    }

    @Test
    void givenCachedZone_whenRetrievingOffset_shouldReturnAndCallCache() {
        //Set up
        String city = "Vidin";
        ZoneOffset zoneOffset = ZoneOffset.UTC;

        //Mock
        Mockito.when(staticTimeZoneDAO.getZoneByCity(city)).thenReturn(zoneOffset);

        //Invocation
        ZoneOffset returnedOffset = timeZoneService.getOffsetByCity(city);

        //Assertions
        assertEquals(zoneOffset.getId(), returnedOffset.getId());
        Mockito.verify(staticTimeZoneDAO, times(1)).getZoneByCity(city);
    }

    @Test
    void givenInvalidZone_whenRetrievingOffset_shouldThrow() {
        //Set up
        String city = "Dobrich";

        //Mock
        Mockito.when(staticTimeZoneDAO.getZoneByCity(city)).thenReturn(null);

        //Invocation
        Throwable exc = assertThrows(ZoneRulesException.class,
                () -> timeZoneService.getOffsetByCity(city));

        //Assertions
        assertEquals(ZoneRulesException.class, exc.getClass());
        Mockito.verify(staticTimeZoneDAO, times(1)).getZoneByCity(city);
    }

}