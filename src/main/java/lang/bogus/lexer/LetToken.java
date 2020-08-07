package lang.bogus.lexer;

import lang.bogus.statement.BogusStatement;
import lang.bogus.statement.Expression;
import lang.bogus.statement.Identifier;
import lang.bogus.statement.LetStatement;

/**
 * Created by juhof on 29.7.2020.
 */
public class LetToken extends BogusToken {

    protected LetToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    protected TokenType type() {
        return TokenType.LET;
    }

    @Override
    public BogusStatement parse(BogusLexer lexer) {
        BogusToken identifier = lexer.next(TokenType.IDENTIFIER);
        lexer.next(TokenType.EQUALS);
        Expression expression = lexer.next().parseExpression(lexer);
        return new LetStatement(new Identifier(identifier), expression);
    }

}
