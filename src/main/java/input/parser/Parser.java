package input.parser;

public interface Parser<T, R> {
    public R parse(T value);
}
