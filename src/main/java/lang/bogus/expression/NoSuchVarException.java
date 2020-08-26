package lang.bogus.expression;

public class NoSuchVarException extends RuntimeException {
    public NoSuchVarException(String name) {
        super("No such var {" + name + "} in scope");
    }
}
