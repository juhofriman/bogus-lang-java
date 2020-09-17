package lang.bogus.value;

/**
 * Created by juhof on 31.7.2020.
 */
public abstract class Value {

    abstract public String asString();

    abstract String typeString();

    public Value applyPlus(Value other) {
        throw new RuntimeException("+ is not supported between " + this.typeString() + " - " + other.typeString());
    }

    public Value applyMultiplication(Value other) {
        throw new RuntimeException("* is not supported between " + this.typeString() + " - " + other.typeString());
    }

    public Value applyMinus(Value other) {
        throw new RuntimeException("- is not supported between " + this.typeString() + " - " + other.typeString());
    }

    public Value applyDivision(Value other) {
        throw new RuntimeException("/ is not supported between " + this.typeString() + " - " + other.typeString());
    }
}
