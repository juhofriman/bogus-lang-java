package lang.bogus.lexer;

public abstract class BogusToken {

    private final RawLiteral literal;

    protected BogusToken(RawLiteral literal) {
        this.literal = literal;
    }

    protected abstract TokenType type();

    @Override
    public String toString() {
        return this.type() + "(" + this.literal.literal + ")" + " ["
                + this.literal.line + "|" + this.literal.column + "]";
    }
}