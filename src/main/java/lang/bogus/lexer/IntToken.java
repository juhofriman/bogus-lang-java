package lang.bogus.lexer;

/**
 * Created by juhof on 31.7.2020.
 */
public class IntToken extends BogusToken {
    public IntToken(RawLiteral rawLiteral) {
        super(rawLiteral);
    }

    @Override
    protected TokenType type() {
        return TokenType.INT;
    }
}
