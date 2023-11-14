package seedu.address.commons.util;

/**
 * Interface for a function that may or may not throw an exception when executing.
 */
@FunctionalInterface
public interface CheckedFunction<T, R, E extends Exception> {
    R apply(T t) throws E;
}
