public class FooBar extends AbstractFooBar {

    @Override
    public String resolve(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                sb.append(Value.FOO.getStr()).append(Value.BAR.getStr());
            } else if (i % Value.FOO.getDivider() == 0) {
                sb.append(Value.FOO.getStr());
            } else if (i % Value.BAR.getDivider() == 0) {
                sb.append(Value.BAR.getStr());
            } else {
                sb.append(i);
            }
            sb.append(" ");
        }

        return sb.toString().trim();
    }
}
