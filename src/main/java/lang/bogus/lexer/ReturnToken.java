package lang.bogus.lexer;

import lang.bogus.statement.BogusStatement;

/**
 * Created by juhof on 29.7.2020.
 */
public class ReturnToken extends BogusToken {
    public ReturnToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    protected TokenType type() {
        return TokenType.RETURN;
    }

    @Override
    public BogusStatement parse(BogusLexer lexer) {
        return null;
    }
}
