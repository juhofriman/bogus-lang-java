package lang.bogus.lexer;

/**
 * Created by juhof on 29.7.2020.
 */
public class IdentifierToken extends BogusToken {
    public IdentifierToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    protected TokenType type() {
        return TokenType.IDENTIFIER;
    }
}
