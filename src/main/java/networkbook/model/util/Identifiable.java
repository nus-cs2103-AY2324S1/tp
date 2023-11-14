package networkbook.model.util;

/**
 * Represents a type of object that has an identity.
 * @param <T> The type of object.
 */
public interface Identifiable<T> {
    /**
     * Checks if another object is the same as this.
     * @param toCheck The other object to check.
     */
    boolean isSame(T toCheck);

    /**
     * Get the value for Json storage.
     */
    String getValue();
}
