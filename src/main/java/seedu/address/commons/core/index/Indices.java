package seedu.address.commons.core.index;

import seedu.address.commons.util.ToStringBuilder;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents one or more sorted, unique Index objects.
 * <p>
 * All Index objects in Indices have to be either zero-based or one-based.
 */
public class Indices {
    private final ArrayList<Index> zeroBasedIndices;
    private int size;

    /**
     * Indices can only be created by calling
     */
    private Indices(ArrayList<Index> zeroBasedIndices) {
        this.zeroBasedIndices = zeroBasedIndices;
        this.size = zeroBasedIndices.size();
    }

    public static Indices fromZeroBased(int[] zeroBasedIndices) {
        Arrays.sort(zeroBasedIndices);
        ArrayList<Index> result = new ArrayList<>();

        for (int index : zeroBasedIndices) {
            Index zeroBasedIndex = Index.fromZeroBased(index);
            if (!result.contains(zeroBasedIndex)) {
                result.add(zeroBasedIndex);
            }
        }
        return new Indices(result);
    }

    public static Indices fromOneBased(int[] oneBasedIndices) {
        Arrays.sort(oneBasedIndices);
        ArrayList<Index> result = new ArrayList<>();

        for (int index : oneBasedIndices) {
            Index oneBasedIndex = Index.fromOneBased(index);
            if (!result.contains(oneBasedIndex)) {
                result.add(oneBasedIndex);
            }
        }
        return new Indices(result);
    }

    public int[] getZeroBased() {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = this.zeroBasedIndices.get(i).getZeroBased();
        }
        return result;
    }

    public int[] getOneBased() {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = this.zeroBasedIndices.get(i).getOneBased();
        }
        return result;
    }

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

    public int getZeroBasedMin() {
        return this.zeroBasedIndices.get(0).getZeroBased();
    }

    public int getZeroBasedMax() {
        return this.zeroBasedIndices.get(size - 1).getZeroBased();
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

        for (int i = 0; i < this.size; i++) {
            if (!this.zeroBasedIndices.get(i).equals(otherIndices.zeroBasedIndices.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("zeroBasedIndices",
                this.getZeroBasedString()).toString();
    }
}
