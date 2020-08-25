package lang.bogus.lexer;

public class SourceCodeAwareException extends RuntimeException {

    protected BogusToken token;

    public SourceCodeAwareException(BogusToken token) {
        super(String.format("@[%d;%d]", token.getLiteral().line, token.getLiteral().column));
        this.token = token;
    }
}
