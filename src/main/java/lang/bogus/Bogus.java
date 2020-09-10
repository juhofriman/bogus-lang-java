package lang.bogus;

import lang.bogus.lexer.BogusLexer;
import lang.bogus.lexer.token.BogusToken;
import lang.bogus.parser.BogusParser;
import lang.bogus.runtime.BogusScope;
import lang.bogus.statement.BogusStatement;
import lang.bogus.value.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class Bogus {


    public static void main( String[] args ) {
        if(args.length < 1) {
            System.out.println("Pass filename");
            System.exit(1);
        }

        BogusScope bogusScope = new BogusScope();

        BogusLexer bogusLexer = new BogusLexer(SourceReader.readSource(args[0]));

//        System.out.println("[");
//        for (BogusToken token : bogusLexer.getTokens()) {
//            System.out.println("\t" + token);
//        }
//        System.out.println("]");

        List<BogusStatement> statements = new BogusParser(bogusLexer).parse();

        for (BogusStatement statement : statements) {
            statement.evaluate(bogusScope);
        }
    }
}
