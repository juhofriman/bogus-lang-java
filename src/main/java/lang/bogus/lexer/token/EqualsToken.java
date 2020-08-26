package lang.bogus.lexer.token;

import lang.bogus.lexer.BogusLexer;
import lang.bogus.lexer.RawLiteral;
import lang.bogus.statement.BogusStatement;

/**
 * Created by juhof on 31.7.2020.
 */
public class EqualsToken extends BogusToken {
    public EqualsToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    public TokenType type() {
        return TokenType.EQUALS;
    }

    @Override
    public BogusStatement parse(BogusLexer lexer) {
        return null;
    }
}
