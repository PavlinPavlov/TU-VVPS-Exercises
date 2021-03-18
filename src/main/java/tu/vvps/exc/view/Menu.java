package tu.vvps.exc.view;

import tu.vvps.exc.Dispatcher;

import java.util.Scanner;

public class Menu {

    private final Dispatcher dispatcher;
    private final Scanner scanner;

    public Menu(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        this.scanner = new Scanner(System.in);
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
                dispatcher.inputCityAndTimeZone();
            case 2:
                dispatcher.calculateDifference();
            case 3:
                dispatcher.calculatePast();
            case 4:
                dispatcher.calculateAge();
            case 5:
                dispatcher.weekdayOfBirth();
            case 6:
                dispatcher.changeTimeZone();
        }
    }
}
