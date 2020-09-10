package lang.bogus.value;

import lang.bogus.lexer.token.StringToken;

public class StringValue implements Value {
    private StringToken string;

    public StringValue(StringToken string) {
        this.string = string;
    }

    @Override
    public String asString() {
        return this.string.getLiteral().literal;
    }

    @Override
    public String typeString() {
        return "String";
    }
}
