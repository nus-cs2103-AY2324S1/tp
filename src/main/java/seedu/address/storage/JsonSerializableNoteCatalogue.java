package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.NoteCatalogue;
import seedu.address.model.ReadOnlyNoteCatalogue;
import seedu.address.model.note.Note;

/**
 * An Immutable NoteCatalogue that is serializable to JSON format.
 */
@JsonRootName(value = "notecatalogue")

public class JsonSerializableNoteCatalogue {
    public static final String MESSAGE_DUPLICATE_LOCAL_COURSE = "Note catalogue contains duplicate local "
            + "course(s).";

    private final List<JsonAdaptedNote> notes = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableNoteCatalogue} with the given notes.
     */
    @JsonCreator
    public JsonSerializableNoteCatalogue(@JsonProperty("notes") List<JsonAdaptedNote>
                                                        noteList) {
        this.notes.addAll(noteList);
    }

    /**
     * Converts a given {@code ReadOnlyNoteCatalogue} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableNoteCatalogue}.
     */
    public JsonSerializableNoteCatalogue(ReadOnlyNoteCatalogue source) {
        notes.addAll(source.getNoteList().stream().map(
                JsonAdaptedNote::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code NoteCatalogue} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public NoteCatalogue toModelType() throws IllegalValueException {
        NoteCatalogue noteCatalogue = new NoteCatalogue();
        for (JsonAdaptedNote jsonAdaptedNote : notes) {
            Note note = jsonAdaptedNote.toModelType();
            if (noteCatalogue.hasNote(note)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_LOCAL_COURSE);
            }
            noteCatalogue.addNote(note);
        }
        return noteCatalogue;
    }


}
