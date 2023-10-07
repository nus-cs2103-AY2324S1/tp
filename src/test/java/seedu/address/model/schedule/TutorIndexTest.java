package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class TutorIndexTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TutorIndex(null));
    }

    @Test
    public void constructor_invalidTutorIndex_throwsIllegalArgumentException() {
        Integer invalidTutorIndex = -1;
        assertThrows(IllegalArgumentException.class, () -> new TutorIndex(invalidTutorIndex));
    }

    @Test
    public void isValidTutorIndex() {
        // null TutorIndex number
        assertThrows(NullPointerException.class, () -> TutorIndex.isValidTutorIndex(null));

        // invalid TutorIndex numbers
        assertFalse(TutorIndex.isValidTutorIndex("")); // empty string
        assertFalse(TutorIndex.isValidTutorIndex(" ")); // spaces only
        assertFalse(TutorIndex.isValidTutorIndex("0")); // zero index
        assertFalse(TutorIndex.isValidTutorIndex("01")); // zero leading digits
        assertFalse(TutorIndex.isValidTutorIndex("-91")); // negative index
        assertFalse(TutorIndex.isValidTutorIndex("TutorIndex")); // non-numeric
        assertFalse(TutorIndex.isValidTutorIndex("9011p041")); // alphabets within digits
        assertFalse(TutorIndex.isValidTutorIndex("9312 1534")); // spaces within digits
        assertFalse(TutorIndex.isValidTutorIndex("1 2 3")); // spaces within digits

        // valid TutorIndex numbers
        assertTrue(TutorIndex.isValidTutorIndex("1"));
        assertTrue(TutorIndex.isValidTutorIndex("35"));
        assertTrue(TutorIndex.isValidTutorIndex("1834753")); // long TutorIndex number
    }

    @Test
    public void equals() {
        TutorIndex tutorIndex = new TutorIndex(999);

        // same values -> returns true
        assertTrue(tutorIndex.equals(new TutorIndex(999)));

        // same object -> returns true
        assertTrue(tutorIndex.equals(tutorIndex));

        // null -> returns false
        assertFalse(tutorIndex.equals(null));

        // different types -> returns false
        assertFalse(tutorIndex.equals(5.0f));

        // different values -> returns false
        assertFalse(tutorIndex.equals(new TutorIndex(995)));
    }
}
