package seedu.address.model.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SalaryTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Salary(null));
    }

    @Test
    public void constructor_invalidSalary_throwsIllegalArgumentException() {
        String invalidSalary = "";
        assertThrows(IllegalArgumentException.class, () -> new Salary(invalidSalary));
    }

    @Test
    public void isValidSalary() {
        // null salary
        assertThrows(NullPointerException.class, () -> Salary.isValidSalary(null));

        // invalid salaries
        assertFalse(Salary.isValidSalary("")); // empty string
        assertFalse(Salary.isValidSalary(" ")); // spaces only
        assertFalse(Salary.isValidSalary("$")); // no digits
        assertFalse(Salary.isValidSalary("salary")); // non-numeric
        assertFalse(Salary.isValidSalary("9,000")); // contains ','
        assertFalse(Salary.isValidSalary("$9000")); // contains '$'
        assertFalse(Salary.isValidSalary("90p0")); // alphabets within digits
        assertFalse(Salary.isValidSalary("9 000")); // spaces within digits

        // valid salaries
        assertTrue(Salary.isValidSalary("9")); // at least 1 number
        assertTrue(Salary.isValidSalary("9000"));
        assertTrue(Salary.isValidSalary("10000000000")); // long salary
    }

    @Test
    public void getOvertimeHourlyRate_validSalary_returnsOvertimeHourlyRate() {
        Salary salary = new Salary("9000");
        assertTrue(salary.getOvertimeHourlyRate() == 1.5 * 9000 / (52 * 44));
    }

    @Test
    public void equals() {
        Salary salary = new Salary("9000");

        // same values -> returns true
        assertTrue(salary.equals(new Salary("9000")));

        // same object -> returns true
        assertTrue(salary.equals(salary));

        // null -> returns false
        assertFalse(salary.equals(null));

        // different types -> returns false
        assertFalse(salary.equals(5.0f));

        // different values -> returns false
        assertFalse(salary.equals(new Salary("1000")));
    }
}
