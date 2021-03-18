package tu.vvps.exc;

import tu.vvps.exc.dao.StaticTimeZoneDAO;
import tu.vvps.exc.service.TimeZoneService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Dispatcher {

    private final StaticTimeZoneDAO staticTimeZoneDAO;
    private final TimeZoneService timeZoneService;
    private final Scanner scanner;

    public Dispatcher(TimeZoneService timeZoneService, StaticTimeZoneDAO staticTimeZoneDAO, Scanner scanner) {
        this.staticTimeZoneDAO = staticTimeZoneDAO;
        this.timeZoneService = timeZoneService;
        this.scanner = scanner;
    }

    public void changeTimeZone() {
        System.out.println("Enter TimeZone. Example Europe/Sofia. Warning, may break app!");
        String input = scanner.nextLine();
        DateGenerator.setTimeZone(input);

        System.out.println("Current time is: " + DateGenerator.getCurrentDateTime());
    }

    public void weekdayOfBirth() {
        System.out.println("Enter Birthday. Example 1998-01-01");
        String input = scanner.nextLine();
        Date birthday = null;
        try {
            birthday = new SimpleDateFormat("yyyy-MM-dd").parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat formatter = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        System.out.println(formatter.format(birthday));
    }

    public void calculatePast() {
        System.out.println("Enter Past date. Example 1998-01-01");
        String input = scanner.nextLine();
        LocalDate pastDate = LocalDate.parse(input);
        System.out.println(ChronoUnit.DAYS.between(pastDate, LocalDate.now()));
    }

    public void calculateAge() {
        System.out.println("Enter Birthday. Example 1998-01-01");
        String input = scanner.nextLine();
        LocalDate birthday = LocalDate.parse(input);
        System.out.println(ChronoUnit.YEARS.between(birthday, LocalDate.now()));
    }

    public void calculateDifference() {
        System.out.println("Enter two cities. Example \"Europe/Sofia Europe/Madrid\"");
        String[] input = scanner.nextLine().split(" ");
        System.out.println(timeZoneService.calculateDifferenceInMinutes(input[0], input[1]));
    }

    public void inputCityAndTimeZone() {
        System.out.println("Enter City and Timezone. Example \"Sofia Europe\"");
        String[] input = scanner.nextLine().split(" ");
        staticTimeZoneDAO.setZone(input[0], input[1]);
    }
}
