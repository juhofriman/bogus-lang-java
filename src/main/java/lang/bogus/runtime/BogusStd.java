package lang.bogus.runtime;

import lang.bogus.expression.Expression;
import lang.bogus.expression.IdentifierExpression;
import lang.bogus.value.FunctionValue;
import lang.bogus.value.IntegerValue;
import lang.bogus.value.TypeValue;
import lang.bogus.value.Value;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BogusStd {

    public static Map<String, Value> create() {
        HashMap<String, Value> registry = new HashMap<>();
        registry.put("type", createTypeFn());
        registry.put("print_scope", createPrintScopeFn());
        return registry;
    }

    private static FunctionValue createTypeFn() {
        LinkedList<IdentifierExpression> argBinding = new LinkedList<>();
        argBinding.add(new IdentifierExpression("object"));
        return new FunctionValue(new IdentifierExpression("type"), argBinding,
                new Expression() {
                    @Override
                    public Value evaluate(BogusScope scope) {
                        return new TypeValue(scope.resolve(new IdentifierExpression("object")));
                    }
                });
    }

    private static FunctionValue createPrintScopeFn() {
        LinkedList<IdentifierExpression> argBinding = new LinkedList<>();
        return new FunctionValue(new IdentifierExpression("print_scope"), argBinding,
                new Expression() {
                    @Override
                    public Value evaluate(BogusScope scope) {
                        scope.echoScope(1);
                        return new IntegerValue(1);
                    }
                });
    }
}
