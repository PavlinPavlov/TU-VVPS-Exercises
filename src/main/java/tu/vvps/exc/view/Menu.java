package tu.vvps.exc.view;

import tu.vvps.exc.Dispatcher;
import tu.vvps.exc.util.ScannerWrapper;

public class Menu {

    private final Dispatcher dispatcher;
    private final ScannerWrapper scanner;

    public Menu(Dispatcher dispatcher, ScannerWrapper scanner) {
        this.dispatcher = dispatcher;
        this.scanner = scanner;
    }

    public void printMenu() {
        System.out.println("+------------------------------------------------------------+");
        System.out.println("| 1) Enter new City with Offset.                             |");
        System.out.println("| 2) Enter two cities to calculate difference.               |");
        System.out.println("| 3) Enter past date to calculate days to now.               |");
        System.out.println("| 4) Calculate age.                                          |");
        System.out.println("| 5) Find weekday of birth.                                  |");
        System.out.println("| 6) Change your TimeZone.                                   |");
        System.out.println("| 0) Exit.                                                   |");
        System.out.println("+------------------------------------------------------------+");
    }

    public void executeChoice() {
        int choice = scanner.readInt();

        switch (choice) {
            case 1:
                dispatcher.inputCityAndTimeZone();
                break;
            case 2:
                dispatcher.calculateDifference();
                break;
            case 3:
                dispatcher.calculatePast();
                break;
            case 4:
                dispatcher.calculateAge();
                break;
            case 5:
                dispatcher.weekdayOfBirth();
                break;
            case 6:
                dispatcher.changeTimeZone();
                break;
            default:
                System.exit(0);
                break;
        }
    }
}
