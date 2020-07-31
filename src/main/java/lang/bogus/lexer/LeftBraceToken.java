package lang.bogus.lexer;

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
}
