package lang.bogus.lexer.token;

import lang.bogus.lexer.BogusLexer;
import lang.bogus.lexer.RawLiteral;
import lang.bogus.statement.BogusStatement;
import lang.bogus.expression.Expression;

public class CommaToken extends BogusToken {
    public CommaToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    public TokenType type() {
        return TokenType.COMMA;
    }

    @Override
    public BogusStatement parse(BogusLexer lexer) {
        return null;
    }

    @Override
    public Expression parsePrefix(BogusLexer lexer) {
        return lexer.next().parseExpression(lexer);
    }

}
