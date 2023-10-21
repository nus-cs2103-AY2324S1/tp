package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SortByAppointmentComparatorTest {
    @Test
    public void equals() {
        SortByAppointmentComparator firstComparator = new SortByAppointmentComparator();
        SortByAppointmentComparator secondComparator = new SortByAppointmentComparator();

        // same object -> returns true
        assertTrue(firstComparator.equals(firstComparator));

        // different types -> returns false
        assertFalse(firstComparator.equals(1));

        // null -> returns false
        assertFalse(firstComparator.equals(null));

        // different comparators -> returns true
        assertTrue(firstComparator.equals(secondComparator));
    }
    @Test
    public void toStringMethod() {
        SortByAppointmentComparator comparator = new SortByAppointmentComparator();

        String expected = SortByAppointmentComparator.class.getCanonicalName() + "{}";
        assertEquals(expected, comparator.toString());
    }
}
