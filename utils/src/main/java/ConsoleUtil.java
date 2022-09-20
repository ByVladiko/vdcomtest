import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringJoiner;

public class ConsoleUtil {

    public static int getNumber(Scanner scanner) {
        int number;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a number!");
                scanner.next();
            }
            number = scanner.nextInt();
        } while (number <= 0);

        return number;
    }

    public static char getSingleChar(Scanner scanner) {
        try {
            String input = scanner.next();
            if (input.length() != 1) {
                System.err.println("The value must be single char\n");
                return getSingleChar(scanner);
            }

            return input.charAt(0);
        } catch (NoSuchElementException ex) {
            System.err.println("The value must be single char\n");
            return getSingleChar(scanner);
        }
    }
}
