package lang.bogus.value;

import lang.bogus.expression.IntegerExpression;
import lang.bogus.runtime.BogusScope;
import lang.bogus.statement.BogusStatement;
import lang.bogus.expression.Expression;
import lang.bogus.expression.IdentifierExpression;

import java.util.LinkedList;
import java.util.List;

public class FunctionValue implements Value {
    private IdentifierExpression name;
    private final List<IdentifierExpression> arguments;
    private Expression expression;
    private BogusStatement statement;

    public FunctionValue(IdentifierExpression name, List<IdentifierExpression> arguments, Expression expression) {
        this.name = name;
        this.arguments = arguments;
        this.expression = expression;
    }

    public FunctionValue(IdentifierExpression name, List<IdentifierExpression> arguments, BogusStatement statement) {
        this.name = name;
        this.arguments = arguments;
        this.statement = statement;
    }


    @Override
    public String asString() {
        return "fn " + this.name + "[" + this.arguments.size() + "]";
    }

    @Override
    public String typeString() {
        return "fn";
    }


    public Value call(BogusScope bogusScope, List<Value> callArguments) {
        if(callArguments.size() != this.arguments.size()) {
            throw new IncorrectArityException(this.name, this.arguments.size(), callArguments.size());
        }
        int i = 0;
        for (IdentifierExpression argument : this.arguments) {
            bogusScope.store(argument, callArguments.get(i));
            i++;
        }

        if(this.statement != null) {
            return this.statement.evaluate(bogusScope);
        }

        return this.expression.evaluate(bogusScope);
    }
}
