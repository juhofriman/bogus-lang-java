package lang.bogus.lexer;

public class ExpectingTokenException extends SourceCodeAwareException {

    private final TokenType expected;

    public ExpectingTokenException(TokenType expected, BogusToken receivedToken) {
        super(receivedToken);
        this.expected = expected;
    }

    @Override
    public String getMessage() {
        return String.format("Expecting token %s but got %s - %s",
                this.expected,
                super.token.type(),
                super.getMessage());
    }
}
