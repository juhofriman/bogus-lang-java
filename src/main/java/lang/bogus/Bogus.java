package lang.bogus;

import lang.bogus.lexer.BogusLexer;
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

    private static String readSource(String filePath) {
        String content = "";

        try {
            content = new String ( Files.readAllBytes(Paths.get(filePath)) );
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    public static void main( String[] args ) {
        if(args.length == 0) {
            System.out.println("Pass filename");
        }

        BogusScope bogusScope = new BogusScope();
        List<BogusStatement> statements = new BogusParser(new BogusLexer(readSource(args[0]))).parse();
        Value value = null;
        for (BogusStatement statement : statements) {
            statement.evaluate(bogusScope);
        }
        System.out.println(value.asString());

    }
}
