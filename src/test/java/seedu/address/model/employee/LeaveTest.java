package seedu.address.model.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LeaveTest {
    private LocalDate validDate = LocalDate.parse("2023-11-11", DateTimeFormatter.ISO_LOCAL_DATE);
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Leave(null));
    }

    @Test
    public void constructor_invalidLeave_throwsIllegalArgumentException() {
        String invalidLeave = "";
        assertThrows(DateTimeParseException.class, () -> new Leave(LocalDate.parse(invalidLeave)));
    }

    @Test
    public void isValidLeave() {
        // null date
        Assertions.assertThrows(NullPointerException.class, () -> Leave.isValidLeaveDate(null));

        // invalid date
        assertFalse(Leave.isValidLeaveDate("")); // empty string
        assertFalse(Leave.isValidLeaveDate(" ")); // spaces only
        assertFalse(Leave.isValidLeaveDate("20231111")); // digits only
        assertFalse(Leave.isValidLeaveDate("11-11-2023")); // invalid format
        assertFalse(Leave.isValidLeaveDate("11 Nov 2023")); // invalid format
        assertFalse(Leave.isValidLeaveDate("2023-11-31")); // invalid date

        // valid date
        assertTrue(Leave.isValidLeaveDate("2023-11-11")); // valid date in correct format
    }

    @Test
    public void equals() {
        Leave leave = new Leave(validDate);

        // same values -> returns true
        assertTrue(leave.equals(new Leave(validDate)));

        // same object -> returns true
        assertTrue(leave.equals(leave));

        // null -> returns false
        assertFalse(leave.equals(null));

        // different types -> returns false
        assertFalse(leave.equals(5.0f));

        // different values -> returns false
        assertFalse(leave.equals(
                new Leave(LocalDate.parse("2023-12-12", DateTimeFormatter.ISO_LOCAL_DATE))));
    }
}
