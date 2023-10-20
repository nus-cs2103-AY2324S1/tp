package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedNote.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalNotes.NOTE_A;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.note.NoteContent;
import seedu.address.model.note.NoteTitle;

public class JsonAdaptedNoteTest {
    private static final String VALID_TITLE = "Hello World!";
    private static final String VALID_CONTENT = "CS2103T is the best module I've taken.";

    @Test
    public void toModelType_validNoteDetails_returnsNote() throws Exception {
        JsonAdaptedNote note = new JsonAdaptedNote(NOTE_A);
        assertEquals(NOTE_A, note.toModelType());
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedNote note = new JsonAdaptedNote(null, VALID_CONTENT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, NoteTitle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, note::toModelType);
    }

    @Test
    public void toModelType_nullContent_throwsIllegalValueException() {
        JsonAdaptedNote note = new JsonAdaptedNote(VALID_TITLE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, NoteContent.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, note::toModelType);
    }
}
