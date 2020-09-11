package lang.bogus.expression;

import lang.bogus.lexer.RawLiteral;
import lang.bogus.lexer.token.BogusToken;
import lang.bogus.lexer.token.StringToken;
import lang.bogus.runtime.BogusScope;
import lang.bogus.value.IntegerValue;
import lang.bogus.value.StringValue;
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
        if(left == null) {
            throw new IllegalArgumentException("Left expression is null");
        }
        if(right == null) {
            throw new IllegalArgumentException("Right expression is null");
        }
        this.token = token;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "{" + this.left.toString() + " " + this.token.getLiteral().literal + " " + this.right.toString() + "}";
    }

    @Override
    public Value evaluate(BogusScope scope) {

        // Trollface
        if(left instanceof IntegerExpression && right instanceof StringExpression) {
            if(this.token.getLiteral().literal.equals("*")) {
                StringBuffer stringBuffer = new StringBuffer();
                for(int i = 0; i < ((IntegerValue) left.evaluate(scope)).getValue(); i++) {
                    stringBuffer.append(right.evaluate(scope).asString());
                }
                return new StringValue(new StringToken(new RawLiteral(-1, -1, stringBuffer.toString())));
            }
        }

        IntegerValue l = (IntegerValue) left.evaluate(scope);
        IntegerValue r = (IntegerValue) right.evaluate(scope);
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
