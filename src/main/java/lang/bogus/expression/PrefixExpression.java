package lang.bogus.expression;

import lang.bogus.lexer.token.BogusToken;
import lang.bogus.runtime.BogusScope;
import lang.bogus.value.IntegerValue;
import lang.bogus.value.Value;

/**
 * Created by juhof on 31.7.2020.
 */
public class PrefixExpression implements Expression {

    private final BogusToken operator;
    private final Expression expression;

    public PrefixExpression(BogusToken operator, Expression expression) {
        this.operator = operator;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "PREFIX" + this.operator.getLiteral().literal + " " + this.expression.toString();
    }

    @Override
    public Value evaluate(BogusScope scope) {
        IntegerValue l = (IntegerValue) expression.evaluate(scope);
        if(this.operator.getLiteral().literal.equals("-")) {
            return new IntegerValue(-l.getValue());
        }
        return null;
    }
}
