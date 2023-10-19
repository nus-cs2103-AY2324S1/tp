package seedu.address.model.person;

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
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidSalary = "";
        assertThrows(IllegalArgumentException.class, () -> new Salary(invalidSalary));
    }

    @Test
    public void isValidPhone() {
        // null salary
        assertThrows(NullPointerException.class, () -> Salary.isValidSalary(null));

        // invalid salary
        assertFalse(Salary.isValidSalary("")); // empty string
        assertFalse(Salary.isValidSalary(" ")); // spaces only
        assertFalse(Salary.isValidSalary("phone")); // non-numeric
        assertFalse(Salary.isValidSalary("9011p041")); // alphabets within digits
        assertFalse(Salary.isValidSalary("9312 1534")); // spaces within digits

        // valid salary
        assertTrue(Phone.isValidPhone("911")); // exactly 3 numbers
        assertTrue(Phone.isValidPhone("124293842033123")); // huge salary
    }

    @Test
    public void equals() {
        Salary salary = new Salary("999");

        // same values -> returns true
        assertTrue(salary.equals(new Salary("999")));

        // same object -> returns true
        assertTrue(salary.equals(salary));

        // null -> returns false
        assertFalse(salary.equals(null));

        // different types -> returns false
        assertFalse(salary.equals(5.0f));

        // different values -> returns false
        assertFalse(salary.equals(new Salary("995")));
    }
}
