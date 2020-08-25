package lang.bogus.lexer;

import lang.bogus.statement.BogusStatement;
import lang.bogus.statement.Expression;

public class CommaToken extends BogusToken {
    public CommaToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    protected TokenType type() {
        return TokenType.COMMA;
    }

    @Override
    public BogusStatement parse(BogusLexer lexer) {
        return null;
    }

    @Override
    public Expression parsePrefix(BogusLexer lexer) {
        return lexer.next().parseExpression(lexer);
    }

}
