package lang.bogus.lexer.token;

import lang.bogus.expression.Expression;
import lang.bogus.expression.FunctionCallExpression;
import lang.bogus.expression.IdentifierExpression;
import lang.bogus.lexer.BogusLexer;
import lang.bogus.lexer.RawLiteral;
import lang.bogus.statement.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by juhof on 29.7.2020.
 */
public class LeftParensToken extends BogusToken {
    public LeftParensToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    public TokenType type() {
        return TokenType.LEFT_PARENS;
    }

    @Override
    public BogusStatement parse(BogusLexer lexer) {
        return parseExpression(lexer);
    }

    @Override
    public Expression parsePrefix(BogusLexer lexer) {
        return lexer.next().parseExpression(lexer);
    }

    @Override
    public Expression parseInfix(BogusLexer lexer, Expression left) {
        if(left instanceof IdentifierExpression) {
            List<Expression> arguments = new LinkedList<>();
            if(lexer.peek().type() == TokenType.RIGHT_PARENS) {
                // nothing to add
                lexer.next();
            } else {
                lexer.next();
                arguments.add(lexer.current().parseExpression(lexer));
                while (lexer.hasNext() && lexer.peek().type() == TokenType.COMMA) {
                    lexer.next();
                    lexer.next();
                    arguments.add(lexer.current().parseExpression(lexer));
                    if(lexer.current().type() == TokenType.RIGHT_PARENS) {
                        break;
                    }
                }
            }
            return new FunctionCallExpression((IdentifierExpression) left, arguments);
        }
        return left;
    }
}
