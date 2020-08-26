package lang.bogus.value;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntegerValue)) return false;
        IntegerValue that = (IntegerValue) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
