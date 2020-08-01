package lang.bogus.lexer;

import lang.bogus.statement.BogusStatement;
import lang.bogus.statement.Expression;

public abstract class BogusToken {

    private final RawLiteral literal;

    protected BogusToken(RawLiteral literal) {
        this.literal = literal;
    }

    protected abstract TokenType type();

    public RawLiteral getLiteral() {
        return literal;
    }

    @Override
    public String toString() {
        return this.type() + "(" + this.literal.literal + ")" + " ["
                + this.literal.line + "|" + this.literal.column + "] " + this.getClass();
    }

    public abstract BogusStatement parse(BogusLexer lexer);

    public String positionString() {
        return this.literal.line + ":" + this.literal.column;
    }

    protected final Expression parseExpression(BogusLexer lexer) {
        return this.parseExpression(lexer, 0);
    }

    protected final Expression parseExpression(BogusLexer lexer, int precedence) {
        Expression leftExpression = this.parsePrefix(lexer);
       if (!lexer.hasNext()) {
           return leftExpression;
        }
        while (lexer.lookahead(0) != null && precedence < lexer.lookahead(0).type().getInfixBindingPower()) {
            leftExpression = lexer.next().parseInfix(lexer, leftExpression);
        }
        return leftExpression;
    }

    public Expression parsePrefix(BogusLexer lexer) {
        System.out.println("Dunno how to parse prefix: " + this.type());
        return null;
    }

    public Expression parseInfix(BogusLexer lexer, Expression left) {
        System.out.println("Dunno how to parse infix: " + this.type());
        return null;
    }
}