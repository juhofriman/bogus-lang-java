package lang.bogus.lexer.token;


import lang.bogus.expression.Expression;
import lang.bogus.expression.FunctionCallExpression;
import lang.bogus.expression.IdentifierExpression;
import lang.bogus.lexer.BogusLexer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LeftParensTokenTest {

    @Test
    public void testArgumentParsingForEmptryArguments() {
        BogusLexer bogusLexer = new BogusLexer("()");
        Expression expression = bogusLexer.next().parseInfix(bogusLexer, new IdentifierExpression("x"));
        assertEquals(FunctionCallExpression.class, expression.getClass());
        FunctionCallExpression fnCall = (FunctionCallExpression) expression;
        assertTrue(fnCall.getArguments().isEmpty());
    }

    @Test
    public void testArgumentParsignForASingleArgument() {
        BogusLexer bogusLexer = new BogusLexer("(1)");
        Expression expression = bogusLexer.next().parseInfix(bogusLexer, new IdentifierExpression("x"));
        assertEquals(FunctionCallExpression.class, expression.getClass());
        FunctionCallExpression fnCall = (FunctionCallExpression) expression;
        assertEquals(1, fnCall.getArguments().size());
    }

    @Test
    public void testArgumentParsignForMultipleArguments() {
        BogusLexer bogusLexer = new BogusLexer("(1, 2, 3, 4)");
        Expression expression = bogusLexer.next().parseInfix(bogusLexer, new IdentifierExpression("x"));
        assertEquals(FunctionCallExpression.class, expression.getClass());
        FunctionCallExpression fnCall = (FunctionCallExpression) expression;
        assertEquals(4, fnCall.getArguments().size());
    }
}
