package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.EnrolDate;
import seedu.address.model.tag.Subject;

public class JsonAdaptedTagTest {
    private static final String INVALID_SUBJECT = "asdfghjkl";
    private static final String VALID_SUBJECT = "Chinese";
    private static final String VALID_DATE = "Jun 2023";

    @Test
    public void toModelType_validSubjectName_returnsSubject() throws Exception {
        JsonAdaptedTag subject = new JsonAdaptedTag(VALID_SUBJECT);
        Subject expected = new Subject("Chinese");
        EnrolDate date = new EnrolDate(VALID_DATE);
        assertEquals(expected, subject.toModelType(date));
    }

    @Test
    public void toModelType_invalidSubjectName_throwsIllegalValueException() {
        JsonAdaptedTag subject = new JsonAdaptedTag(INVALID_SUBJECT);
        EnrolDate date = new EnrolDate(VALID_DATE);
        String expectedMessage = Subject.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> subject.toModelType(date));
    }
}
