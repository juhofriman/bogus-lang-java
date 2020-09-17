package lang.bogus.value;

public class TypeValue extends Value {
    private Value value;

    public TypeValue(Value Value) {
        value = Value;
    }

    @Override
    public String asString() {
        return this.value.typeString();
    }

    @Override
    public String typeString() {
        return "Type";
    }
}
