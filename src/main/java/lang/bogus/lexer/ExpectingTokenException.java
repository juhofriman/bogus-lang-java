package lang.bogus.lexer;

import lang.bogus.lexer.token.BogusToken;
import lang.bogus.lexer.token.TokenType;

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
