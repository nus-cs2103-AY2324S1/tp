package seedu.address.model.note;

/**
 * Represents a Note's title.
 */
public class NoteTitle {
    private final String title;

    private NoteTitle(String title) {
        this.title = title;
    }

    /**
     * Constructs a NoteTitle.
     * @param title The title of the note.
     */
    public static NoteTitle fromString(String title) {
        return new NoteTitle(title);
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
