package lang.bogus.statement;

import lang.bogus.runtime.BogusScope;
import lang.bogus.value.Value;

/**
 * Created by juhof on 31.7.2020.
 */
public class LetStatement implements BogusStatement {
    private final Identifier identifier;
    private final Expression expression;

    public LetStatement(Identifier identifier, Expression expression) {
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
}
