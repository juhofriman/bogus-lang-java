package lang.bogus.statement;

import lang.bogus.expression.Expression;
import lang.bogus.expression.IdentifierExpression;
import lang.bogus.runtime.BogusScope;
import lang.bogus.value.Value;

/**
 * Created by juhof on 31.7.2020.
 */
public class LetStatement implements BogusStatement {

    private final IdentifierExpression identifier;
    private final Expression expression;

    public LetStatement(IdentifierExpression identifier, Expression expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "LET " + this.identifier + " " + this.expression;
    }

    @Override
    public Value evaluate(BogusScope scope) {
        scope.store(this.identifier, this.expression.evaluate(scope));
        return scope.resolve(this.identifier);
    }

    public int bar() {
        return 1;
    }

    public void foo() {
        int a = bar() + bar();
        int c = -bar();
    }
}
