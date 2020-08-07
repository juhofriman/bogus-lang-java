package lang.bogus.lexer;

import lang.bogus.statement.BogusStatement;
import lang.bogus.statement.Expression;
import lang.bogus.statement.Identifier;

/**
 * Created by juhof on 29.7.2020.
 */
public class IdentifierToken extends BogusToken {
    public IdentifierToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    protected TokenType type() {
        return TokenType.IDENTIFIER;
    }

    @Override
    public BogusStatement parse(BogusLexer lexer) {
        return super.parseExpression(lexer);
    }

    @Override
    public Expression parseInfix(BogusLexer lexer, Expression left) {
        return null;
    }

    @Override
    public Expression parsePrefix(BogusLexer lexer) {
        return new Identifier(this);
    }
}
