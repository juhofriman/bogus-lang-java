package lang.bogus.parser;

import lang.bogus.expression.IdentifierExpression;
import lang.bogus.expression.IntegerExpression;
import lang.bogus.expression.OperationExpression;
import lang.bogus.expression.StringExpression;
import lang.bogus.lexer.BogusLexer;
import lang.bogus.lexer.token.IdentifierToken;
import lang.bogus.lexer.RawLiteral;
import lang.bogus.runtime.BogusScope;
import lang.bogus.statement.*;
import lang.bogus.value.FunctionValue;
import lang.bogus.value.IntegerValue;
import lang.bogus.value.Value;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

interface StatementAssertionFunction {
    void run(List<BogusStatement> statements);
}

public class BogusParserTest {

    @Test
    public void testLiteralExpressions() {
        assertParsing("1", 1, (List<BogusStatement> statements) -> {
            assertEquals(IntegerExpression.class, statements.get(0).getClass());
        });

        assertParsing("\"String\"", 1, (List<BogusStatement> statements) -> {
            assertEquals(StringExpression.class, statements.get(0).getClass());
        });
    }

    @Test
    public void testArithmeticExpressions() {
        assertParsing("1 + 1", 1, (List<BogusStatement> statements) -> {
            assertEquals(OperationExpression.class, statements.get(0).getClass());
            assertEquals(new IntegerValue(2), statements.get(0).evaluate(new BogusScope()));
        });

        assertParsing("(1 + 1)", 1, (List<BogusStatement> statements) -> {
            assertEquals(OperationExpression.class, statements.get(0).getClass());
            assertEquals(new IntegerValue(2), statements.get(0).evaluate(new BogusScope()));
        });

        assertParsing("(1) + (1)", 1, (List<BogusStatement> statements) -> {
            assertEquals(OperationExpression.class, statements.get(0).getClass());
            assertEquals(new IntegerValue(2), statements.get(0).evaluate(new BogusScope()));
        });

        assertParsing("(1 + 1) + (1)", 1, (List<BogusStatement> statements) -> {
            assertEquals(OperationExpression.class, statements.get(0).getClass());
            assertEquals(new IntegerValue(3), statements.get(0).evaluate(new BogusScope()));
        });

        assertParsing("(1 + (1 + 1)) + (1)", 1, (List<BogusStatement> statements) -> {
            assertEquals(OperationExpression.class, statements.get(0).getClass());
            assertEquals(new IntegerValue(4), statements.get(0).evaluate(new BogusScope()));
        });

        assertParsing("(((1 + (1 + 1)) + (1 + (1 + 1 + (1 + 1))))", 1, (List<BogusStatement> statements) -> {
            assertEquals(OperationExpression.class, statements.get(0).getClass());
            assertEquals(new IntegerValue(8), statements.get(0).evaluate(new BogusScope()));
        });
    }

    @Test
    public void testFunctionDefinition() {
        assertParsing("fun x(a) = a", 1, (List<BogusStatement> statements) -> {
            assertEquals(FunctionStatement.class, statements.get(0).getClass());
            FunctionStatement fn = (FunctionStatement) statements.get(0);
            BogusScope captureScope = new BogusScope();
            fn.evaluate(captureScope);
            assertNotNull(captureScope.resolve(dummyIdentifier("x")));
            FunctionValue fnValue = (FunctionValue) captureScope.resolve(dummyIdentifier("x"));
            assertEquals(new IntegerValue(1), fnValue.call(captureScope, createArgs(new IntegerValue(1))));
        });

        assertParsing("fun x(a) { return a; }", 1, (List<BogusStatement> statements) -> {
            assertEquals(FunctionStatement.class, statements.get(0).getClass());
            FunctionStatement fn = (FunctionStatement) statements.get(0);
            BogusScope captureScope = new BogusScope();
            fn.evaluate(captureScope);
            assertNotNull(captureScope.resolve(dummyIdentifier("x")));
            FunctionValue fnValue = (FunctionValue) captureScope.resolve(dummyIdentifier("x"));
            assertEquals(new IntegerValue(1), fnValue.call(captureScope, createArgs(new IntegerValue(1))));
        });


        assertParsing("fun x(a) { let b = 2; return a + b; }", 1, (List<BogusStatement> statements) -> {
            assertEquals(FunctionStatement.class, statements.get(0).getClass());
            FunctionStatement fn = (FunctionStatement) statements.get(0);
            BogusScope captureScope = new BogusScope();
            fn.evaluate(captureScope);
            assertNotNull(captureScope.resolve(dummyIdentifier("x")));
            FunctionValue fnValue = (FunctionValue) captureScope.resolve(dummyIdentifier("x"));
            assertEquals(new IntegerValue(3), fnValue.call(captureScope, createArgs(new IntegerValue(1))));
        });
    }

    private LinkedList<Value> createArgs(Value ... values) {
        LinkedList<Value> vals = new LinkedList<>();
        for (Value value : values) {
            vals.add(value);
        }
        return vals;
    }

    private IdentifierExpression dummyIdentifier(String name) {
        return new IdentifierExpression(new IdentifierToken(new RawLiteral(1, 1, name)));
    }


    private void assertParsing(String s, int expectedStatementCount, StatementAssertionFunction o) {
        List<BogusStatement> statements = new BogusParser(new BogusLexer(s)).parse();
        assertEquals(expectedStatementCount, statements.size());
        o.run(statements);
    }
}
