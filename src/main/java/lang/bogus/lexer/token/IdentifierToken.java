package lang.bogus.lexer.token;

import lang.bogus.lexer.BogusLexer;
import lang.bogus.lexer.RawLiteral;
import lang.bogus.statement.BogusStatement;
import lang.bogus.expression.Expression;
import lang.bogus.expression.IdentifierExpression;

/**
 * Created by juhof on 29.7.2020.
 */
public class IdentifierToken extends BogusToken {
    public IdentifierToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    public TokenType type() {
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
        return new IdentifierExpression(this);
    }
}
