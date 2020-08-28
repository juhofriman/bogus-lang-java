package lang.bogus.lang.bogus.inspector;

/**
 * This is just a weird wu-tang joke...
 */
public class Deck {

    private static boolean output = true;

    private Deck() {}

    public static void message(Object message) {
        if(output) {
            System.out.println("\t[DECK] " + message);
        }
    }

    public static void toggle() {
        output = !output;
    }
}
