package lang.bogus.value;

public class VoidValue implements Value {
    public static final VoidValue VOID = new VoidValue();

    private VoidValue() {

    }

    @Override
    public String asString() {
        return "";
    }

    @Override
    public String typeString() {
        return "void";
    }
}
