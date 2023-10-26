package seedu.address.model.note;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Note's title.
 */
public class NoteTitle {
    public static final String MESSAGE_CONSTRAINTS = "Note title should not be blank";
    /*
     * The first character of the note title must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String title;

    private NoteTitle(String title) {
        requireNonNull(title);
        checkArgument(isValidNoteTitle(title), MESSAGE_CONSTRAINTS);
        this.title = title;
    }

    /**
     * Constructs a NoteTitle.
     * @param title The title of the note.
     */
    public static NoteTitle fromString(String title) {
        return new NoteTitle(title);
    }

    /**
     * Returns true if a given string is a valid note title.
     * @param test The string to be tested.
     * @return A boolean indicating whether the string is valid.
     */
    public static boolean isValidNoteTitle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteTitle)) {
            return false;
        }

        NoteTitle otherNoteTitle = (NoteTitle) other;
        return title.equals(otherNoteTitle.title);
    }

    @Override
    public String toString() {
        return this.title;
    }
}
