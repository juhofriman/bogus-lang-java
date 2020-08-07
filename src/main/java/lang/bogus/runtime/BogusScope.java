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

    public void store(Identifier identifier, Value value) {
        this.registry.put(identifier.getName(), value);
    }

    public Value resolve(Identifier identifier) {
        return this.registry.get(identifier.getName());
    }

}
