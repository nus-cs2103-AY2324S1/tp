package seedu.address.model.partnercourse.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalObjects.COMP1000;
import static seedu.address.testutil.TypicalObjects.COMP2000;

import org.junit.jupiter.api.Test;

public class PartnerCourseComparatorByPartnerNameTest {
    private PartnerCourseComparatorByPartnerName partnerCourseComparatorByPartnerName =
            new PartnerCourseComparatorByPartnerName();

    @Test
    public void compare() {
        assertEquals(-1, partnerCourseComparatorByPartnerName.compare(COMP2000, COMP1000));
        assertEquals(0, partnerCourseComparatorByPartnerName.compare(COMP2000, COMP2000));
        assertEquals(1, partnerCourseComparatorByPartnerName.compare(COMP1000, COMP2000));
    }

    @Test
    public void equals() {
        // same values -> returns true
        assertTrue(partnerCourseComparatorByPartnerName.equals(new PartnerCourseComparatorByPartnerName()));

        // same object -> returns true
        assertTrue(partnerCourseComparatorByPartnerName.equals(partnerCourseComparatorByPartnerName));

        // null -> returns false
        assertFalse(partnerCourseComparatorByPartnerName.equals(null));

        // different types -> returns false
        assertFalse(partnerCourseComparatorByPartnerName.equals(1));

        // different values -> returns false
        assertFalse(partnerCourseComparatorByPartnerName.equals(new PartnerCourseComparatorByPartnerCode()));
    }
}
