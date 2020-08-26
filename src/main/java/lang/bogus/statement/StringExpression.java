package lang.bogus.statement;

import lang.bogus.lexer.StringToken;
import lang.bogus.runtime.BogusScope;
import lang.bogus.value.StringValue;
import lang.bogus.value.Value;

public class StringExpression implements Expression {
    private StringToken string;

    public StringExpression(StringToken string) {
        this.string = string;
    }

    @Override
    public Value evaluate(BogusScope scope) {
        return new StringValue(this.string);
    }
}
