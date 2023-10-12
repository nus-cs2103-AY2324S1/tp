package seedu.address.model.note;

/**
 * The class for holding a Note
 */
public class Note {
    private final String title;
    private final String body;

    /**
     * The constructor for the Note class
     * @param title The title of the note
     * @param body The body of the note
     */
    public Note(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
