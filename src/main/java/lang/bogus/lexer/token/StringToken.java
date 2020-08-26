package lang.bogus.lexer.token;

import lang.bogus.lexer.BogusLexer;
import lang.bogus.lexer.RawLiteral;
import lang.bogus.statement.BogusStatement;
import lang.bogus.expression.Expression;
import lang.bogus.expression.StringExpression;

/**
 * Created by juhof on 31.7.2020.
 */
public class StringToken extends BogusToken{
    public StringToken(RawLiteral rawLiteral) {
        super(rawLiteral);
    }

    @Override
    public TokenType type() {
        return TokenType.STRING;
    }

    @Override
    public BogusStatement parse(BogusLexer lexer) {
        return super.parseExpression(lexer);
    }

    @Override
    public Expression parsePrefix(BogusLexer lexer) {
        return new StringExpression(this);
    }

}
