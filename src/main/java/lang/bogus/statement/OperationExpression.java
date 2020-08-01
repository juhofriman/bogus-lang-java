package lang.bogus.statement;

import lang.bogus.lexer.BogusToken;
import lang.bogus.value.IntegerValue;
import lang.bogus.value.Value;

/**
 * Created by juhof on 31.7.2020.
 */
public class OperationExpression implements Expression {
    // TODO this shouldn't be token...
    private final BogusToken token;
    private final Expression left;
    private final Expression right;

    public OperationExpression(BogusToken token, Expression left, Expression right) {
        this.token = token;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "{" + this.left.toString() + " " + this.token.getLiteral().literal + " " + this.right.toString() + "}";
    }

    @Override
    public Value evaluate() {
        IntegerValue l = (IntegerValue) left.evaluate();
        IntegerValue r = (IntegerValue) right.evaluate();
        if(this.token.getLiteral().literal.equals("+")) {
            return new IntegerValue(l.getValue() + r.getValue());
        } else if (this.token.getLiteral().literal.equals("-")) {
            return new IntegerValue(l.getValue() - r.getValue());
        } else if (this.token.getLiteral().literal.equals("*")) {
            return new IntegerValue(l.getValue() * r.getValue());
        } else if (this.token.getLiteral().literal.equals("/")) {
            return new IntegerValue(l.getValue() / r.getValue());
        }

        return null;
    }
}
