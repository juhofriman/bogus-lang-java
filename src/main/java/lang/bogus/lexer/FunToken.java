package lang.bogus.lexer;

/**
 * Created by juhof on 29.7.2020.
 */
public class FunToken extends BogusToken {
    public FunToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    protected TokenType type() {
        return TokenType.FUN;
    }
}
