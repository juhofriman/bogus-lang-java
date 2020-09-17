package lang.bogus.value;

import java.util.Objects;

/**
 * Created by juhof on 31.7.2020.
 */
public class IntegerValue extends Value {

    private int value;

    public IntegerValue(int value) {
        this.value = value;
    }

    @Override
    public String asString() {
        return String.valueOf(this.value);
    }

    @Override
    public String typeString() {
        return "int";
    }

    public int getValue() {
        return value;
    }

    @Override
    public Value applyPlus(Value other) {
        if(other instanceof IntegerValue) {
            return new IntegerValue(this.value + ((IntegerValue) other).value);
        }
        if(other instanceof StringValue) {
            return new StringValue(this.asString() + other.asString());
        }
        return super.applyPlus(other);
    }

    @Override
    public Value applyMinus(Value other) {
        if(other instanceof IntegerValue) {
            return new IntegerValue(this.value - ((IntegerValue) other).value);
        }
        return super.applyMinus(other);
    }

    @Override
    public Value applyDivision(Value other) {
        if(other instanceof IntegerValue) {
            return new IntegerValue(this.value / ((IntegerValue) other).value);
        }
        return super.applyDivision(other);
    }

    @Override
    public Value applyMultiplication(Value other) {
        if(other instanceof IntegerValue) {
            return new IntegerValue(this.value * ((IntegerValue) other).value);
        }
        if(other instanceof StringValue) {
            return new StringValue(other.asString().repeat(this.value));
        }
        return super.applyMultiplication(other);
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
