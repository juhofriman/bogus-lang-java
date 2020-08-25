package lang.bogus;

import lang.bogus.lexer.BogusLexer;
import lang.bogus.parser.BogusParser;
import lang.bogus.runtime.BogusScope;
import lang.bogus.statement.BogusStatement;
import lang.bogus.value.Value;

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
                System.out.print("bogus> ");
                return scanner.nextLine();
            };

            String quit = ":quit";
            String lex = ":lex ";
            String parse = ":parse ";
            BogusScope root = new BogusScope();
            Function<String, String> expressionHandler = expression -> {

                try {
                    if (quit.equals(expression)) {
                        return quit;
                    }

                    if(expression.trim().isEmpty()) {
                        return expression;
                    }

                    if (expression.startsWith(lex)) {
                        BogusLexer bogusLexer = new BogusLexer(expression.substring(lex.length()));
                        System.out.println(bogusLexer.getTokens());
                        return expression;
                    }

                    if (expression.startsWith(parse)) {
                        BogusParser bogusParser = new BogusParser(new BogusLexer(expression.substring(parse.length())));
                        System.out.println(bogusParser.parse());
                        return expression;
                    }
                    BogusParser bogusParser = new BogusParser(new BogusLexer(expression));
                    Value r = null;
                    for (BogusStatement bogusStatement : bogusParser.parse()) {
                        r = bogusStatement.evaluate(root);
                    }
                    System.out.println(r.asString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return expression;

            };

            Predicate<String> quitCommand = (command) -> quit.equalsIgnoreCase(command.trim());

            Stream.generate(input).map(expressionHandler).noneMatch(quitCommand);
        }
    }
}
