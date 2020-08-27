package lang.bogus.value;

import lang.bogus.expression.IdentifierExpression;
import lang.bogus.expression.IntegerExpression;
import lang.bogus.lexer.RawLiteral;
import lang.bogus.lexer.token.IntToken;
import lang.bogus.runtime.BogusScope;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class FunctionValueTest extends ValueTest {

    @Test
    public void testCall() {
        IntegerExpression ie = new IntegerExpression(new IntToken(new RawLiteral(1, 2, "1")));
        FunctionValue fn = new FunctionValue(new IdentifierExpression("x"),
                identifiers(),
                ie);

        Value producedValue = fn.call(new BogusScope(), values());
        assertEquals(ie.evaluate(new BogusScope()), producedValue);
    }

    @Test(expected = IncorrectArityException.class)
    public void testCallTooFewArguments() {
        IntegerExpression ie = new IntegerExpression(new IntToken(new RawLiteral(1, 2, "1")));
        FunctionValue fn = new FunctionValue(new IdentifierExpression("x"),
                identifiers(new IdentifierExpression("a")),
                ie);

        Value producedValue = fn.call(new BogusScope(), values());
        assertEquals(ie.evaluate(new BogusScope()), producedValue);
    }

    @Test(expected = IncorrectArityException.class)
    public void testCallTooManyArguments() {
        IntegerExpression ie = new IntegerExpression(new IntToken(new RawLiteral(1, 2, "1")));
        FunctionValue fn = new FunctionValue(new IdentifierExpression("x"),
                identifiers(new IdentifierExpression("a")),
                ie);

        Value producedValue = fn.call(new BogusScope(), values(new IntegerValue(1), new IntegerValue(2)));
        assertEquals(ie.evaluate(new BogusScope()), producedValue);
    }
}
