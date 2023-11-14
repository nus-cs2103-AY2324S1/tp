package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    private String invalidDateInput = "";
    @Test
    public void constructor_allNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null, null));
    }

    @Test
    public void constructor_deadlineNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null, "20/04/2024"));
    }

    @Test
    public void constructor_startDateNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline("20/04/2024", null));
    }

    @Test
    public void constructor_allInvalid_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDateInput, invalidDateInput));
    }

    @Test
    public void constructor_deadlineInvalid_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDateInput, "20/04/2024"));
    }

    @Test
    public void constructor_startDateInvalid_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Deadline("20/04/2024", invalidDateInput));
    }

    @Test
    public void isValidDeadline() {
        // invalid deadline
        assertFalse(Deadline.isValidDeadline("91", "72")); // not in valid format
        assertFalse(Deadline.isValidDeadline("10 Oct 2023", "11 Oct 2023")); // not in valid format
        assertFalse(Deadline.isValidDeadline("10-10-2023", "11-11-2023")); // not in valid format
        assertFalse(Deadline.isValidDeadline("deadline", "startDate")); // not in valid format
        assertFalse(Deadline.isValidDeadline("10/13/2023", "11/13/2023")); // not in valid format

        // valid deadline numbers
        assertTrue(Deadline.isValidDeadline("10/01/2022", "15/01/2022"));
        assertTrue(Deadline.isValidDeadline("01/02/1996", "12/12/2021"));
    }

    @Test
    public void equals() {
        Deadline deadline = new Deadline("10/10/2024", "11/11/2024");

        // same values -> returns true
        assertTrue(deadline.equals(new Deadline("10/10/2024", "11/11/2024")));

        // same object -> returns true
        assertTrue(deadline.equals(deadline));

        // null -> returns false
        assertFalse(deadline.equals(null));

        // different types -> returns false
        assertFalse(deadline.equals(5.0f));

        // different values -> returns false
        assertFalse(deadline.equals(new Deadline("30/12/2025", "01/01/2027")));
    }

    @Test
    public void toString_validDeadlineName_correctFormat() {
        Deadline deadline = new Deadline("10/10/2024", "11/11/2024");
        assertEquals("10/10/2024", deadline.toString());
    }

    // If two objects are considered equal (as defined by the equals() method), they must return the same hash code.
    @Test
    public void hashCode_sameDeadlineName_sameHashCode() {
        Deadline deadline1 = new Deadline("10/10/2024", "11/11/2024");
        Deadline deadline2 = new Deadline("10/10/2024", "11/11/2024");
        assertEquals(deadline1.hashCode(), deadline2.hashCode());
    }

    @Test
    public void compareTo_sameDate_returnsZero() {
        Deadline deadline1 = new Deadline("01/01/2022", "02/01/2022");
        Deadline deadline2 = new Deadline("01/01/2022", "02/01/2022");

        assertEquals(0, deadline1.compareTo(deadline2));
    }

    @Test
    public void compareTo_deadline1EarlierThanDeadline2_returnsNegative() {
        Deadline deadline1 = new Deadline("01/01/2022", "03/01/2022");
        Deadline deadline2 = new Deadline("02/01/2022", "03/01/2022");

        assertEquals(true, deadline1.compareTo(deadline2) < 0);
    }

    @Test
    public void compareTo_deadline1LaterThanDeadline2_returnsPositive() {
        Deadline deadline1 = new Deadline("03/01/2022", "04/01/2022");
        Deadline deadline2 = new Deadline("02/01/2022", "03/01/2022");

        assertEquals(true, deadline1.compareTo(deadline2) > 0);
    }

    @Test
    public void compareTo_datesOneDayApart_correctOrder() {
        Deadline deadline1 = new Deadline("31/12/2021", "02/01/2022");
        Deadline deadline2 = new Deadline("01/01/2022", "02/01/2022");

        assertEquals(true, deadline1.compareTo(deadline2) < 0);
    }
}
