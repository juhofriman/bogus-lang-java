package lang.bogus.value;

/**
 * Created by juhof on 31.7.2020.
 */
public class IntegerValue implements Value {

    private int value;

    public IntegerValue(int value) {
        this.value = value;
    }

    @Override
    public String asString() {
        return String.valueOf(this.value);
    }

    public int getValue() {
        return value;
    }
}
