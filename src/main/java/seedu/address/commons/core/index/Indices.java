package seedu.address.commons.core.index;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents one or more sorted, unique Index objects.
 * <p>
 * All Index objects in Indices have to be either zero-based or one-based. Similar to {@code Index},
 * it should be used right from the start when parsing new user input that allows for more than
 * one index provided.
 */
public class Indices {
    private final SortedSet<Index> zeroBasedIndices;
    private int size;

    /**
     * Indices can only be created by calling {@link Indices#fromZeroBased} or
     * {@link Indices#fromOneBased(int[])}.
     */
    private Indices(SortedSet<Index> zeroBasedIndices) {
        this.zeroBasedIndices = zeroBasedIndices;
        this.size = zeroBasedIndices.size();
    }

    /**
     * Creates a new {@code Indices} using zero-based indices.
     */
    public static Indices fromZeroBased(int[] zeroBasedIndices) {
        SortedSet<Index> result = new TreeSet<>();

        for (int index : zeroBasedIndices) {
            Index zeroBasedIndex = Index.fromZeroBased(index);
            result.add(zeroBasedIndex);
        }
        return new Indices(result);
    }

    /**
     * Creates a new {@code Indices} using one-based indices.
     */
    public static Indices fromOneBased(int[] oneBasedIndices) {
        SortedSet<Index> result = new TreeSet<>();

        for (int index : oneBasedIndices) {
            Index oneBasedIndex = Index.fromOneBased(index);
            result.add(oneBasedIndex);
        }
        return new Indices(result);
    }

    public int[] getZeroBased() {
        return zeroBasedIndices.stream()
                .mapToInt(Index::getZeroBased)
                .toArray();
    }

    public int[] getOneBased() {
        return zeroBasedIndices.stream()
                .mapToInt(Index::getOneBased)
                .toArray();
    }

    /**
     * Returns a white space separated string of zero-based indices.
     */
    public String getZeroBasedString() {
        int[] zeroBased = this.getZeroBased();

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size - 1; i++) {
            result.append(zeroBased[i])
                    .append(" ");
        }
        result.append(zeroBased[size - 1]);
        return result.toString();
    }

    /**
     * Returns a white space separated string of one-based indices.
     */
    public String getOneBasedString() {
        int[] oneBased = this.getOneBased();

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size - 1; i++) {
            result.append(oneBased[i])
                    .append(" ");
        }
        result.append(oneBased[size - 1]);
        return result.toString();
    }

    public int getSize() {
        return this.size;
    }

    /**
     * Returns the smallest zero-based index in indices.
     */
    public int getZeroBasedMin() {
        return Collections.min(this.zeroBasedIndices).getZeroBased();
    }

    /**
     * Returns the largest zero-based index in indices.
     */
    public int getZeroBasedMax() {
        return Collections.max(this.zeroBasedIndices).getZeroBased();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Indices)) {
            return false;
        }

        Indices otherIndices = (Indices) other;

        if (this.size != otherIndices.size) {
            return false;
        }
        return this.zeroBasedIndices.equals(otherIndices.zeroBasedIndices);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("zeroBasedIndices",
                this.getZeroBasedString()).toString();
    }
}
