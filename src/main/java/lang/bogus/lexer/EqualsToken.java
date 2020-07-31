package lang.bogus.lexer;

/**
 * Created by juhof on 31.7.2020.
 */
public class EqualsToken extends BogusToken {
    public EqualsToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    protected TokenType type() {
        return TokenType.EQUALS;
    }
}
