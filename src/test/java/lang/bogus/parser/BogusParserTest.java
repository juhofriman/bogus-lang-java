package lang.bogus.parser;

import lang.bogus.expression.*;
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
        assertParsing("1", statementCountMustBe(1), (List<BogusStatement> statements) -> {
            assertEquals(IntegerExpression.class, statements.get(0).getClass());
        });

        assertParsing("\"String\"", statementCountMustBe(1), (List<BogusStatement> statements) -> {
            assertEquals(StringExpression.class, statements.get(0).getClass());
        });
    }

    @Test
    public void testArithmeticExpressions() {
        assertParsing("1 + 2", statementCountMustBe(1), (List<BogusStatement> statements) -> {
            assertEquals(OperationExpression.class, statements.get(0).getClass());
            assertEquals(new IntegerValue(3), statements.get(0).evaluate(new BogusScope()));
        });

        assertParsing("(1 + 1)", statementCountMustBe(1), (List<BogusStatement> statements) -> {
            assertEquals(OperationExpression.class, statements.get(0).getClass());
            assertEquals(new IntegerValue(2), statements.get(0).evaluate(new BogusScope()));
        });

        assertParsing("(1) + (1)", statementCountMustBe(1), (List<BogusStatement> statements) -> {
            assertEquals(OperationExpression.class, statements.get(0).getClass());
            assertEquals(new IntegerValue(2), statements.get(0).evaluate(new BogusScope()));
        });

        assertParsing("(1 + 1) + (1)", statementCountMustBe(1), (List<BogusStatement> statements) -> {
            assertEquals(OperationExpression.class, statements.get(0).getClass());
            assertEquals(new IntegerValue(3), statements.get(0).evaluate(new BogusScope()));
        });

        assertParsing("(1 + (1 + 1)) + (1)", statementCountMustBe(1), (List<BogusStatement> statements) -> {
            assertEquals(OperationExpression.class, statements.get(0).getClass());
            assertEquals(new IntegerValue(4), statements.get(0).evaluate(new BogusScope()));
        });

        // TODO: this does not pass
//        assertParsing("(((1 + (1 + 1)) + (1 + (1 + 1 + (1 + 1))))", statementCountMustBe(1), (List<BogusStatement> statements) -> {
//            assertEquals(OperationExpression.class, statements.get(0).getClass());
//            assertEquals(new IntegerValue(8), statements.get(0).evaluate(new BogusScope()));
//        });
    }

    @Test
    public void testSimpleFnCall() {
        assertParsing("foo()", statementCountMustBe(1), (List<BogusStatement> statements) -> {
            assertEquals(FunctionCallExpression.class, statements.get(0).getClass());
            FunctionCallExpression call = (FunctionCallExpression) statements.get(0);
            assertEquals(0, call.getArguments().size());
        });
    }

    @Test
    public void testFnCallWithArg() {
        assertParsing("foo(1)", statementCountMustBe(1), (List<BogusStatement> statements) -> {
            assertEquals(FunctionCallExpression.class, statements.get(0).getClass());
            FunctionCallExpression call = (FunctionCallExpression) statements.get(0);
            assertEquals(1, call.getArguments().size());
            assertEquals(IntegerExpression.class, call.getArguments().get(0).getClass());
        });
    }

    @Test
    public void testFnCallWitthStringArg() {
        assertParsing("foo(\"bar\")", statementCountMustBe(1), (List<BogusStatement> statements) -> {
            assertEquals(FunctionCallExpression.class, statements.get(0).getClass());
            FunctionCallExpression call = (FunctionCallExpression) statements.get(0);
            assertEquals(1, call.getArguments().size());
            assertEquals(StringExpression.class, call.getArguments().get(0).getClass());
        });
    }

    @Test
    public void testFnCallWithMultipleArgs() {

        assertParsing("foo(1, 2)", statementCountMustBe(1), (List<BogusStatement> statements) -> {
            assertEquals(FunctionCallExpression.class, statements.get(0).getClass());
            FunctionCallExpression call = (FunctionCallExpression) statements.get(0);
            assertEquals(2, call.getArguments().size());
            assertEquals(IntegerExpression.class, call.getArguments().get(0).getClass());
            assertEquals(IntegerExpression.class, call.getArguments().get(1).getClass());
        });

        assertParsing("foo(1, 2, 3, 4)", statementCountMustBe(1), (List<BogusStatement> statements) -> {
            assertEquals(FunctionCallExpression.class, statements.get(0).getClass());
            FunctionCallExpression call = (FunctionCallExpression) statements.get(0);
            assertEquals(4, call.getArguments().size());
            assertEquals(IntegerExpression.class, call.getArguments().get(0).getClass());
            assertEquals(IntegerExpression.class, call.getArguments().get(1).getClass());
            assertEquals(IntegerExpression.class, call.getArguments().get(2).getClass());
            assertEquals(IntegerExpression.class, call.getArguments().get(3).getClass());
        });

    }

    @Test
    public void testNestedFnCall() {
        assertParsing("foo(bar(1))", statementCountMustBe(1), (List<BogusStatement> statements) -> {
            assertEquals(FunctionCallExpression.class, statements.get(0).getClass());
            FunctionCallExpression call = (FunctionCallExpression) statements.get(0);
            assertEquals(1, call.getArguments().size());
            assertEquals(FunctionCallExpression.class, call.getArguments().get(0).getClass());
            assertEquals(1, ((FunctionCallExpression) call.getArguments().get(0)).getArguments().size());
        });
    }

    @Test
    public void testNestedFnCallWithMultipleArgs() {
        assertParsing("foo(bar(1, 2))", statementCountMustBe(1), (List<BogusStatement> statements) -> {
            assertEquals(FunctionCallExpression.class, statements.get(0).getClass());
            FunctionCallExpression call = (FunctionCallExpression) statements.get(0);
            assertEquals(1, call.getArguments().size());
            assertEquals(FunctionCallExpression.class, call.getArguments().get(0).getClass());
            assertEquals(2, ((FunctionCallExpression) call.getArguments().get(0)).getArguments().size());
        });
    }

    @Test
    public void testYetAnotherNestedeFnCall() {
        assertParsing("foo(bar(1, 2), 1)", statementCountMustBe(1), (List<BogusStatement> statements) -> {
            assertEquals(FunctionCallExpression.class, statements.get(0).getClass());
            FunctionCallExpression call = (FunctionCallExpression) statements.get(0);
            assertEquals(2, call.getArguments().size());
            assertEquals(FunctionCallExpression.class, call.getArguments().get(0).getClass());
            assertEquals(2, ((FunctionCallExpression) call.getArguments().get(0)).getArguments().size());
        });

        assertParsing("foo(1, bar(1, 2))", statementCountMustBe(1), (List<BogusStatement> statements) -> {
            assertEquals(FunctionCallExpression.class, statements.get(0).getClass());
            FunctionCallExpression call = (FunctionCallExpression) statements.get(0);
            assertEquals(2, call.getArguments().size());
            assertEquals(IntegerExpression.class, call.getArguments().get(0).getClass());
            assertEquals(2, ((FunctionCallExpression) call.getArguments().get(1)).getArguments().size());
        });
    }

    @Test
    public void testComplexNestedFnCall() {
        assertParsing("foo(baz(1, 2), bar(1, 2))", statementCountMustBe(1), (List<BogusStatement> statements) -> {
            assertEquals(FunctionCallExpression.class, statements.get(0).getClass());
            FunctionCallExpression rootCall = (FunctionCallExpression) statements.get(0);
            assertEquals(2, rootCall.getArguments().size());

            assertEquals(FunctionCallExpression.class, rootCall.getArguments().get(0).getClass());
            assertEquals(2, ((FunctionCallExpression) rootCall.getArguments().get(0)).getArguments().size());

        });
    }

    @Test
    public void testFnCallWithDifferentTypes() {

        assertParsing("foo(\"bar\", 2)", statementCountMustBe(1), (List<BogusStatement> statements) -> {
            assertEquals(FunctionCallExpression.class, statements.get(0).getClass());
            FunctionCallExpression call = (FunctionCallExpression) statements.get(0);
            assertEquals(2, call.getArguments().size());
            assertEquals(StringExpression.class, call.getArguments().get(0).getClass());
            assertEquals(IntegerExpression.class, call.getArguments().get(1).getClass());
        });

        assertParsing("foo(1, \"bar\")", statementCountMustBe(1), (List<BogusStatement> statements) -> {
            assertEquals(FunctionCallExpression.class, statements.get(0).getClass());
            FunctionCallExpression call = (FunctionCallExpression) statements.get(0);
            assertEquals(2, call.getArguments().size());
            assertEquals(IntegerExpression.class, call.getArguments().get(0).getClass());
            assertEquals(StringExpression.class, call.getArguments().get(1).getClass());
        });

    }

    @Test
    public void testConsecutiveFnCalls() {
        assertParsing(multiline("foo();",  "bar();"), statementCountMustBe(2), (List<BogusStatement> statements) -> {
            assertEquals(FunctionCallExpression.class, statements.get(0).getClass());
            assertEquals(FunctionCallExpression.class, statements.get(1).getClass());
        });

    }

    @Test
    public void testConsecutiveFnCallsWithArg() {
        assertParsing(multiline("foo(1);",  "bar(2);"), statementCountMustBe(2), (List<BogusStatement> statements) -> {
            assertEquals(FunctionCallExpression.class, statements.get(0).getClass());
            assertEquals(FunctionCallExpression.class, statements.get(1).getClass());
        });

    }

    @Test
    public void testConsecutiveNestedFnCallWithArg() {

        assertParsing(multiline("foo(baz(1));",  "bar(daz(2));"), statementCountMustBe(2), (List<BogusStatement> statements) -> {
            assertEquals(FunctionCallExpression.class, statements.get(0).getClass());
            FunctionCallExpression call = (FunctionCallExpression) statements.get(0);
            assertEquals(1, call.getArguments().size());
            assertEquals(FunctionCallExpression.class, call.getArguments().get(0).getClass());
            assertEquals(1, ((FunctionCallExpression) call.getArguments().get(0)).getArguments().size());

            assertEquals(FunctionCallExpression.class, statements.get(1).getClass());
            FunctionCallExpression call2 = (FunctionCallExpression) statements.get(1);
            assertEquals(1, call2.getArguments().size());
            assertEquals(FunctionCallExpression.class, call2.getArguments().get(0).getClass());
            assertEquals(1, ((FunctionCallExpression) call2.getArguments().get(0)).getArguments().size());


        });
    }

    @Test
    public void testFunctionDefinition() {
        assertParsing("fun x(a) = a", statementCountMustBe(1), (List<BogusStatement> statements) -> {
            assertEquals(FunctionStatement.class, statements.get(0).getClass());
            FunctionStatement fn = (FunctionStatement) statements.get(0);
            BogusScope captureScope = new BogusScope();
            fn.evaluate(captureScope);
            assertNotNull(captureScope.resolve(dummyIdentifier("x")));
            FunctionValue fnValue = (FunctionValue) captureScope.resolve(dummyIdentifier("x"));
            assertEquals(new IntegerValue(1), fnValue.call(captureScope, createArgs(new IntegerValue(1))));
        });

        assertParsing("fun x(a) { return a; }", statementCountMustBe(1), (List<BogusStatement> statements) -> {
            assertEquals(FunctionStatement.class, statements.get(0).getClass());
            FunctionStatement fn = (FunctionStatement) statements.get(0);
            BogusScope captureScope = new BogusScope();
            fn.evaluate(captureScope);
            assertNotNull(captureScope.resolve(dummyIdentifier("x")));
            FunctionValue fnValue = (FunctionValue) captureScope.resolve(dummyIdentifier("x"));
            assertEquals(new IntegerValue(1), fnValue.call(captureScope, createArgs(new IntegerValue(1))));
        });


        assertParsing("fun x(a) { let b = 2; return a + b; }", statementCountMustBe(1), (List<BogusStatement> statements) -> {
            assertEquals(FunctionStatement.class, statements.get(0).getClass());
            FunctionStatement fn = (FunctionStatement) statements.get(0);
            BogusScope captureScope = new BogusScope();
            fn.evaluate(captureScope);
            assertNotNull(captureScope.resolve(dummyIdentifier("x")));
            FunctionValue fnValue = (FunctionValue) captureScope.resolve(dummyIdentifier("x"));
            assertEquals(new IntegerValue(3), fnValue.call(captureScope, createArgs(new IntegerValue(1))));
        });
    }

    @Test
    public void multiStatementParsing() {
        assertParsing(multiline(
                "let a = 1;",
                "let b = 2;",
                "let c = 3;"
        ), statementCountMustBe(3), (List<BogusStatement> statements) -> {
            assertEquals(LetStatement.class, statements.get(0).getClass());
            assertEquals(LetStatement.class, statements.get(1).getClass());
            assertEquals(LetStatement.class, statements.get(2).getClass());
        });

        assertParsing(multiline(
                "let a = x(1);",
                "let b = 2;",
                "let c = 3;"
        ), statementCountMustBe(3), (List<BogusStatement> statements) -> {
            assertEquals(LetStatement.class, statements.get(0).getClass());
            assertEquals(LetStatement.class, statements.get(1).getClass());
            assertEquals(LetStatement.class, statements.get(2).getClass());
        });
    }

    @Test
    public void complexCode() {
        assertParsing(multiline(
                "fun x() {",
                "    let a = 5;",
                "    let b = 3;",
                "    return  a + b;",
                "};",
                "let value = x();",
                "let value2 = x();"
        ), statementCountMustBe(3), (List<BogusStatement> statements) -> {
            assertEquals(FunctionStatement.class, statements.get(0).getClass());
            assertEquals(LetStatement.class, statements.get(1).getClass());
            assertEquals(LetStatement.class, statements.get(2).getClass());
        });
    }

    private int statementCountMustBe(int i) {
        return i;
    }

    private String multiline(String ... lines) {
        StringBuffer sb = new StringBuffer();
        for (String line : lines) {
            sb.append(line);
            sb.append("\n");
        }
        return sb.toString();
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
        System.out.println(statements);
        assertEquals("Expected " + expectedStatementCount + " statements but got " + statements.size(),
                expectedStatementCount, statements.size());
        o.run(statements);
    }
}
