package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Note;

/**
 * Jackson-friendly version of {@link Note}.
 */
class JsonAdaptedNote {

    private final String noteText;

    /**
     * Constructs a {@code JsonAdaptedNote} with the given {@code noteText}.
     */
    @JsonCreator
    public JsonAdaptedNote(String noteText) {
        this.noteText = noteText;
    }

    /**
     * Converts a given {@code Note} into this class for Jackson use.
     */
    public JsonAdaptedNote(Note source) {
        noteText = source.toString();
    }

    @JsonValue
    public String getNoteText() {
        return noteText;
    }

    /**
     * Converts this Jackson-friendly adapted note object into the model's {@code Note} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted note.
     */
    public Note toModelType() throws IllegalValueException {
        // You can add more validation here if necessary, for now I'm directly converting it
        return new Note(noteText);
    }
}
