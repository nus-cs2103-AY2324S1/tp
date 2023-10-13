package seedu.address.commons.core.index;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.ArrayList;

/**
 * Represents a group of unique Index objects.
 * <p>
 * All Index objects in Indices have to be either zero-based or one-based.
 */
public class Indices {

    public static final String MESSAGE_DUPLICATE_INDEX = "Duplicate index detected.";
    private ArrayList<Index> zeroBasedIndices;
    private int size;

    /**
     * Indices can only be created by calling
     */
    private Indices(ArrayList<Index> zeroBasedIndices) {
        this.zeroBasedIndices = zeroBasedIndices;
        this.size = zeroBasedIndices.size();
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

    public static Indices fromZeroBased(int[] zeroBasedIndices) throws ParseException {
        ArrayList<Index> result = new ArrayList<>();

        for (int index : zeroBasedIndices) {
            Index zeroBasedIndex = Index.fromZeroBased(index);
            if (result.contains(zeroBasedIndex)) {
                throw new ParseException(MESSAGE_DUPLICATE_INDEX);
            }
            result.add(zeroBasedIndex);
        }
        return new Indices(result);
    }

    public static Indices fromOneBased(int[] oneBasedIndices) throws ParseException {
        ArrayList<Index> result = new ArrayList<>();

        for (int index : oneBasedIndices) {
            Index oneBasedIndex = Index.fromOneBased(index);
            if (result.contains(oneBasedIndex)) {
                throw new ParseException(MESSAGE_DUPLICATE_INDEX);
            }
            result.add(oneBasedIndex);
        }
        return new Indices(result);
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
        int[] zeroBasedIndices = this.getZeroBased();
        return new ToStringBuilder(this).add("zeroBasedIndices", zeroBasedIndices).toString();
    }
}
