package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.note.Note;

public class JsonAdaptedNote {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Notes's %s field is missing!";

    private final String title;
    private final String body;

    /**
     * Constructs a {@code JsonAdaptedNote} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedNote(@JsonProperty("title") String title, @JsonProperty("body") String body) {
        this.title = title;
        this.body = body;
    }

    /**
     * Converts a given {@code Note} into this class for Jackson use.
     */
    public JsonAdaptedNote(Note source) {
        title = source.getTitle();
        body = source.getBody();

    }

    /**
     * Converts this Jackson-friendly adapted note object into the model's {@code Note} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Note.
     */
    public Note toModelType() throws IllegalValueException {

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "title"));
        }

        if (body == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,"body"));
        }
        
        return new Note(title, body);
    }
}
