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
}
