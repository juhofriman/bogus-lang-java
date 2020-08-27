package lang.bogus.runtime;

import lang.bogus.lang.bogus.inspector.Deck;
import lang.bogus.expression.IdentifierExpression;
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
        this.registry = BogusStd.create();
    }

    public BogusScope(BogusScope parentScope) {
        this.parentScope = parentScope;
    }

    public void store(IdentifierExpression identifier, Value value) {
        Deck.message("Storing " + identifier);
        this.registry.put(identifier.getName(), value);
    }

    public Value resolve(IdentifierExpression identifier) {

        Value value = this.registry.get(identifier.getName());
        if(value == null) {
            Deck.message("resolving " + identifier + "from parent");
            if(this.parentScope != null) {
                return this.parentScope.resolve(identifier);
            }
            return null;
        } else {
            Deck.message("resolved " + identifier);
            return value;
        }
    }

}
