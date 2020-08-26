package lang.bogus.lexer.token;

import lang.bogus.lexer.BogusLexer;
import lang.bogus.lexer.RawLiteral;
import lang.bogus.statement.BogusStatement;
import lang.bogus.expression.Expression;

/**
 * Created by juhof on 31.7.2020.
 */
public class RightBraceToken extends BogusToken {
    public RightBraceToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    public TokenType type() {
        return TokenType.RIGHT_BRACE;
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
