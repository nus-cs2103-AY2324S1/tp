package seedu.address.model.partnercourse;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.EDGE_CASE_VALID_PARTNER_COURSE_NAME;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE_NAME;

import org.junit.jupiter.api.Test;

public class PartnerNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.address.model.partnercourse.PartnerName(null));
    }

    @Test
    public void constructor_invalidPartnerName_throwsIllegalArgumentException() {
        String invalidPartnerName = "";
        assertThrows(IllegalArgumentException.class, () ->
                new seedu.address.model.partnercourse.PartnerName(invalidPartnerName));
    }

    @Test
    public void isValidPartnerName() {
        // null partnerName
        assertThrows(NullPointerException.class, () ->
                seedu.address.model.partnercourse.PartnerName.isValidPartnerName(null));

        // invalid partnerName
        assertFalse(seedu.address.model.partnercourse.PartnerName.isValidPartnerName("")); // empty string
        assertFalse(seedu.address.model.partnercourse.PartnerName.isValidPartnerName(" ")); // spaces only
        assertFalse(seedu.address.model.partnercourse.PartnerName
                .isValidPartnerName(" I love cs")); // first character space

        // valid partnerName`
        assertTrue(seedu.address.model.partnercourse.PartnerName
                .isValidPartnerName("Cs is so fun")); // alphabets only
        assertTrue(seedu.address.model.partnercourse.PartnerName
                .isValidPartnerName("join soc cs now!")); // alphanumeric, with symbols
        assertTrue(seedu.address.model.partnercourse.PartnerName
                .isValidPartnerName(TYPICAL_PARTNER_COURSE_NAME)); // alphanumeric characters
    }

    @Test
    public void equals() {
        PartnerName partnerName = new PartnerName(TYPICAL_PARTNER_COURSE_NAME);

        // same values -> returns true
        assertTrue(partnerName.equals(new PartnerName(TYPICAL_PARTNER_COURSE_NAME)));

        // same object -> returns true
        assertTrue(partnerName.equals(partnerName));

        // null -> returns false
        assertFalse(partnerName.equals(null));

        // different types -> returns false
        assertFalse(partnerName.equals(5.0f));

        // different values -> returns false
        assertFalse(partnerName.equals(new PartnerName(EDGE_CASE_VALID_PARTNER_COURSE_NAME)));
    }
}
