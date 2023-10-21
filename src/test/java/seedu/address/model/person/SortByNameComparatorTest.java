package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SortByNameComparatorTest {
    @Test
    public void equals() {
        SortByNameComparator firstComparator = new SortByNameComparator();
        SortByNameComparator secondComparator = new SortByNameComparator();

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
        SortByNameComparator comparator = new SortByNameComparator();

        String expected = SortByNameComparator.class.getCanonicalName() + "{}";
        assertEquals(expected, comparator.toString());
    }
}
