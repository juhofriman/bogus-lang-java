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

    private void echoIndented(int indentation, String message) {
        // OMG
        for(int i = 0; i < indentation; i++) {
            System.out.print(" ");
        }
        System.out.println(message);
    }

    public void echoScope(int indentation) {
        this.echoIndented(indentation, "{");
        for (Map.Entry<String, Value> keys : this.registry.entrySet()) {
            this.echoIndented(indentation + 1, keys.getKey() + " -> " + keys.getValue());
        }
        if(this.parentScope != null) {
            this.parentScope.echoScope(indentation + 1);
        }
        this.echoIndented(indentation, "}");
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
