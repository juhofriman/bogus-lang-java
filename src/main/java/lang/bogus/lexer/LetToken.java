package lang.bogus.lexer;

/**
 * Created by juhof on 29.7.2020.
 */
public class LetToken extends BogusToken {

    protected LetToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    protected TokenType type() {
        return TokenType.LET;
    }

}
