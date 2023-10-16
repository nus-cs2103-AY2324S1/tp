package seedu.address.model.note;

/**
 * Represents a Note's content.
 */
public class NoteContent {
    private final String content;

    private NoteContent(String content) {
        this.content = content;
    }

    /**
     * Constructs a NoteContent.
     * @param content The content of the note.
     */
    public static NoteContent fromString(String content) {
        return new NoteContent(content);
    }

    @Override
    public String toString() {
        return this.content;
    }
}
