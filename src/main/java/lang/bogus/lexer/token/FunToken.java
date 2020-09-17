package lang.bogus.lexer.token;

import lang.bogus.expression.Expression;
import lang.bogus.expression.IdentifierExpression;
import lang.bogus.lexer.BogusLexer;
import lang.bogus.lexer.RawLiteral;
import lang.bogus.statement.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by juhof on 29.7.2020.
 */
public class FunToken extends BogusToken {
    public FunToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    public TokenType type() {
        return TokenType.FUN;
    }

    @Override
    public BogusStatement parse(BogusLexer lexer) {
        BogusToken identifier = lexer.next(TokenType.IDENTIFIER);
        List<IdentifierExpression> arguments = new LinkedList<>();
        lexer.next(TokenType.LEFT_PARENS);
        BogusToken next = lexer.next();
        while (!next.getLiteral().literal.equals(")")) {
            if(next instanceof IdentifierToken) {
                arguments.add(new IdentifierExpression(next));
                next = lexer.next();
                if(next.getLiteral().literal.equals(",")) {
                    next = lexer.next();
                    continue;
                }

            } else {
                throw new RuntimeException("Encoutered " + next + " while expecting identifier");
            }
        }
        next = lexer.next();
        if(next.type() == TokenType.EQUALS) {
            Expression expression = lexer.next().parseExpression(lexer);
            return new FunctionStatement(new IdentifierExpression(identifier), arguments, expression);
        }
        if(next.type() == TokenType.LEFT_BRACE) {
            BogusStatement statement = next.parse(lexer);
            return new FunctionStatement(new IdentifierExpression(identifier), arguments, statement);
        }
        throw new RuntimeException("Expecting = or {, encountered " + next);


    }
}
