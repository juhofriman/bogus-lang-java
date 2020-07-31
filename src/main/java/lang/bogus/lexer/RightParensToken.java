package lang.bogus.lexer;

/**
 * Created by juhof on 29.7.2020.
 */
public class RightParensToken extends BogusToken {
    public RightParensToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    protected TokenType type() {
        return TokenType.RIGHT_PARENS;
    }
}
