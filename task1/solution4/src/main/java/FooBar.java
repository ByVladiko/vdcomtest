import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FooBar extends AbstractFooBar {

    @Override
    public String resolve(int n) {
        return IntStream.rangeClosed(1, n)
                .mapToObj(val -> val % 3 == 0 ? (val % 5 == 0 ? FOOBAR : Value.FOO.getStr())
                        : (val % 5 == 0 ? Value.BAR.getStr() : String.valueOf(val)))
                .collect(Collectors.joining(" "));
    }

}
