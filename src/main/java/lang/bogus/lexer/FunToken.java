package lang.bogus.lexer;

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
    protected TokenType type() {
        return TokenType.FUN;
    }

    /*
    fun sum(a, b) =
     */

    @Override
    public BogusStatement parse(BogusLexer lexer) {
        BogusToken identifier = lexer.next(TokenType.IDENTIFIER);
        List<Identifier> arguments = new LinkedList<>();
        lexer.next(TokenType.LEFT_PARENS);
        BogusToken next = lexer.next();
        while (!next.getLiteral().literal.equals(")")) {
            if(next instanceof IdentifierToken) {
                arguments.add(new Identifier(next));
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
            return new FunctionStatement(new Identifier(identifier), arguments, expression);
        }
        if(next.type() == TokenType.LEFT_BRACE) {
            BogusStatement statement = lexer.next().parse(lexer);
            System.out.println(statement);
            return new FunctionStatement(new Identifier(identifier), arguments, statement);
        }
        throw new RuntimeException("Expecting = or {, encountered " + next);


    }
}
