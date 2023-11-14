package seedu.address.model.unique;

/**
 * A Unique object with behavior define by isDuplicate method
 * @param <T>
 */
public interface Unique<T> {
    public boolean isDuplicate(T other);
}
