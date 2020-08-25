package lang.bogus.lexer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for simple BogusRepl.
 */
public class BogusLexerTest {

    @Test
    public void forEmptyCodeReturnsEmpty() {
        BogusLexer lexer = lexerFromSource("");
        assertFalse(lexer.hasNext());
        assertNull(lexer.next());
    }

    @Test
    public void lexesTokensAsExpected() {
        assertSingleTokenMatches("let", TokenType.LET);
        assertSingleTokenMatches("return", TokenType.RETURN);
        assertSingleTokenMatches("fun", TokenType.FUN);
        assertSingleTokenMatches("(", TokenType.LEFT_PARENS);
        assertSingleTokenMatches(")", TokenType.RIGHT_PARENS);
        assertSingleTokenMatches("{", TokenType.LEFT_BRACE);
        assertSingleTokenMatches("}", TokenType.RIGHT_BRACE);

        assertSingleTokenMatches("12", TokenType.INT);
        assertSingleTokenMatches("\"Hello\"", TokenType.STRING);
        assertSingleTokenMatches("=", TokenType.EQUALS);
        assertSingleTokenMatches("+", TokenType.PLUS);
        assertSingleTokenMatches("-", TokenType.MINUS);
        assertSingleTokenMatches("*", TokenType.MULTIPLICATION);
        assertSingleTokenMatches("/", TokenType.DIVISION);
        assertSingleTokenMatches("myvar", TokenType.IDENTIFIER);
        assertSingleTokenMatches(",", TokenType.COMMA);
        assertSingleTokenMatches(";", TokenType.SEMICOLON);
    }

    @Test
    public void testConsumeToken() {
        BogusLexer lexer = lexerFromSource("let return fun +");

        assertTrue(lexer.hasNext());
        assertTokenMatches(TokenType.LET, lexer.next());
        assertTrue(lexer.hasNext());
        assertTokenMatches(TokenType.RETURN, lexer.next());
        assertTrue(lexer.hasNext());
        assertTokenMatches(TokenType.FUN, lexer.next());
        assertTrue(lexer.hasNext());
        assertTokenMatches(TokenType.PLUS, lexer.next());

        assertFalse(lexer.hasNext());
        assertNull(lexer.next());
    }

    @Test
    public void testLookahead() {
        BogusLexer lexer = lexerFromSource("let return fun +");

        assertNull(lexer.lookahead(100));

        assertTrue(lexer.hasNext());
        assertTokenMatches(TokenType.LET, lexer.lookahead(0));
        assertTrue(lexer.hasNext());
        assertTokenMatches(TokenType.RETURN, lexer.lookahead(1));
        assertTrue(lexer.hasNext());
        assertTokenMatches(TokenType.FUN,  lexer.lookahead(2));
        assertTrue(lexer.hasNext());
        assertTokenMatches(TokenType.PLUS,  lexer.lookahead(3));

        assertTrue(lexer.hasNext());
        assertTokenMatches(TokenType.LET, lexer.next());
        assertTrue(lexer.hasNext());
    }

    @Test(expected = ExpectingTokenException.class)
    public void testNextWithAssertion() {
        BogusLexer lexer = lexerFromSource("let");
        lexer.next(TokenType.FUN);
    }

    private void assertSingleTokenMatches(String source, TokenType expected) {
        BogusLexer lexer = lexerFromSource(source);
        assertTrue(lexer.hasNext());
        BogusToken next = lexer.next();
        assertTokenMatches(expected, next);
        assertFalse(lexer.hasNext());
    }

    private void assertTokenMatches(TokenType expected, BogusToken next) {
        assertTrue(String.format("Expecting %s but received %s", expected, next), next.is(expected));
    }

    private BogusLexer lexerFromSource(String code) {
        return new BogusLexer(code);
    }
}
