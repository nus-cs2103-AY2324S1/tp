package seedu.address.model.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LeaveTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Leave(null));
    }

    @Test
    public void constructor_invalidLeave_throwsIllegalArgumentException() {
        String invalidLeave = "";
        assertThrows(IllegalArgumentException.class, () -> new Leave(invalidLeave));
    }

    @Test
    public void isValidLeave() {
        // null leave
        assertThrows(NullPointerException.class, () -> Leave.isValidLeave(null));

        // invalid leave
        assertFalse(Leave.isValidLeave("")); // empty string
        assertFalse(Leave.isValidLeave(" ")); // spaces only
        assertFalse(Leave.isValidLeave("phone")); // non-numeric
        assertFalse(Leave.isValidLeave("9011p041")); // alphabets within digits
        assertFalse(Leave.isValidLeave("9312 1534")); // spaces within digits

        // valid leave
        assertTrue(Leave.isValidLeave("0")); // 0 leave
        assertTrue(Leave.isValidLeave("14")); // normal leave
        assertTrue(Leave.isValidLeave("91")); // large leave
        assertTrue(Leave.isValidLeave("124293842033123")); // huge leave
    }

    @Test
    public void equals() {
        Leave leave = new Leave("21");

        // same values -> returns true
        assertTrue(leave.equals(new Leave("21")));

        // same object -> returns true
        assertTrue(leave.equals(leave));

        // null -> returns false
        assertFalse(leave.equals(null));

        // different types -> returns false
        assertFalse(leave.equals(5.0f));

        // different values -> returns false
        assertFalse(leave.equals(new Leave("35")));
    }
}
