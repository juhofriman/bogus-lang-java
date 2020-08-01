package lang.bogus.lexer;

import lang.bogus.statement.BogusStatement;
import lang.bogus.statement.Expression;
import lang.bogus.statement.OperationExpression;
import lang.bogus.statement.PrefixExpression;

/**
 * Created by juhof on 31.7.2020.
 */
public class MinusToken extends BogusToken {
    public MinusToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    protected TokenType type() {
        return TokenType.MINUS;
    }

    @Override
    public BogusStatement parse(BogusLexer lexer) {
        return super.parseExpression(lexer, 0);
    }

    @Override
    public Expression parsePrefix(BogusLexer lexer) {
        Expression right = lexer.next().parseExpression(lexer, type().getInfixBindingPower());
        return new PrefixExpression(this, right);
    }

    @Override
    public Expression parseInfix(BogusLexer lexer, Expression left) {
        Expression right = lexer.next().parseExpression(lexer, type().getInfixBindingPower());
        return new OperationExpression(this, left, right);
    }
}
