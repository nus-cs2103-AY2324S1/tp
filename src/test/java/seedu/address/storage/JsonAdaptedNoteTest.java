package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.messages.ConstraintMessage;
import seedu.address.model.note.Content;
import seedu.address.model.note.Note;
import seedu.address.testutil.TypicalObjects;

public class JsonAdaptedNoteTest {

    private static final String VALID_CONTENT1 = "Exchange Application Deadline: 25 September 2023";

    private static final String VALID_CONTENT2 = "SEP Country Choices: Canada, Australia";

    private static final Note VALID_NOTE1 = TypicalObjects.NOTE1;

    private static final Note VALID_NOTE2 = TypicalObjects.NOTE2;

    private static final List<JsonAdaptedTag> VALID_TAGS1 = VALID_NOTE1.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    private static final List<JsonAdaptedTag> VALID_TAGS2 = VALID_NOTE2.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    private static final Integer VALID_INDEX1 = 1;

    private static final Integer VALID_INDEX2 = 2;

    private static final Integer INVALID_INDEX = -1;


    @Test
    public void toModelType_validNoteDetails_returnsNote() throws Exception {
        JsonAdaptedNote note1 = new JsonAdaptedNote(VALID_NOTE1);
        assertEquals(VALID_NOTE1, note1.toModelType());

        JsonAdaptedNote note2 = new JsonAdaptedNote(VALID_NOTE2);
        assertEquals(VALID_NOTE2, note2.toModelType());
    }

    @Test
    public void toModelType_validNote_returnsNote() throws Exception {
        JsonAdaptedNote note1 = new JsonAdaptedNote(VALID_CONTENT1, VALID_TAGS1, VALID_INDEX1);
        assertEquals(VALID_NOTE1, note1.toModelType());

        JsonAdaptedNote note2 = new JsonAdaptedNote(VALID_CONTENT2, VALID_TAGS2, VALID_INDEX2);
        assertEquals(VALID_NOTE2, note2.toModelType());
    }

    @Test
    public void toModelType_invalidNoteNullContent_returnsNote() {
        JsonAdaptedNote note1 = new JsonAdaptedNote(null, VALID_TAGS1, VALID_INDEX1);
        String expectedMessage = String.format(JsonAdaptedNote.MISSING_FIELD_MESSAGE_FORMAT,
                Content.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, note1::toModelType);
    }

    @Test
    public void toModelType_invalidContent_returnsNote() {
        JsonAdaptedNote note1 = new JsonAdaptedNote("", VALID_TAGS1, VALID_INDEX1);
        String expectedMessage = ConstraintMessage.NOTE_CONTENT.toString();
        assertThrows(IllegalValueException.class, expectedMessage, note1::toModelType);
    }

    @Test
    public void toModelType_invalidIndex_returnsNote() {
        JsonAdaptedNote note1 = new JsonAdaptedNote("test", VALID_TAGS1, INVALID_INDEX);
        String expectedMessage = JsonAdaptedNote.INVALID_INDEX_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, note1::toModelType);
    }

}
