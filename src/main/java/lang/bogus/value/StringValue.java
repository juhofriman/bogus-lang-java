package lang.bogus.value;

import lang.bogus.lexer.token.StringToken;

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
}
