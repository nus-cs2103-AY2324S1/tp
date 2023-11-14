package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.note.Note;
import seedu.address.model.note.NoteContent;
import seedu.address.model.note.NoteTitle;

/**
 * Jackson-friendly version of {@link Note}.
 */
public class JsonAdaptedNote {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Notes's %s field is missing!";

    private final String title;
    private final String content;

    /**
     * Constructs a {@code JsonAdaptedNote} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedNote(@JsonProperty("title") String title, @JsonProperty("content") String content) {
        this.title = title;
        this.content = content;
    }

    /**
     * Converts a given {@code Note} into this class for Jackson use.
     */
    public JsonAdaptedNote(Note source) {
        title = source.getTitle();
        content = source.getContent();

    }

    /**
     * Converts this Jackson-friendly adapted note object into the model's
     * {@code Note} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted Note.
     */
    public Note toModelType() throws IllegalValueException {

        if (title == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, NoteTitle.class.getSimpleName()));
        }

        if (content == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, NoteContent.class.getSimpleName()));
        }

        return new Note(title, content);
    }
}
