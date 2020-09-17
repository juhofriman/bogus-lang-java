package lang.bogus.value;

public class BooleanValue extends Value {
    private boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }

    @Override
    public String asString() {
        return String.valueOf(value);
    }

    @Override
    String typeString() {
        return "boolean";
    }
}
