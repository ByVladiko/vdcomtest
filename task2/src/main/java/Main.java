import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static final int START_VALUE = 0;
    public static final String DEFAULT_FILENAME = "out.txt";

    public static void main(String[] args) {
        System.out.println("Enter positive, non zero, multiple of two integer");
        execute(new Scanner(System.in));
    }

    private static void execute(Scanner scanner) {
        int n = ConsoleUtil.getNumber(scanner);
        try {
            validate(n);
            FileUtils.createFile(getPath(), String.valueOf(START_VALUE));
            AsyncCountIncrementer countIncrementer = new AsyncCountIncrementer(getPath());
            countIncrementer.asyncIncrementValueToFile(n);
        } catch (IllegalArgumentException | IOException ex) {
            System.err.println(ex.getMessage());
            execute(scanner);
        }
    }

    private static String getPath() {
        return "task2/" + DEFAULT_FILENAME;
    }

    private static void validate(int n) {
        if (n <= 0 || n % 2 != 0)
            throw new IllegalArgumentException("Value should be positive, non zero, multiple of two integer");
    }

}
