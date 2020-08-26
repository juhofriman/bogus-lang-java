package lang.bogus.lexer.token;

import lang.bogus.lexer.BogusLexer;
import lang.bogus.lexer.RawLiteral;
import lang.bogus.statement.BogusStatement;
import lang.bogus.expression.Expression;
import lang.bogus.expression.IntegerExpression;

/**
 * Created by juhof on 31.7.2020.
 */
public class IntToken extends BogusToken {
    public IntToken(RawLiteral rawLiteral) {
        super(rawLiteral);
    }

    @Override
    public TokenType type() {
        return TokenType.INT;
    }

    @Override
    public BogusStatement parse(BogusLexer lexer) {
        return super.parseExpression(lexer);
    }

    @Override
    public Expression parsePrefix(BogusLexer lexer) {
        return new IntegerExpression(this);
    }

    @Override
    public Expression parseInfix(BogusLexer lexer, Expression left) {
        return null;
    }
}
