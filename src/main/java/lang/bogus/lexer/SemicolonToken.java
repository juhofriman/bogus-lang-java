package lang.bogus.lexer;

import lang.bogus.statement.BogusStatement;
import lang.bogus.statement.Expression;

public class SemicolonToken extends BogusToken {
    public SemicolonToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    protected TokenType type() {
        return TokenType.SEMICOLON;
    }

    @Override
    public BogusStatement parse(BogusLexer lexer) {
        return null;
    }

    @Override
    public Expression parseInfix(BogusLexer lexer, Expression left) {
        lexer.next();
        return left;
    }
}
