package lang.bogus.lexer;

/**
 * Created by juhof on 29.7.2020.
 */
public class UnrecognizedTokenException extends RuntimeException {
    public UnrecognizedTokenException(RawLiteral literal) {
        super("Encountered unrecognized literal: " + literal + " : " + literal.line + "," + literal.column);
    }
}
