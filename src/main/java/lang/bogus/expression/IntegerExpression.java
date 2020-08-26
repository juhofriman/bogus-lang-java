package lang.bogus.expression;

import lang.bogus.lexer.token.IntToken;
import lang.bogus.runtime.BogusScope;
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
    public Value evaluate(BogusScope scope) {
        return new IntegerValue(Integer.parseInt(token.getLiteral().literal));
    }
}
