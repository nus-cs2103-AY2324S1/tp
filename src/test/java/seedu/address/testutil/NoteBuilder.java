package seedu.address.testutil;

import seedu.address.model.note.Note;
import seedu.address.model.note.NoteContent;
import seedu.address.model.note.NoteTitle;

/**
 * A utility class to help with building Note objects.
 */
public class NoteBuilder {
    public static final String DEFAULT_TITLE = "Labrador Park";
    public static final String DEFAULT_CONTENT = "Forgot to take picture";

    private NoteTitle title;
    private NoteContent content;

    /**
     * Creates a {@code NoteBuilder} with the default details.
     */
    public NoteBuilder() {
        title = NoteTitle.fromString(DEFAULT_TITLE);
        content = NoteContent.fromString(DEFAULT_CONTENT);
    }

    /**
     * Initializes the NoteBuilder with the data of {@code noteToCopy}.
     */
    public NoteBuilder(Note noteToCopy) {
        title = NoteTitle.fromString(noteToCopy.getTitle());
        content = NoteContent.fromString(noteToCopy.getContent());
    }

    /**
     * Sets the {@code Title} of the {@code Note} that we are building.
     */
    public NoteBuilder withTitle(String title) {
        this.title = NoteTitle.fromString(title);
        return this;
    }

    /**
     * Sets the {@code Content} of the {@code Note} that we are building.
     */
    public NoteBuilder withContent(String content) {
        this.content = NoteContent.fromString(content);
        return this;
    }

    public Note build() {
        return new Note(title.toString(), content.toString());
    }
}
