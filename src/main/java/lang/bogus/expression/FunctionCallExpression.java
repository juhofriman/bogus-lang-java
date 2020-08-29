package lang.bogus.expression;

import lang.bogus.runtime.BogusScope;
import lang.bogus.value.FunctionValue;
import lang.bogus.value.Value;

import java.util.LinkedList;
import java.util.List;

public class FunctionCallExpression implements Expression {
    private final IdentifierExpression identifier;
    private final List<Expression> arguments;

    public FunctionCallExpression(IdentifierExpression identifier, List<Expression> arguments) {
        this.identifier = identifier;
        this.arguments = arguments;
    }

    public List<Expression> getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        return "fnCall " + this.identifier + "(" + this.arguments + ")";
    }

    @Override
    public Value evaluate(BogusScope scope) {
        Value resolve = scope.resolve(this.identifier);
        if(resolve == null) {
            throw new RuntimeException("No fn " + this.identifier + " in scope");
        }
        if(resolve instanceof FunctionValue) {

            FunctionValue fn = (FunctionValue) resolve;
            LinkedList<Value> resolvedArgs = new LinkedList<>();
            if(this.arguments != null) {
                for (Expression argument : this.arguments) {
                    resolvedArgs.add(argument.evaluate(scope));
                }
            }
            return fn.call(new BogusScope(scope), resolvedArgs);
        }
        // TODO: maybe throw?
        return resolve;
    }
}
