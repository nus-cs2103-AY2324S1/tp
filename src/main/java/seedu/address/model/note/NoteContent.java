package seedu.address.model.note;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Note's content.
 */
public class NoteContent {
    public static final String MESSAGE_CONSTRAINTS = "Note content should not be blank";
    /*
     * The first character of the note content must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String content;

    private NoteContent(String content) {
        requireNonNull(content);
        checkArgument(isValidNoteContent(content), MESSAGE_CONSTRAINTS);
        this.content = content;
    }

    /**
     * Constructs a NoteContent.
     * @param content The content of the note.
     */
    public static NoteContent fromString(String content) {
        return new NoteContent(content);
    }

    /**
     * Returns true if a given string is a valid note content.
     * @param test The string to be tested.
     * @return A boolean indicating whether the string is valid.
     */
    public static boolean isValidNoteContent(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteContent)) {
            return false;
        }

        NoteContent otherNoteContent = (NoteContent) other;
        return content.equals(otherNoteContent.content);
    }

    @Override
    public String toString() {
        return this.content;
    }
}
