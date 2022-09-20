import java.util.Scanner;

public abstract class AbstractFooBar {

    public static final String FOOBAR = Value.FOO.str + Value.BAR.str;

    enum Value {
        FOO("Foo", 3), BAR("Bar", 5);

        final String str;
        final int divider;

        Value(String str, int divider) {
            this.str = str;
            this.divider = divider;
        }

        int getDivider() {
            return divider;
        }

        public String getStr() {
            return str;
        }
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter positive non zero integer");
            int n = ConsoleUtil.getNumber(scanner);

            try {
                validate(n);
                System.out.println(resolve(n));
            } catch (IllegalArgumentException ex) {
                System.err.println(ex.getMessage());
            }

            System.out.println("Repeat? (n - break)");
            char response = Character.toLowerCase(ConsoleUtil.getSingleChar(scanner));
            if (response == 'n') break;
        }
    }

    private void validate(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("The value must be positive and non zero");
    }

    public abstract String resolve(int n);
}
