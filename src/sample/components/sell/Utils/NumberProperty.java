package sample.components.sell.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberProperty<T extends Comparable<T>> {
    private T number;

    public NumberProperty(T number) {
        this.number = number;
    }

    private Matcher createMatcher(String input, String regex) {
        Pattern r = Pattern.compile(regex);
        return r.matcher(input);
    }

    public T getNumber() {
        return number;
    }

    public void setNumber(T number) {
        this.number = number;
    }
}
