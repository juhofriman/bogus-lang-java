package lang.bogus.lexer.token;

import lang.bogus.lexer.BogusLexer;
import lang.bogus.lexer.RawLiteral;
import lang.bogus.statement.BogusStatement;
import lang.bogus.expression.Expression;
import lang.bogus.expression.IdentifierExpression;
import lang.bogus.statement.LetStatement;

/**
 * Created by juhof on 29.7.2020.
 */
public class LetToken extends BogusToken {

    public LetToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    public TokenType type() {
        return TokenType.LET;
    }

    @Override
    public BogusStatement parse(BogusLexer lexer) {
        BogusToken identifier = lexer.next(TokenType.IDENTIFIER);
        lexer.next(TokenType.EQUALS);
        Expression expression = lexer.next().parseExpression(lexer);
        return new LetStatement(new IdentifierExpression(identifier), expression);
    }

}
