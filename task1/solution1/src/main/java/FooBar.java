public class FooBar extends AbstractFooBar {

    @Override
    public String resolve(int n) {
        StringBuilder sb = new StringBuilder();
        int curValue = 1;

        do {
            sb.append(getFooBar(curValue));
            sb.append(" ");
            curValue++;
        } while (curValue <= n);

        return sb.toString();
    }

    private String getFooBar(int n) {
        String result = "";
        for (Value value : Value.values())
            result = n % value.getDivider() == 0 ? result + value.getStr() : result;

        return result.isEmpty() ? String.valueOf(n) : result;
    }
}
