package seedu.address.model.localcourse;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.EDGE_CASE_VALID_LOCAL_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_CODE;

import org.junit.jupiter.api.Test;

public class LocalCodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LocalCode(null));
    }

    @Test
    public void constructor_invalidLocalCode_throwsIllegalArgumentException() {
        String invalidLocalCode = "";
        assertThrows(IllegalArgumentException.class, () -> new LocalCode(invalidLocalCode));
    }

    @Test
    public void isValidLocalCode() {
        // null localCode
        assertThrows(NullPointerException.class, () -> LocalCode.isValidLocalCode(null));

        // invalid localCode
        assertFalse(LocalCode.isValidLocalCode("")); // empty string
        assertFalse(LocalCode.isValidLocalCode(" ")); // spaces only
        assertFalse(LocalCode.isValidLocalCode("^")); // only non-alphanumeric characters
        assertFalse(LocalCode.isValidLocalCode("potato*")); // contains non-alphanumeric characters

        // valid localCode
        assertTrue(LocalCode.isValidLocalCode("CSCSCS")); // alphabets only
        assertTrue(LocalCode.isValidLocalCode("T151")); // first character alphabet, followed by number
        assertTrue(LocalCode.isValidLocalCode(TYPICAL_LOCAL_COURSE_CODE)); // alphanumeric characters
    }

    @Test
    public void equals() {
        LocalCode localCode = new LocalCode(TYPICAL_LOCAL_COURSE_CODE);

        // same values -> returns true
        assertTrue(localCode.equals(new LocalCode(TYPICAL_LOCAL_COURSE_CODE)));

        // same object -> returns true
        assertTrue(localCode.equals(localCode));

        // null -> returns false
        assertFalse(localCode.equals(null));

        // different types -> returns false
        assertFalse(localCode.equals(5.0f));

        // different values -> returns false
        assertFalse(localCode.equals(new LocalCode(EDGE_CASE_VALID_LOCAL_COURSE_CODE)));
    }
}
