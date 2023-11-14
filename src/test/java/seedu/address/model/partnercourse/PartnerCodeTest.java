package seedu.address.model.partnercourse;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.EDGE_CASE_VALID_PARTNER_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE_CODE;

import org.junit.jupiter.api.Test;

public class PartnerCodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PartnerCode(null));
    }

    @Test
    public void isValidPartnerCode() {
        // null partnerCode
        assertThrows(NullPointerException.class, () -> PartnerCode.isValidPartnerCode(null));

        // invalid partnerCode
        assertFalse(PartnerCode.isValidPartnerCode("")); // empty string
        assertFalse(PartnerCode.isValidPartnerCode(" ")); // spaces only
        assertFalse(PartnerCode.isValidPartnerCode("!")); // only non-alphanumeric characters
        assertFalse(PartnerCode.isValidPartnerCode("*cs*")); // contains non-alphanumeric characters

        // valid partnerCode
        assertTrue(PartnerCode.isValidPartnerCode("CSCSCS")); // alphabets only
        assertTrue(PartnerCode.isValidPartnerCode("T151")); // first character alphabet, followed by number
        assertTrue(PartnerCode.isValidPartnerCode(TYPICAL_PARTNER_COURSE_CODE)); // alphanumeric characters
    }

    @Test
    public void equals() {
        PartnerCode partnerCode = new PartnerCode(TYPICAL_PARTNER_COURSE_CODE);

        // same values -> returns true
        assertTrue(partnerCode.equals(new PartnerCode(TYPICAL_PARTNER_COURSE_CODE)));

        // same object -> returns true
        assertTrue(partnerCode.equals(partnerCode));

        // null -> returns false
        assertFalse(partnerCode.equals(null));

        // different types -> returns false
        assertFalse(partnerCode.equals(5.0f));

        // different values -> returns false
        assertFalse(partnerCode.equals(new PartnerCode(EDGE_CASE_VALID_PARTNER_COURSE_CODE)));
    }
}
