package tu.vvps.exc.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tu.vvps.exc.Dispatcher;
import tu.vvps.exc.TestConstants;
import tu.vvps.exc.dao.StaticTimeZoneDAO;
import tu.vvps.exc.service.TimeZoneService;
import tu.vvps.exc.util.ScannerWrapper;
import tu.vvps.exc.view.Menu;

import static org.mockito.Mockito.*;

public class MenuIntegrationTests {

    private Dispatcher dispatcherSpy;
    private Menu menu;
    private ScannerWrapper scannerMock;


    @BeforeEach
    private void setUp() {
        scannerMock = Mockito.mock(ScannerWrapper.class);

        Dispatcher dispatcher = new Dispatcher(TimeZoneService.getInstance(), StaticTimeZoneDAO.getInstance(), scannerMock);
        dispatcherSpy = Mockito.spy(dispatcher);
        menu = new Menu(dispatcherSpy, scannerMock);
    }

    @Test
    void givenChoice1_whenDispatching_shouldCallProperMethod() {
        // Mock
        when(scannerMock.readInt()).thenReturn(1);
        when(scannerMock.read()).thenReturn("Sofia -02:00");

        // Invoke
        menu.executeChoice();

        // Verify
        verify(dispatcherSpy, times(1)).inputCityAndTimeZone();
        verify(dispatcherSpy, times(0)).calculateDifference();
        verify(dispatcherSpy, times(0)).calculatePast();
        verify(dispatcherSpy, times(0)).calculateAge();
        verify(dispatcherSpy, times(0)).weekdayOfBirth();
        verify(dispatcherSpy, times(0)).changeTimeZone();

    }

    @Test
    void givenChoice2_whenDispatching_shouldCallProperMethod() {
        // Mock
        when(scannerMock.readInt()).thenReturn(2);
        when(scannerMock.read()).thenReturn("Europe/Sofia Europe/Madrid");

        // Invoke
        menu.executeChoice();

        // Verify
        verify(dispatcherSpy, times(0)).inputCityAndTimeZone();
        verify(dispatcherSpy, times(1)).calculateDifference();
        verify(dispatcherSpy, times(0)).calculatePast();
        verify(dispatcherSpy, times(0)).calculateAge();
        verify(dispatcherSpy, times(0)).weekdayOfBirth();
        verify(dispatcherSpy, times(0)).changeTimeZone();

    }

    @Test
    void givenChoice3_whenDispatching_shouldCallProperMethod() {
        // Mock
        when(scannerMock.readInt()).thenReturn(3);
        when(scannerMock.read()).thenReturn(TestConstants.PAST_DATE);

        // Invoke
        menu.executeChoice();

        // Verify
        verify(dispatcherSpy, times(0)).inputCityAndTimeZone();
        verify(dispatcherSpy, times(0)).calculateDifference();
        verify(dispatcherSpy, times(1)).calculatePast();
        verify(dispatcherSpy, times(0)).calculateAge();
        verify(dispatcherSpy, times(0)).weekdayOfBirth();
        verify(dispatcherSpy, times(0)).changeTimeZone();

    }

    @Test
    void givenChoice4_whenDispatching_shouldCallProperMethod() {
        // Mock
        when(scannerMock.readInt()).thenReturn(4);
        when(scannerMock.read()).thenReturn(TestConstants.PAST_DATE);

        // Invoke
        menu.executeChoice();

        // Verify
        verify(dispatcherSpy, times(0)).inputCityAndTimeZone();
        verify(dispatcherSpy, times(0)).calculateDifference();
        verify(dispatcherSpy, times(0)).calculatePast();
        verify(dispatcherSpy, times(1)).calculateAge();
        verify(dispatcherSpy, times(0)).weekdayOfBirth();
        verify(dispatcherSpy, times(0)).changeTimeZone();

    }

    @Test
    void givenChoice5_whenDispatching_shouldCallProperMethod() {
        // Mock
        when(scannerMock.readInt()).thenReturn(5);
        when(scannerMock.read()).thenReturn(TestConstants.PAST_DATE);

        // Invoke
        menu.executeChoice();

        // Verify
        verify(dispatcherSpy, times(0)).inputCityAndTimeZone();
        verify(dispatcherSpy, times(0)).calculateDifference();
        verify(dispatcherSpy, times(0)).calculatePast();
        verify(dispatcherSpy, times(0)).calculateAge();
        verify(dispatcherSpy, times(1)).weekdayOfBirth();
        verify(dispatcherSpy, times(0)).changeTimeZone();

    }

    @Test
    void givenChoice6_whenDispatching_shouldCallProperMethod() {
        // Mock
        when(scannerMock.readInt()).thenReturn(6);
        when(scannerMock.read()).thenReturn(TestConstants.TEST_OFFSET_1);

        // Invoke
        menu.executeChoice();

        // Verify
        verify(dispatcherSpy, times(0)).inputCityAndTimeZone();
        verify(dispatcherSpy, times(0)).calculateDifference();
        verify(dispatcherSpy, times(0)).calculatePast();
        verify(dispatcherSpy, times(0)).calculateAge();
        verify(dispatcherSpy, times(0)).weekdayOfBirth();
        verify(dispatcherSpy, times(1)).changeTimeZone();
    }
}
