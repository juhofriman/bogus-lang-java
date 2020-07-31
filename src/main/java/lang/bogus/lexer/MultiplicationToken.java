package lang.bogus.lexer;

/**
 * Created by juhof on 31.7.2020.
 */
public class MultiplicationToken extends BogusToken {
    public MultiplicationToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    protected TokenType type() {
        return TokenType.MULTIPLICATION;
    }
}
