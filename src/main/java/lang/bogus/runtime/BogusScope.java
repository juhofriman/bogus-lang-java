package lang.bogus.runtime;

import lang.bogus.statement.Identifier;
import lang.bogus.value.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by juhof on 1.8.2020.
 */
public class BogusScope {

    private Map<String, Value> registry = new HashMap<>();
    private BogusScope parentScope;

    public BogusScope() {

    }

    public BogusScope(BogusScope parentScope) {
        this.parentScope = parentScope;
    }

    public void store(Identifier identifier, Value value) {
        System.out.println("Storing " + identifier);
        this.registry.put(identifier.getName(), value);
    }

    public Value resolve(Identifier identifier) {

        Value value = this.registry.get(identifier.getName());
        if(value == null) {
            System.out.println("resolving " + identifier + "from parent");
            if(this.parentScope != null) {
                return this.parentScope.resolve(identifier);
            }
            return null;
        } else {
            System.out.println("resolved " + identifier);
            return value;
        }
    }

}
