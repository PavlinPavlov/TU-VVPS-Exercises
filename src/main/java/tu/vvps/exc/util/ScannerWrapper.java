package tu.vvps.exc.util;

import java.util.Scanner;

public class ScannerWrapper {

    private final Scanner scanner;

    public ScannerWrapper(Scanner scanner) {
        this.scanner = scanner;
    }

    public String read() {
        return scanner.nextLine();
    }

    public int readInt() {
        return Integer.parseInt(read());
    }
}
