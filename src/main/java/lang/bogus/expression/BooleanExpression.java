package lang.bogus.expression;

import lang.bogus.runtime.BogusScope;
import lang.bogus.value.BooleanValue;
import lang.bogus.value.Value;

public class BooleanExpression implements Expression {

    private boolean value;

    public BooleanExpression(boolean value) {
        this.value = value;
    }

    @Override
    public Value evaluate(BogusScope scope) {
        return new BooleanValue(this.value);
    }
}
