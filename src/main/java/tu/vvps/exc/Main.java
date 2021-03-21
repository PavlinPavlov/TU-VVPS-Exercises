package tu.vvps.exc;

import tu.vvps.exc.dao.StaticTimeZoneDAO;
import tu.vvps.exc.service.TimeZoneService;
import tu.vvps.exc.util.ScannerWrapper;
import tu.vvps.exc.view.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ScannerWrapper scannerWrapper = new ScannerWrapper(new Scanner(System.in));

        Dispatcher dispatcher = new Dispatcher(TimeZoneService.getInstance(), StaticTimeZoneDAO.getInstance(), scannerWrapper);
        Menu menu = new Menu(dispatcher, scannerWrapper);

        do {
            menu.printMenu();
            menu.executeChoice();
        } while (true);
    }
}
