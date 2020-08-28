package lang.bogus.lexer.token;

import lang.bogus.lang.bogus.inspector.Deck;
import lang.bogus.lexer.BogusLexer;
import lang.bogus.lexer.RawLiteral;
import lang.bogus.statement.BogusStatement;
import lang.bogus.expression.Expression;

/**
 * Created by juhof on 29.7.2020.
 */
public class RightParensToken extends BogusToken {
    public RightParensToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    public TokenType type() {
        return TokenType.RIGHT_PARENS;
    }

    @Override
    public BogusStatement parse(BogusLexer lexer) {
        return parseExpression(lexer, type().getInfixBindingPower() + 1);
    }

    @Override
    public Expression parsePrefix(BogusLexer lexer) {
        return null;
    }

    @Override
    public Expression parseInfix(BogusLexer lexer, Expression left) {
        return left;
    }

}
