package lang.bogus.lexer.token;

import lang.bogus.lexer.BogusLexer;
import lang.bogus.lexer.RawLiteral;
import lang.bogus.statement.BogusStatement;
import lang.bogus.expression.Expression;
import lang.bogus.expression.OperationExpression;
import lang.bogus.expression.PrefixExpression;

/**
 * Created by juhof on 31.7.2020.
 */
public class MinusToken extends BogusToken {
    public MinusToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    public TokenType type() {
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
