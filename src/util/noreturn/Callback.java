package util.noreturn;

@FunctionalInterface
public interface Callback<T> {
    void call(T t);
}
