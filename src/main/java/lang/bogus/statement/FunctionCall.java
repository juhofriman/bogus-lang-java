package lang.bogus.statement;

import lang.bogus.runtime.BogusScope;
import lang.bogus.value.FunctionValue;
import lang.bogus.value.IntegerValue;
import lang.bogus.value.Value;

import java.util.LinkedList;
import java.util.List;

public class FunctionCall implements Expression {
    private final Identifier identifier;
    private final List<Expression> arguments;

    public FunctionCall(Identifier identifier, List<Expression> arguments) {
        this.identifier = identifier;
        this.arguments = arguments;
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
            for (Expression argument : this.arguments) {
                resolvedArgs.add(argument.evaluate(scope));
            }

            return fn.call(new BogusScope(scope), resolvedArgs);
        }
        System.out.println(resolve.getClass());
        return new IntegerValue(123);
    }
}
