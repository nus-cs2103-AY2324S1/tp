package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Object for the visual representation that user wants to export.
 */
public class Visual {
    public static final String MESSAGE_CONSTRAINTS =
            "Visual representation should only be TABLE or BAR.";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String visual;

    /**
     * All valid visual representations to be exported.
     */
    public enum ValidVisual { TABLE, BAR; }

    /**
     * Constructs a {@code Visual}.
     *
     * @param visual The visual representation type you want to export.
     */
    public Visual(String visual) {
        requireNonNull(visual);
        checkArgument(isValidVisual(visual), MESSAGE_CONSTRAINTS);
        this.visual = visual.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid visual in attribute.
     */
    public static boolean isValidVisual(String sequence) {
        try {
            ValidVisual enumValue = ValidVisual.valueOf(sequence.toUpperCase());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return visual;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Visual)) {
            return false;
        }

        Visual otherSortIn = (Visual) other;
        return visual.equals(otherSortIn.visual);
    }

    @Override
    public int hashCode() {
        return visual.hashCode();
    }
}
