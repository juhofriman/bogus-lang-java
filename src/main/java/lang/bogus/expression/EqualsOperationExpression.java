package lang.bogus.expression;

import lang.bogus.runtime.BogusScope;
import lang.bogus.value.BooleanValue;
import lang.bogus.value.Value;

public class EqualsOperationExpression implements Expression {

    private final Expression left;
    private final Expression right;

    public EqualsOperationExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Value evaluate(BogusScope scope) {
        // It might just be this simple
        return new BooleanValue(left.evaluate(scope).equals(right.evaluate(scope)));
    }
}
