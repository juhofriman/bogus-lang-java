package lang.bogus.lexer;

import lang.bogus.statement.BogusStatement;

/**
 * Created by juhof on 31.7.2020.
 */
public class LeftBraceToken extends BogusToken {
    public LeftBraceToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    protected TokenType type() {
        return TokenType.LEFT_BRACE;
    }

    @Override
    public BogusStatement parse(BogusLexer lexer) {
        return null;
    }
}
