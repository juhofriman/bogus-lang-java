package lang.bogus.lexer;

import lang.bogus.statement.BogusStatement;
import lang.bogus.statement.Expression;

/**
 * Created by juhof on 29.7.2020.
 */
public class RightParensToken extends BogusToken {
    public RightParensToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    protected TokenType type() {
        return TokenType.RIGHT_PARENS;
    }

    @Override
    public BogusStatement parse(BogusLexer lexer) {
        return parseExpression(lexer);
    }

    @Override
    public Expression parsePrefix(BogusLexer lexer) {
        return null;
    }

    @Override
    public Expression parseInfix(BogusLexer lexer, Expression left) {
        lexer.next();
        return left;
    }

}
