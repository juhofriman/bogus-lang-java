package lang.bogus.lexer;

/**
 * Created by juhof on 29.7.2020.
 */
public class LeftParensToken extends BogusToken {
    public LeftParensToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    protected TokenType type() {
        return TokenType.LEFT_PARENS;
    }
}
