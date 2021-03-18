package tu.vvps.exc;

import tu.vvps.exc.dao.StaticTimeZoneDAO;
import tu.vvps.exc.service.TimeZoneService;
import tu.vvps.exc.view.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Dispatcher dispatcher = new Dispatcher(TimeZoneService.getInstance(), StaticTimeZoneDAO.getInstance(), new Scanner(System.in));
        Menu menu = new Menu(dispatcher);

        do {
            menu.printMenu();
            menu.executeChoice();
        } while (true);
    }
}
