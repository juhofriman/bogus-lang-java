package lang.bogus.lexer;

/**
 * Created by juhof on 29.7.2020.
 */
public class RawLiteral {
    public int line = 1;
    public int column = 1;
    public String literal;

    public RawLiteral(int line, int column, String literal) {
        this.line = line;
        this.column = column;
        this.literal = literal;
    }
}
