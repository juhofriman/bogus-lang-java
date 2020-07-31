package lang.bogus.lexer;

/**
 * Created by juhof on 29.7.2020.
 */
class RawLiteral {
    int line = 1;
    int column = 1;
    String literal;

    public RawLiteral(int line, int column, String literal) {
        this.line = line;
        this.column = column;
        this.literal = literal;
    }
}
