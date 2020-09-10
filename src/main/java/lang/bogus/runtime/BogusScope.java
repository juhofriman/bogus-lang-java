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

    /** The "memory" or "registry" */
    private final Map<String, Value> registry = new HashMap<>();
    /** Reference to parent scope */
    private final BogusScope parentScope;

    public BogusScope() {
        // create root scope with "std"
        // "std" places stuff like println(), type() and all that in scope
        // those should be usable everywhere
        this.registry.putAll(BogusStd.create());
        this.parentScope = null;
    }

    public BogusScope(BogusScope parentScope) {
        // Create nested scope with reference to the parent
        this.parentScope = parentScope;
    }

    public void store(IdentifierExpression identifier, Value value) {
        // Store identifier -> value
        this.registry.put(identifier.getName(), value);
    }

    public Value resolve(IdentifierExpression identifier) {
        // Resolve value with given identifier
        Value value = this.registry.get(identifier.getName());
        // Yes, I know, null, BAD
        if(value == null) {
            // identifier was not found in scope, maybe it is in parent?
            if(this.parentScope != null) {
                return this.parentScope.resolve(identifier);
            }
            // Nope, we did not manage to resolve "myVariable"
            // Yes, again, null, I know. BAD
            return null;
        } else {
            return value;
        }
    }

    public BogusScope join(BogusScope closure) {
        // This is just a first try to implement closures
        // Kinda works...
        this.registry.putAll(closure.registry);
        return this;
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
}
