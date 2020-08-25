package lang.bogus.statement;

import lang.bogus.runtime.BogusScope;
import lang.bogus.value.Value;

public class ReturnStatement implements BogusStatement {
    private Expression expression;

    public ReturnStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Value evaluate(BogusScope scope) {
        return this.expression.evaluate(scope);
    }
}
