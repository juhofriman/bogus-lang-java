package lang.bogus.value;

import lang.bogus.expression.IdentifierExpression;

import java.util.LinkedList;
import java.util.List;

public class ValueTest {

    protected List<IdentifierExpression> identifiers(IdentifierExpression... bindings) {
        LinkedList<IdentifierExpression> vals = new LinkedList<>();
        for (IdentifierExpression value : bindings) {
            vals.add(value);
        }
        return vals;
    }

    protected List<Value> values(Value... values) {
        LinkedList<Value> vals = new LinkedList<>();
        for (Value value : values) {
            vals.add(value);
        }
        return vals;
    }
}
