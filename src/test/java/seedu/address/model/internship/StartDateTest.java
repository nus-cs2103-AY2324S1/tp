package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class StartDateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StartDate(null));
    }

    @Test
    public void constructor_invalidStartDate_throwsIllegalArgumentException() {
        String invalidStartDate = "";
        assertThrows(IllegalArgumentException.class, () -> new StartDate(invalidStartDate));
    }

    @Test
    public void isValidStartDate() {
        // null startDate -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> StartDate.isValidStartDate(null));

        // invalid startDate -> returns false
        assertFalse(StartDate.isValidStartDate("")); // empty string
        assertFalse(StartDate.isValidStartDate(" ")); // spaces only
        assertFalse(StartDate.isValidStartDate("91")); // not in valid format
        assertFalse(StartDate.isValidStartDate("10 Oct 2023")); // not in valid format
        assertFalse(StartDate.isValidStartDate("10-10-2023")); // not in valid format
        assertFalse(StartDate.isValidStartDate("startDate")); // not in valid format
        assertFalse(StartDate.isValidStartDate("10/13/2023")); // not in valid format

        // valid startDate -> returns true
        assertTrue(StartDate.isValidStartDate("10/01/2022")); // valid DD/MM/YYYY format
        assertTrue(StartDate.isValidStartDate("31/12/2023"));
    }

    @Test
    public void equals() {
        StartDate startDate = new StartDate("10/10/2024");

        // same values -> returns true
        assertTrue(startDate.equals(new StartDate("10/10/2024")));

        // same object -> returns true
        assertTrue(startDate.equals(startDate));

        // null -> returns false
        assertFalse(startDate.equals(null));

        // different types -> returns false
        assertFalse(startDate.equals(5.0f));

        // different values -> returns false
        assertFalse(startDate.equals(new StartDate("30/12/2025")));
    }

    @Test
    public void compareTo_sameDate_returnsZero() {
        StartDate startDate1 = new StartDate("01/01/2022");
        StartDate startDate2 = new StartDate("01/01/2022");

        assertEquals(0, startDate1.compareTo(startDate2));
    }

    @Test
    public void compareTo_startDate1EarlierThanStartDate2_returnsNegative() {
        StartDate startDate1 = new StartDate("01/01/2022");
        StartDate startDate2 = new StartDate("02/01/2022");

        assertEquals(true, startDate1.compareTo(startDate2) < 0);
    }

    @Test
    public void compareTo_startDate1LaterThanStartDate2_returnsPositive() {
        StartDate startDate1 = new StartDate("03/01/2022");
        StartDate startDate2 = new StartDate("02/01/2022");

        assertEquals(true, startDate1.compareTo(startDate2) > 0);
    }

    @Test
    public void compareTo_datesOneDayApart_correctOrder() {
        StartDate startDate1 = new StartDate("31/12/2021");
        StartDate startDate2 = new StartDate("01/01/2022");

        assertEquals(true, startDate1.compareTo(startDate2) < 0);
    }
}
