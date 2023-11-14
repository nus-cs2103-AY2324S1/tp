package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedUniversity.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.INVALID_UNIVERSITY_NAME;
import static seedu.address.testutil.TypicalObjects.NTU;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.messages.ConstraintMessage;
import seedu.address.model.university.UniversityName;

public class JsonAdaptedUniversityTest {
    @Test
    public void toModelType_validUniversityName_returnsUniversity() throws Exception {
        JsonAdaptedUniversity university = new JsonAdaptedUniversity(NTU);
        assertEquals(NTU, university.toModelType());
    }

    @Test
    public void toModelType_invalidUniversityName_throwsIllegalValueException() {
        JsonAdaptedUniversity university =
                new JsonAdaptedUniversity(INVALID_UNIVERSITY_NAME);
        String expectedMessage = ConstraintMessage.UNIVERSITY_NAME.toString();
        assertThrows(IllegalValueException.class, expectedMessage, university::toModelType);
    }

    @Test
    public void toModelType_nullUniversity_throwsIllegalValueException() {
        JsonAdaptedUniversity university = new JsonAdaptedUniversity((String) null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, UniversityName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, university::toModelType);
    }
}
