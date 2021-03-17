package tu.vvps.exc;

import tu.vvps.exc.view.Menu;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Menu menu = new Menu(new Scanner(System.in));

        do {
            menu.printMenu();
            menu.executeChoice();
        } while (true);
    }
}
