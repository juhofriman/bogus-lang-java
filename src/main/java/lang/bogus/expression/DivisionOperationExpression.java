package lang.bogus.expression;

import lang.bogus.runtime.BogusScope;
import lang.bogus.value.Value;

public class DivisionOperationExpression implements Expression {

    private final Expression left;
    private final Expression right;

    public DivisionOperationExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Value evaluate(BogusScope scope) {
        return left.evaluate(scope).applyDivision(right.evaluate(scope));
    }
}
