package util.returnable;

@FunctionalInterface
public interface Callback<P, R> {
    R call (P p);
}
