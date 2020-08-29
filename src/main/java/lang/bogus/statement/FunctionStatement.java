package lang.bogus.statement;

import lang.bogus.expression.Expression;
import lang.bogus.expression.IdentifierExpression;
import lang.bogus.runtime.BogusScope;
import lang.bogus.value.FunctionValue;
import lang.bogus.value.Value;

import java.util.List;

public class FunctionStatement implements BogusStatement {

    private final IdentifierExpression identifier;
    private final List<IdentifierExpression> arguments;
    private Expression expression;
    private BogusStatement statement;

    public FunctionStatement(IdentifierExpression identifier, List<IdentifierExpression> arguments, Expression expression) {
        this.identifier = identifier;
        this.arguments = arguments;
        this.expression = expression;
    }

    public FunctionStatement(IdentifierExpression identifier, List<IdentifierExpression> arguments, BogusStatement statement) {
        this.identifier = identifier;
        this.arguments = arguments;
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "FunctionStatement{" +
                "identifier=" + identifier +
                ", arguments=" + arguments +
                ", expression=" + expression +
                ", statement=" + statement +
                '}';
    }

    @Override
    public Value evaluate(BogusScope scope) {
        FunctionValue fn = null;
        if(this.expression != null) {
            fn = new FunctionValue(this.identifier, this.arguments, this.expression);
            scope.store(this.identifier, new FunctionValue(this.identifier, this.arguments, this.expression));
        }
        if(this.statement != null) {
            fn = new FunctionValue(this.identifier, this.arguments, this.statement, scope);

        }
        scope.store(this.identifier, fn);
        return fn;
    }
}
