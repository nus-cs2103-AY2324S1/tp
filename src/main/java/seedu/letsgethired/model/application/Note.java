package seedu.letsgethired.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.letsgethired.commons.util.AppUtil.checkArgument;

/**
 * Represents an InternApplication's note in the InternTracker
 * Guarantees: immutable; is always valid
 */
public class Note {

    public static final String MESSAGE_CONSTRAINTS =
            "Note insertion must be followed by the prefix 'i/' "
                    + "and should not contain any slashes, and it should not be blank\n"
                    + "Example: note 1 i/Need to brush up on database querying\n"
                    + "Note deletion must be followed by the prefix 'o/' and must only contain the index of the note.\n"
                    + "Example: 'note 1 o/3' will delete the 3rd note of the first InternApplication";

    public static final String VALIDATION_REGEX = "^[^/\\\\\\\\]*$";


    public final String value;

    /**
     * Constructs an {@code Note}.
     *
     * @param note A valid note.
     */
    public Note(String note) {
        requireNonNull(note);
        checkArgument(isValidNote(note), MESSAGE_CONSTRAINTS);
        value = note;
    }

    /**
     * Returns true if a given string is a valid note.
     */
    public static boolean isValidNote(String test) {
        return !test.isBlank() && test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Note)) {
            return false;
        }

        Note otherNote = (Note) other;
        return value.equals(otherNote.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
