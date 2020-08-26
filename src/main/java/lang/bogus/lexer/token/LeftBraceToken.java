package lang.bogus.lexer.token;

import lang.bogus.lang.bogus.inspector.Deck;
import lang.bogus.lexer.BogusLexer;
import lang.bogus.lexer.RawLiteral;
import lang.bogus.statement.BogusStatement;
import lang.bogus.statement.GroupedStatement;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by juhof on 31.7.2020.
 */
public class LeftBraceToken extends BogusToken {
    public LeftBraceToken(RawLiteral literal) {
        super(literal);
    }

    @Override
    public TokenType type() {
        return TokenType.LEFT_BRACE;
    }

    @Override
    public BogusStatement parse(BogusLexer lexer) {
        List<BogusStatement> expressions = new LinkedList<>();
        BogusToken next = lexer.next();

        while(next != null && next.getLiteral().literal != "}") {
            BogusStatement parse = next.parse(lexer);
            if(parse != null) {
                expressions.add(parse);
            }
            next = lexer.next();

        }

        Deck.message(expressions);
        return new GroupedStatement(expressions);
    }

}
