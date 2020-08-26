package lang.bogus.lexer.token;

import lang.bogus.lexer.BogusLexer;
import lang.bogus.lexer.RawLiteral;
import lang.bogus.statement.BogusStatement;
import lang.bogus.expression.Expression;
import lang.bogus.statement.ReturnStatement;

/**
 * Created by juhof on 29.7.2020.
 */
public class ReturnToken extends BogusToken {
    public ReturnToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    public TokenType type() {
        return TokenType.RETURN;
    }

    @Override
    public BogusStatement parse(BogusLexer lexer) {
        Expression expression = lexer.next().parseExpression(lexer);
        return new ReturnStatement(expression);
    }
}
