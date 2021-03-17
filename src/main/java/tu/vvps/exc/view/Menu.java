package tu.vvps.exc.view;

import tu.vvps.exc.DateGenerator;
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

public class Menu {

    private final Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void printMenu() {
        System.out.println("+------------------------------------------------------------+");
        System.out.println("| 1) Enter new City with TimeZone.                           |");
        System.out.println("| 2) Enter two cities to calculate difference.               |");
        System.out.println("| 3) Enter past date to calculate days to now.               |");
        System.out.println("| 4) Calculate age.                                          |");
        System.out.println("| 5) Find weekday of birth.                                  |");
        System.out.println("| 6) Change your TimeZone.                                   |");
        System.out.println("| 0) Exit.                                                   |");
        System.out.println("+------------------------------------------------------------+");
    }

    public void executeChoice() {
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 0:
                System.exit(0);
            case 1:
                inputCityAndTimeZone();
            case 2:
                calculateDifference();
            case 3:
                calculatePast();
            case 4:
                calculateAge();
            case 5:
                weekdayOfBirth();
            case 6:
                changeTimeZone();
        }
    }

    private void changeTimeZone() {
        System.out.println("Enter TimeZone. Example Europe/Sofia. Warning, may break app!");
        String input = scanner.nextLine();
        DateGenerator.setTimeZone(input);

        System.out.println("Current time is: " + DateGenerator.getCurrentDateTime());
    }

    private void weekdayOfBirth() {
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

    private void calculatePast() {
        System.out.println("Enter Past date. Example 1998-01-01");
        String input = scanner.nextLine();
        LocalDate pastDate = LocalDate.parse(input);
        System.out.println(ChronoUnit.DAYS.between(pastDate, LocalDate.now()));
    }

    private void calculateAge() {
        System.out.println("Enter Birthday. Example 1998-01-01");
        String input = scanner.nextLine();
        LocalDate birthday = LocalDate.parse(input);
        System.out.println(ChronoUnit.YEARS.between(birthday, LocalDate.now()));
    }

    private void calculateDifference() {
        System.out.println("Enter two cities. Example \"Europe/Sofia Europe/Madrid\"");
        String[] input = scanner.nextLine().split(" ");
        System.out.println(TimeZoneService.getInstance().calculateDifferenceInMinutes(input[0], input[1]));
    }

    private void inputCityAndTimeZone() {
        System.out.println("Enter City and Timezone. Example \"Sofia Europe\"");
        String[] input = scanner.nextLine().split(" ");
        StaticTimeZoneDAO.getInstance().setZone(input[0], input[1]);
    }
}
