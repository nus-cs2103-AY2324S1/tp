package seedu.address.model.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class OvertimeHoursTest {
    @Test
    public void equals() {
        OvertimeHours overtimeHours = new OvertimeHours(72);

        // same values -> returns true
        assertTrue(overtimeHours.equals(new OvertimeHours(72)));

        //different values -> returns false
        assertFalse(overtimeHours.equals(new OvertimeHours(60)));

        // same object -> returns true
        assertTrue(overtimeHours.equals(overtimeHours));

        // null -> returns false
        assertFalse(overtimeHours.equals(null));

        // different types -> returns false
        assertFalse(overtimeHours.equals(5.0f));
    }
}
