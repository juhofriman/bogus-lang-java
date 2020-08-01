package lang.bogus.lexer;

import lang.bogus.statement.BogusStatement;

/**
 * Created by juhof on 29.7.2020.
 */
public class FunToken extends BogusToken {
    public FunToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    protected TokenType type() {
        return TokenType.FUN;
    }

    @Override
    public BogusStatement parse(BogusLexer lexer) {
        return null;
    }
}
