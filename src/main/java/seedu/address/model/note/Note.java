package seedu.address.model.note;

/**
 * Represents a Note.
 */
public class Note {
    private final NoteTitle title;
    private final NoteContent content;

    /**
     * Constructs a Note.
     * @param title The title of the note.
     * @param content The content of the note.
     */
    public Note(String title, String content) {
        this.title = NoteTitle.fromString(title);
        this.content = NoteContent.fromString(content);
    }

    /**
     * Returns the title of the note.
     * @return The title of the note.
     */
    public String getTitle() {
        return title.toString();
    }

    /**
     * Returns the content of the note.
     * @return The content of the note.
     */
    public String getContent() {
        return content.toString();
    }

    /**
     * Returns a string that can be used to represent this note on GUI.
     * @return The information in string.
     */
    public String getUiText() {
        String result = this.getTitle() + " (content: " + this.getContent() + ")";
        return result;
    }

    /**
     * Returns true if both notes have the same identity and data fields.
     * This defines a stronger notion of equality between two notes.
     */
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
        return title.equals(otherNote.title)
                && content.equals(otherNote.content);
    }
}
