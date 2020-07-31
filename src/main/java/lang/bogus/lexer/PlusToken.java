package lang.bogus.lexer;

/**
 * Created by juhof on 31.7.2020.
 */
public class PlusToken extends BogusToken {
    public PlusToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    protected TokenType type() {
        return TokenType.PLUS;
    }
}
