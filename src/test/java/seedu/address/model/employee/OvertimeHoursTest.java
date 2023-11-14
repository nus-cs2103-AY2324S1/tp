package seedu.address.model.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.employee.Employee.MAX_OVERTIME_HOURS;

import org.junit.jupiter.api.Test;

class OvertimeHoursTest {
    @Test
    public void equals() {
        OvertimeHours overtimeHours = new OvertimeHours(MAX_OVERTIME_HOURS);

        // same values -> returns true
        assertTrue(overtimeHours.equals(new OvertimeHours(MAX_OVERTIME_HOURS)));

        //different values -> returns false
        assertFalse(overtimeHours.equals(new OvertimeHours(60)));

        // same object -> returns true
        assertTrue(overtimeHours.equals(overtimeHours));

        // null -> returns false
        assertFalse(overtimeHours.equals(null));

        // different types -> returns false
        assertFalse(overtimeHours.equals(5.0f));
    }

    @Test
    public void isValidOvertimeHours() {

        // within range 0 to 72 inclusive -> returns true
        assertTrue(OvertimeHours.isValidOvertimeHours(67));
        assertTrue(OvertimeHours.isValidOvertimeHours(0));
        assertTrue(OvertimeHours.isValidOvertimeHours(72));

        //outside range 0 to 72 inclusive -> return false;
        assertFalse(OvertimeHours.isValidOvertimeHours(-6));
        assertFalse(OvertimeHours.isValidOvertimeHours(80));
    }
}
