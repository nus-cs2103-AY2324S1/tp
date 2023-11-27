package seedu.address.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix {
    private final String prefix;

    /**
     * Constructs a Prefix with a specified prefix string.
     *
     * @param prefix The prefix string to mark the beginning of an argument.
     */
    public Prefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Retrieves the prefix string.
     *
     * @return The prefix string.
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Returns the prefix string as the representation of this object.
     *
     * @return The prefix string representation.
     */
    @Override
    public String toString() {
        return getPrefix();
    }

    /**
     * Generates a hash code for this Prefix.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return prefix == null ? 0 : prefix.hashCode();
    }

    /**
     * Checks if this Prefix is equal to another object.
     *
     * @param other The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Prefix)) {
            return false;
        }

        Prefix otherPrefix = (Prefix) other;
        return prefix.equals(otherPrefix.prefix);
    }
}
