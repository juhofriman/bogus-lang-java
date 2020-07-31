package lang.bogus.lexer;

/**
 * Created by juhof on 31.7.2020.
 */
public class RightBraceToken extends BogusToken {
    public RightBraceToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    protected TokenType type() {
        return TokenType.RIGHT_BRACE;
    }
}
