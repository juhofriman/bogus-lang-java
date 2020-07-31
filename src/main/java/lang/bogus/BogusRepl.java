package lang.bogus;

import lang.bogus.lexer.BogusLexer;

import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class BogusRepl {
    public static void main( String[] args ) {
        try (Scanner scanner = new Scanner(System.in)) {

            Supplier<String> input = () -> {
                System.out.print("> ");
                return scanner.nextLine();
            };

            String quit = ":quit";
            String lex = ":lex ";
            Function<String, String> expressionHandler = expression -> {

                if (quit.equals(expression)) {
                    return quit;
                }

                if (expression.startsWith(lex)) {
                    BogusLexer bogusLexer = new BogusLexer(expression.substring(lex.length()));
                    System.out.println(bogusLexer.getTokens());
                    return expression;
                }
                System.out.println("Evaluation of bogus not yet implemented");
                return expression;

            };

            Predicate<String> quitCommand = (command) -> quit.equalsIgnoreCase(command.trim());

            Stream.generate(input).map(expressionHandler).noneMatch(quitCommand);
        }
    }
}
