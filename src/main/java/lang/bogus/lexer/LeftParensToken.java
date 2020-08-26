package lang.bogus.lexer;

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
    protected TokenType type() {
        return TokenType.LEFT_PARENS;
    }

    @Override
    public BogusStatement parse(BogusLexer lexer) {
        return parseExpression(lexer);
    }

    @Override
    public Expression parsePrefix(BogusLexer lexer) {
        return lexer.next().parseExpression(lexer, type().getPrefixBindingPower());
    }

    @Override
    public Expression parseInfix(BogusLexer lexer, Expression left) {
        System.out.println("left " + left);
        if(left instanceof Identifier) {
            List<Expression> arguments = new LinkedList<>();
            BogusToken next = lexer.next();
            while (next != null && lexer.hasNext()) {

                arguments.add(next.parseExpression(lexer));
                next = lexer.next();
            }
            return new FunctionCall((Identifier) left, arguments);
        }
        System.out.println(lexer.next());
        return left;
    }
}
