package lang.bogus.expression;

import lang.bogus.lexer.token.BogusToken;
import lang.bogus.runtime.BogusScope;
import lang.bogus.value.Value;

/**
 * Created by juhof on 1.8.2020.
 */
public class IdentifierExpression implements Expression {

    private final String name;

    public IdentifierExpression(BogusToken token) {
        name = token.getLiteral().literal;
    }

    @Override
    public Value evaluate(BogusScope scope) {
        Value resolvedValue = scope.resolve(this);
        if(resolvedValue == null) {
            throw new NoSuchVarException(this.name);
        }
        return resolvedValue;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{" + this.name + "}";
    }
}
