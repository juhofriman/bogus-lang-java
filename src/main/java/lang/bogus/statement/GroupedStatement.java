package lang.bogus.statement;

import lang.bogus.runtime.BogusScope;
import lang.bogus.value.Value;

import java.util.List;

public class GroupedStatement implements BogusStatement {
    private List<BogusStatement> expressions;

    public GroupedStatement(List<BogusStatement> statements) {
        this.expressions = statements;
    }

    @Override
    public Value evaluate(BogusScope scope) {
        Value evaluate = null;
        for (BogusStatement expression : this.expressions) {
            evaluate = expression.evaluate(scope);
            if(expression instanceof ReturnStatement) {
                break;
            }
        }
        return evaluate;
    }

    @Override
    public String toString() {
        return "GroupedStatement{" +
                "expressions=" + expressions +
                '}';
    }
}
