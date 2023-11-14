package seedu.address.model.partnercourse.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalObjects.COMP1000;
import static seedu.address.testutil.TypicalObjects.COMP2000;

import org.junit.jupiter.api.Test;

public class PartnerCourseComparatorByPartnerCodeTest {

    private PartnerCourseComparatorByPartnerCode partnerCourseComparatorByPartnerCode =
            new PartnerCourseComparatorByPartnerCode();

    @Test
    public void compare() {
        assertEquals(-1, partnerCourseComparatorByPartnerCode.compare(COMP1000, COMP2000));
        assertEquals(0, partnerCourseComparatorByPartnerCode.compare(COMP1000, COMP1000));
        assertEquals(1, partnerCourseComparatorByPartnerCode.compare(COMP2000, COMP1000));
    }

    @Test
    public void equals() {
        // same values -> returns true
        assertTrue(partnerCourseComparatorByPartnerCode.equals(new PartnerCourseComparatorByPartnerCode()));

        // same object -> returns true
        assertTrue(partnerCourseComparatorByPartnerCode.equals(partnerCourseComparatorByPartnerCode));

        // null -> returns false
        assertFalse(partnerCourseComparatorByPartnerCode.equals(null));

        // different types -> returns false
        assertFalse(partnerCourseComparatorByPartnerCode.equals(1));

        // different values -> returns false
        assertFalse(partnerCourseComparatorByPartnerCode.equals(new PartnerCourseComparatorByPartnerName()));
    }
}
