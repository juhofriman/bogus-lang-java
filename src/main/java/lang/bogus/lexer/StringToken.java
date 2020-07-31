package lang.bogus.lexer;

/**
 * Created by juhof on 31.7.2020.
 */
public class StringToken extends BogusToken{
    public StringToken(RawLiteral rawLiteral) {
        super(rawLiteral);
    }

    @Override
    protected TokenType type() {
        return TokenType.STRING;
    }
}
