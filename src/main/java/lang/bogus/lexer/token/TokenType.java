package lang.bogus.lexer.token;

/**
 * Created by juhof on 29.7.2020.
 */
public enum TokenType {
    LET(null, 1),
    RETURN(null, 1),
    FUN(null, 1),
    LEFT_PARENS(0, 50),
    RIGHT_PARENS(10, 1),
    LEFT_BRACE(1, 50),
    RIGHT_BRACE(1, 1),
    INT(null, 1),
    STRING(1, 1),
    EQUALS(1, 1),
    PLUS(null, 5),
    MINUS(100, 10),
    MULTIPLICATION(null, 5),
    DIVISION(null, 9),
    IDENTIFIER(null, 1),
    COMMA(null, 0),
    SEMICOLON(null, 0);


    private final Integer prefixBindingPower;
    private final Integer infixBindingPower;

    TokenType(Integer prefixBindingPower, Integer infixBindingPower) {
        this.prefixBindingPower = prefixBindingPower;
        this.infixBindingPower = infixBindingPower;
    }

    public Integer getInfixBindingPower() {
        return infixBindingPower;
    }

    public Integer getPrefixBindingPower() {
        return prefixBindingPower;
    }
}
