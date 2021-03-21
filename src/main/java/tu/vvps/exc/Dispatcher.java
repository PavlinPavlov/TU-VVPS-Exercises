package tu.vvps.exc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tu.vvps.exc.dao.StaticTimeZoneDAO;
import tu.vvps.exc.service.TimeZoneService;
import tu.vvps.exc.util.DateGenerator;
import tu.vvps.exc.util.ScannerWrapper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class Dispatcher {

    private static final Logger logger = LoggerFactory.getLogger(Dispatcher.class);

    private final StaticTimeZoneDAO staticTimeZoneDAO;
    private final TimeZoneService timeZoneService;
    private final ScannerWrapper scanner;

    public Dispatcher(TimeZoneService timeZoneService, StaticTimeZoneDAO staticTimeZoneDAO, ScannerWrapper scanner) {
        this.staticTimeZoneDAO = staticTimeZoneDAO;
        this.timeZoneService = timeZoneService;
        this.scanner = scanner;
    }

    public void changeTimeZone() {
        System.out.println("Enter offset. Example \"+02:30\"");
        String input = scanner.read();
        DateGenerator.setTimeZone(input);

        logger.info("Current time is: {}", DateGenerator.getCurrentDateTime());
    }

    public void weekdayOfBirth() {
        System.out.println("Enter Birthday. Example \"1998-01-01\"");
        String input = scanner.read();

        LocalDate pastDate = LocalDate.parse(input);

        if (!isPastDate(pastDate)) {
            logger.error("Entered date: {} is no a past date", pastDate);
            return;
        }

        Date birthday = null;
        try {
            birthday = new SimpleDateFormat("yyyy-MM-dd").parse(input);
        } catch (ParseException e) {
            logger.error("Error in input!", e);
        }
        DateFormat formatter = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        logger.info(formatter.format(birthday));
    }

    public void calculatePast() {
        System.out.println("Enter Past date. \"Example 1998-01-01\"");
        String input = scanner.read();
        LocalDate pastDate = LocalDate.parse(input);

        if (!isPastDate(pastDate)) {
            logger.error("Entered date: {} is no a past date", pastDate);
            return;
        }

        logger.info("{} days ago.", ChronoUnit.DAYS.between(pastDate, LocalDate.now()));
    }

    public void calculateAge() {
        System.out.println("Enter Birthday. Example 1998-01-01");
        String input = scanner.read();
        LocalDate birthday = LocalDate.parse(input);

        if (!isPastDate(birthday)) {
            logger.error("Entered date: {} is no a past date", birthday);
            return;
        }

        logger.info("{} years.", ChronoUnit.YEARS.between(birthday, LocalDate.now()));
    }

    public void calculateDifference() {
        System.out.println("Enter two cities. Example \"Europe/Sofia Europe/Madrid\"");
        String[] input = scanner.read().split(" ");
        long minutesApart = timeZoneService.calculateDifferenceInMinutes(input[0], input[1]);
        logger.info("The two cities have {} min. difference", minutesApart);
    }

    public void inputCityAndTimeZone() {
        System.out.println("Enter City and Offset. Example \"Sofia -02:00\"");
        String[] input = scanner.read().split(" ");
        staticTimeZoneDAO.setZone(input[0], ZoneOffset.of(input[1]));
    }

    private boolean isPastDate(LocalDate dateToCheck) {
        LocalDate now = LocalDate.now();
        return dateToCheck.isBefore(now);
    }

}
