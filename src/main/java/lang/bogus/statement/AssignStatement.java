package lang.bogus.statement;

import lang.bogus.expression.Expression;
import lang.bogus.expression.IdentifierExpression;
import lang.bogus.lexer.token.IdentifierToken;
import lang.bogus.runtime.BogusScope;
import lang.bogus.value.Value;
import lang.bogus.value.VoidValue;

public class AssignStatement implements BogusStatement {

    private final IdentifierExpression identifier;
    private final Expression expression;

    public AssignStatement(IdentifierExpression identifier, Expression expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "AssignStatement{" +
                "identifier=" + identifier +
                ", expression=" + expression +
                '}';
    }

    @Override
    public Value evaluate(BogusScope scope) {
        if(!scope.has(this.identifier)) {
            throw new RuntimeException("Can't assign to undefined identifier=" + this.identifier);
        }
        scope.store(this.identifier, this.expression.evaluate(scope));
        return VoidValue.VOID;
    }
}
