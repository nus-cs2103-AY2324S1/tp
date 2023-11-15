package seedu.address.model.displayable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SortOrderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortOrder(null));
    }

    @Test
    public void constructor_invalidSortOrder_throwsIllegalArgumentException() {
        String invalidSortOrder = "b";
        assertThrows(IllegalArgumentException.class, () -> new SortOrder(invalidSortOrder));
    }

    @Test
    public void isValidSortOrder() {
        // null sort order
        assertThrows(NullPointerException.class, () -> SortOrder.isValidSortOrder(null));

        // blank sort order
        assertFalse(SortOrder.isValidSortOrder("")); // empty string
        assertFalse(SortOrder.isValidSortOrder(" ")); // spaces only

        // invalid sort order
        assertFalse(SortOrder.isValidSortOrder("1")); // number
        assertFalse(SortOrder.isValidSortOrder("b")); // wrong alphabet
        assertFalse(SortOrder.isValidSortOrder("aa")); // more than one of the correct char
        assertFalse(SortOrder.isValidSortOrder("ab")); // correct char followed by an invalid char
        assertFalse(SortOrder.isValidSortOrder("ba")); // invalid char followed by a correct char
        assertFalse(SortOrder.isValidSortOrder("A")); // valid char but capitalised
        assertFalse(SortOrder.isValidSortOrder("D")); // valid char but capitalised

        // valid sort order
        assertTrue(SortOrder.isValidSortOrder("a"));
        assertTrue(SortOrder.isValidSortOrder("d"));
    }

    @Test
    public void equals() {
        SortOrder sortOrder = new SortOrder("a");

        // same values -> returns true
        assertTrue(sortOrder.equals(new SortOrder("a")));

        // same object -> returns true
        assertTrue(sortOrder.equals(sortOrder));

        // null -> returns false
        assertFalse(sortOrder.equals(null));

        // different types -> returns false
        assertFalse(sortOrder.equals(5.0f));

        // different values -> returns false
        assertFalse(sortOrder.equals(new SortOrder("d")));
    }
}
