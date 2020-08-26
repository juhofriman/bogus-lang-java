package lang.bogus;

import lang.bogus.lang.bogus.inspector.Deck;
import lang.bogus.lexer.BogusLexer;
import lang.bogus.lexer.token.BogusToken;
import lang.bogus.parser.BogusParser;
import lang.bogus.runtime.BogusScope;
import lang.bogus.statement.BogusStatement;
import lang.bogus.value.Value;

import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

enum Mode {
    LEX, PARSE, EVAL
}

public class BogusRepl {

    private Mode currentMode = Mode.EVAL;

    public static void main(String[] args) {
        new BogusRepl().run();
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {

            Supplier<String> input = () -> {
                switch (currentMode) {
                    case LEX: {
                        System.out.print("lexus> ");
                        break;
                    }
                    case PARSE: {
                        System.out.print("parsus> ");
                        break;
                    }
                    case EVAL: {
                        System.out.print("bogus> ");
                        break;
                    }
                }
                return scanner.nextLine();
            };

            String quit = ":quit";
            String lex = ":lex";
            String parse = ":parse";
            String deck = ":deck";
            String eval = ":eval";
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
                        currentMode = Mode.LEX;
                        return expression;
                    }

                    if (expression.startsWith(parse)) {
                        currentMode = Mode.PARSE;
                        return expression;
                    }

                    if (expression.startsWith(deck)) {
                        Deck.toggle();
                        return expression;
                    }

                    if (expression.startsWith(eval)) {
                        currentMode = Mode.EVAL;
                        return expression;
                    }

                    BogusLexer bogusLexer = new BogusLexer(expression);
                    if(currentMode == Mode.LEX) {
                        System.out.println("[");
                        for (BogusToken token : bogusLexer.getTokens()) {
                            System.out.println("\t" + token);
                        }
                        System.out.println("]");
                        return expression;
                    }

                    BogusParser bogusParser = new BogusParser(bogusLexer);
                    if(currentMode == Mode.PARSE) {
                        System.out.println(bogusParser.parse());
                        return expression;
                    }

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
