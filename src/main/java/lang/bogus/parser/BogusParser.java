package lang.bogus.parser;

import lang.bogus.lexer.BogusLexer;
import lang.bogus.lexer.BogusToken;
import lang.bogus.statement.BogusStatement;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by juhof on 31.7.2020.
 */
public class BogusParser {

    private final BogusLexer lexer;
    private final List<BogusStatement> statements = new LinkedList();

    public BogusParser(BogusLexer lexer) {
        this.lexer = lexer;
    }

    public List<BogusStatement> parse() {
        while(lexer.hasNext()) {
            BogusToken next = lexer.next();
            BogusStatement parsed = next.parse(lexer);
            if(parsed != null) {
                statements.add(parsed);
            }
        }
        return this.statements;
    }
}
