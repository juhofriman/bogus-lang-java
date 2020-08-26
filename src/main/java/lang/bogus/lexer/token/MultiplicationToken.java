package lang.bogus.lexer.token;

import lang.bogus.lexer.BogusLexer;
import lang.bogus.lexer.RawLiteral;
import lang.bogus.statement.BogusStatement;
import lang.bogus.expression.Expression;
import lang.bogus.expression.OperationExpression;

/**
 * Created by juhof on 31.7.2020.
 */
public class MultiplicationToken extends BogusToken {
    public MultiplicationToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    public TokenType type() {
        return TokenType.MULTIPLICATION;
    }

    @Override
    public BogusStatement parse(BogusLexer lexer) {
        return super.parseExpression(lexer);
    }

    @Override
    public Expression parsePrefix(BogusLexer lexer) {
        return null;
    }

    @Override
    public Expression parseInfix(BogusLexer lexer, Expression left) {
        Expression right = lexer.next().parseExpression(lexer, type().getInfixBindingPower());
        return new OperationExpression(this, left, right);
    }
}
