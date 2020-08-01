package lang.bogus.statement;

import lang.bogus.lexer.BogusToken;
import lang.bogus.value.Value;

/**
 * Created by juhof on 31.7.2020.
 */
public class LetStatement implements BogusStatement {
    private final BogusToken identifier;
    private final Expression expression;

    public LetStatement(BogusToken identifier, Expression expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "LET " + this.identifier + " " + this.expression;
    }

    @Override
    public Value evaluate() {
        return this.expression.evaluate();
    }
}
