package lang.bogus.lexer;

import lang.bogus.lexer.token.BogusToken;
import lang.bogus.lexer.token.TokenType;
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
        assertTokensMatch("let", TokenType.LET);
        assertTokensMatch("return", TokenType.RETURN);
        assertTokensMatch("fun", TokenType.FUN);
        assertTokensMatch("(", TokenType.LEFT_PARENS);
        assertTokensMatch(")", TokenType.RIGHT_PARENS);
        assertTokensMatch("{", TokenType.LEFT_BRACE);
        assertTokensMatch("}", TokenType.RIGHT_BRACE);

        assertTokensMatch("12", TokenType.INT);
        assertTokensMatch("\"Hello\"", TokenType.STRING);
        assertTokensMatch("=", TokenType.EQUALS);
        assertTokensMatch("+", TokenType.PLUS);
        assertTokensMatch("-", TokenType.MINUS);
        assertTokensMatch("*", TokenType.MULTIPLICATION);
        assertTokensMatch("/", TokenType.DIVISION);
        assertTokensMatch("myvar", TokenType.IDENTIFIER);
        assertTokensMatch(",", TokenType.COMMA);
        assertTokensMatch(";", TokenType.SEMICOLON);
    }

    @Test
    public void testTokenCombinations() {
        assertTokensMatch("(1)", TokenType.LEFT_PARENS, TokenType.INT, TokenType.RIGHT_PARENS);
        assertTokensMatch("( 1 )", TokenType.LEFT_PARENS, TokenType.INT, TokenType.RIGHT_PARENS);
        assertTokensMatch("a(b, c)",
                TokenType.IDENTIFIER,
                TokenType.LEFT_PARENS,
                TokenType.IDENTIFIER,
                TokenType.COMMA,
                TokenType.IDENTIFIER,
                TokenType.RIGHT_PARENS);
    }

    @Test
    public void testConsumeToken() {
        BogusLexer lexer = lexerFromSource("let return fun +");

        assertTokenMatches(TokenType.LET, lexer.current());

        assertTrue(lexer.hasNext());
        assertTokenMatches(TokenType.RETURN, lexer.next());
        assertTokenMatches(TokenType.RETURN, lexer.current());

        assertTrue(lexer.hasNext());
        assertTokenMatches(TokenType.FUN, lexer.next());
        assertTokenMatches(TokenType.FUN, lexer.current());

        assertTrue(lexer.hasNext());
        assertTokenMatches(TokenType.PLUS, lexer.next());
        assertTokenMatches(TokenType.PLUS, lexer.current());

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
    }

    @Test
    public void testPeek() {
        BogusLexer lexer = lexerFromSource("let return fun +");

        assertTrue(lexer.hasNext());
        assertTokenMatches(TokenType.LET, lexer.current());
        assertTokenMatches(TokenType.RETURN, lexer.peek());
        lexer.next();
        assertTokenMatches(TokenType.RETURN, lexer.current());
        assertTokenMatches(TokenType.FUN, lexer.peek());
        lexer.next();
        assertTokenMatches(TokenType.FUN, lexer.current());
        assertTokenMatches(TokenType.PLUS, lexer.peek());
        lexer.next();
        assertTokenMatches(TokenType.PLUS, lexer.current());
        assertFalse(lexer.hasNext());
        assertNull(lexer.peek());
    }


    @Test(expected = ExpectingTokenException.class)
    public void testNextWithAssertion() {
        BogusLexer lexer = lexerFromSource("let notfun");
        lexer.next(TokenType.FUN);
    }

    private void assertTokensMatch(String source, TokenType ... expected) {
        BogusLexer lexer = lexerFromSource(source);
        assertTrue("Lexer was empty", lexer.current() != null);

        for (TokenType tokenType : expected) {
            assertTokenMatches(tokenType, lexer.current());
            lexer.next();
        }

        assertFalse("Lexer was not empty", lexer.hasNext());
    }

    private void assertTokenMatches(TokenType expected, BogusToken next) {
        assertTrue(String.format("Expecting %s but received %s", expected, next), next.is(expected));
    }

    private BogusLexer lexerFromSource(String code) {
        return new BogusLexer(code);
    }
}
