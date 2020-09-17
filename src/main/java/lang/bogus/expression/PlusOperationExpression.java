package lang.bogus.expression;

import lang.bogus.runtime.BogusScope;
import lang.bogus.value.Value;

public class PlusOperationExpression implements Expression {

    private final Expression left;
    private final Expression right;

    public PlusOperationExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Value evaluate(BogusScope scope) {
        return left.evaluate(scope).applyPlus(right.evaluate(scope));
    }
}
