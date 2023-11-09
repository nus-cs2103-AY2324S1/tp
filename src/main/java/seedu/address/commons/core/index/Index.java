package seedu.address.commons.core.index;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a zero-based or one-based index.
 *
 * {@code Index} should be used right from the start (when parsing in a new user input), so that if the current
 * component wants to communicate with another component, it can send an {@code Index} to avoid having to know what
 * base the other component is using for its index. However, after receiving the {@code Index}, that component can
 * convert it back to an int if the index will not be passed to a different component again.
 */
public class Index {
    private int zeroBasedIndex;

    /**
     * Index can only be created by calling {@link Index#fromZeroBased(int)} or
     * {@link Index#fromOneBased(int)}.
     */
    private Index(int zeroBasedIndex) {
        if (zeroBasedIndex < 0) {
            throw new IndexOutOfBoundsException();
        }

        this.zeroBasedIndex = zeroBasedIndex;
    }

    public int getZeroBased() {
        return zeroBasedIndex;
    }

    public int getOneBased() {
        return zeroBasedIndex + 1;
    }

    /**
     * Returns the default index. The default index is created with a value of
     * Integer.MAX_VALUE.
     *
     * @return The default index with a value of Integer.MAX_VALUE.
     */
    public static Index getDefaultIndex() {
        return new Index((Integer.MAX_VALUE));
    }

    /**
     * Checks if the index is present. An index is considered present if its value
     * is not equal to Integer.MAX_VALUE.
     *
     * @return {@code true} if the index is present; {@code false} otherwise.
     */
    public boolean isPresent() {
        return zeroBasedIndex != Integer.MAX_VALUE;
    }

    /**
     * Creates a new {@code Index} using a zero-based index.
     */
    public static Index fromZeroBased(int zeroBasedIndex) {
        return new Index(zeroBasedIndex);
    }

    /**
     * Creates a new {@code Index} using a one-based index.
     */
    public static Index fromOneBased(int oneBasedIndex) {
        return new Index(oneBasedIndex - 1);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Index)) {
            return false;
        }

        Index otherIndex = (Index) other;
        return zeroBasedIndex == otherIndex.zeroBasedIndex;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("zeroBasedIndex", zeroBasedIndex).toString();
    }
}
