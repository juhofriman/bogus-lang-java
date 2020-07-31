package lang.bogus.lexer;

/**
 * Created by juhof on 31.7.2020.
 */
public class DivisionToken extends BogusToken {
    public DivisionToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    protected TokenType type() {
        return TokenType.DIVISION;
    }
}
