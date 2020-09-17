package lang.bogus.value;

import lang.bogus.lexer.token.StringToken;

import java.util.Objects;

public class StringValue extends Value {
    private String string;

    @Deprecated(forRemoval = true)
    public StringValue(StringToken string) {
        this.string = string.getLiteral().literal;
    }

    public StringValue(String string) {
        this.string = string;
    }

    @Override
    public Value applyPlus(Value other) {
        if(other instanceof StringValue) {
            return new StringValue(this.asString() + other.asString());
        }
        return super.applyPlus(other);
    }

    @Override
    public Value applyMultiplication(Value other) {
        if(other instanceof IntegerValue) {
            return new StringValue(this.asString().repeat(((IntegerValue) other).getValue()));
        }
        return super.applyMultiplication(other);
    }

    @Override
    public String asString() {
        return this.string;
    }

    @Override
    public String typeString() {
        return "String";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StringValue)) return false;
        StringValue that = (StringValue) o;
        return string.equals(that.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string);
    }
}
