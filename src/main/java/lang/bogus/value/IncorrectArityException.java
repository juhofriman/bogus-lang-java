package lang.bogus.value;

import lang.bogus.expression.IdentifierExpression;

public class IncorrectArityException extends RuntimeException {
    public IncorrectArityException(IdentifierExpression name, int expected, int received) {
        super("Incorrect arity while calling " + name + " expected " + expected +
                " arguments, but got " + received);
    }
}
