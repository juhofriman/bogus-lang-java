package lang.bogus.lexer.token;

import lang.bogus.expression.BooleanExpression;
import lang.bogus.expression.Expression;
import lang.bogus.lexer.BogusLexer;
import lang.bogus.lexer.RawLiteral;
import lang.bogus.statement.BogusStatement;

public class BooleanToken extends BogusToken {
    public BooleanToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    public TokenType type() {
        return TokenType.BOOLEAN;
    }

    @Override
    public BogusStatement parse(BogusLexer lexer) {
        return null;
    }

    @Override
    public Expression parsePrefix(BogusLexer lexer) {
        return new BooleanExpression(this.getLiteral().literal.equals("true"));
    }
}
