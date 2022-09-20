public class FooBar extends AbstractFooBar {

    @Override
    public String resolve(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0) {
                if (i % 5 == 0) {
                    return FOOBAR;
                }
                return Value.FOO.getStr();
            }
            if (i % 5 == 0) {
                return Value.BAR.getStr();
            }
            sb.append(" ");
        }

        return sb.toString().trim();
    }

}
