package lang.bogus.statement;

import lang.bogus.lexer.IntToken;
import lang.bogus.value.IntegerValue;
import lang.bogus.value.Value;

/**
 * Created by juhof on 31.7.2020.
 */
public class IntegerExpression implements Expression {
    private IntToken token;

    public IntegerExpression(IntToken token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "INT=" + token.getLiteral().literal;
    }

    @Override
    public Value evaluate() {
        return new IntegerValue(Integer.parseInt(token.getLiteral().literal));
    }
}
