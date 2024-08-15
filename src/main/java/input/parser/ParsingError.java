package input.parser;

public class ParsingError extends RuntimeException {
    public ParsingError(String s) {
        super(s);
    }
}
