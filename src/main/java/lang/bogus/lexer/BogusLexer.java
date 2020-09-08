package lang.bogus.lexer;

import lang.bogus.lexer.token.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

class RawTokenizer {

    /** Source reader */
    private final StringReader reader;

    /** Current line number */
    private int line = 1;
    /** Current column number */
    private int column = 0;
    /** Current character ascii code */
    private int current = -1;

    public RawTokenizer(String code) {
        this.reader = new StringReader(code);
        // Proceed to initial state
        this.readAndIncrement();
    }

    /**
     * Reads next character from source.
     *
     * Increments line and column positions currectly.
     */
    private void readAndIncrement() {
        try {
            this.column++;
            this.current = this.reader.read();
            if (this.current == 10) {
                this.line++;
                this.column = 1;
            }
        } catch (IOException e) {
            throw new RuntimeException("IO Error while consuming stream", e);
        }
    }

    /**
     * Consumes stream until non whitespace character is encountered
     */
    private void consumeWhitespaces() {
        while (Character.isWhitespace(this.current)) {
            this.readAndIncrement();
        }
    }

    /**
     * Tells if given character is "stopping", which means it's token by itself.
     *
     * Such as ( or } or = or, you know.
     *
     * @param c
     * @return
     */
    private boolean isStopping(int c) {
        return c == 40 || // (
                c == 41 || // )
                c == 123 || // {
                c == 125 || // }
                c == 43 || // +
                c == 45 || // -
                c == 42 || // *
                c == 47 || // /
                c == 44 || // ,
                c == 59 || // ;
                c == 61; // =
    }


    /**
     * Consumes next token from source code.
     *
     * Returns mapped BogusToken or Optinal.empty if no more tokens are encountered.
     *
     */
    public Optional<BogusToken> consumeNext() {

        // Consume extra whitespaces until meaningful character is encountered
        this.consumeWhitespaces();

        // Remember the starting column of token
        int startingColumn = this.column;

        // End of file encountered
        if(this.current == -1) {
            return Optional.empty();
        }

        StringBuffer sb = new StringBuffer();

        if(Character.isDigit(this.current)) {
            // Token must be int (number)
            // TODO: floats could be handled here also, allow . and if contains dot, it is a floating point literal
            while (Character.isDigit(this.current)) {
                this.readToBuffer(sb);
            }
            return Optional.of(
                    new IntToken(new RawLiteral(this.line, startingColumn, sb.toString()))
            );
        } else if(this.current == 34) {
            // It's a string
            // Pop string delimitter out
            this.readAndIncrement();
            while(this.current != 34) {
                this.readToBuffer(sb);
                if(this.current == -1) {
                    throw new RuntimeException("EOF encountered and non matching end of string");
                }
            }
            // Pop trailing string delimitter out
            this.readAndIncrement();
            return Optional.of(
                    new StringToken(new RawLiteral(this.line, startingColumn, sb.toString()))
            );
        } else if(this.isStopping(this.current)) {
            // It's a token by itself
            this.readToBuffer(sb);
            return Optional.of(
                    mapReservedWordsOrFallToIdentifier(new RawLiteral(this.line, startingColumn, sb.toString()))
            );
        } else {
            // Token is reserved word or identifier
            do {
                this.readToBuffer(sb);
            } while (this.current != -1 && !Character.isWhitespace(this.current) && !this.isStopping(this.current));
            return Optional.of(
                    mapReservedWordsOrFallToIdentifier(new RawLiteral(this.line, startingColumn, sb.toString()))
            );
        }
    }

    private BogusToken mapReservedWordsOrFallToIdentifier(RawLiteral literal) {
        switch (literal.literal) {
            case "let":
                return new LetToken(literal);
            case "fun":
                return new FunToken(literal);
            case "return":
                return new ReturnToken(literal);
            case "(":
                return new LeftParensToken(literal);
            case ")":
                return new RightParensToken(literal);
            case "{":
                return new LeftBraceToken(literal);
            case "}":
                return new RightBraceToken(literal);
            case "=":
                return new EqualsToken(literal);
            case "+":
                return new PlusToken(literal);
            case "-":
                return new MinusToken(literal);
            case "*":
                return new MultiplicationToken(literal);
            case "/":
                return new DivisionToken(literal);
            case ",":
                return new CommaToken(literal);
            case ";":
                return new SemicolonToken(literal);
            default:
                return new IdentifierToken(literal);
        }
    }

    /**
     * Reads current character to given StringBuffer and
     * moves pointer to next character
     *
     * @param sb
     */
    private void readToBuffer(StringBuffer sb) {
        sb.append(Character.toChars(this.current));
        this.readAndIncrement();
    }
}

public class BogusLexer {

    private List<BogusToken> tokens = new ArrayList<>();
    private int pointer = 0;

    public BogusLexer(String source) {
        RawTokenizer rawTokenizer = new RawTokenizer(source);
        Optional<BogusToken> rawLiteral = rawTokenizer.consumeNext();
        while (rawLiteral.isPresent()) {
            tokens.add(rawLiteral.get());
            rawLiteral = rawTokenizer.consumeNext();
        }
    }


    public List<BogusToken> getTokens() {
        return this.tokens;
    }

    public BogusToken next() {
        if(!this.hasNext()) {
            return null;
        }
        BogusToken bogusToken = this.tokens.get(this.pointer);
        this.pointer++;
        return bogusToken;
    }

    public BogusToken next(TokenType type) {
        BogusToken next = this.next();
        if(!next.type().equals(type)) {
            throw new ExpectingTokenException(type, next);
        }
        return next;
    }

    public BogusToken peek() {
        return this.lookahead(1);
    }

    public BogusToken lookahead(int i) {
        if(!this.hasNext()) {
            return null;
        }
        int index = this.pointer + i;
        if(index >= this.tokens.size()) {
            return null;
        }
        BogusToken bogusToken = this.tokens.get(index);
        return bogusToken;
    }

    public boolean hasNext() {
        return this.pointer < this.tokens.size();
    }

    public BogusToken current() {
        return this.lookahead(0);
    }
}
