package lang.bogus.lexer;

/**
 * Created by juhof on 31.7.2020.
 */
public class MinusToken extends BogusToken {
    public MinusToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    protected TokenType type() {
        return TokenType.MINUS;
    }
}
