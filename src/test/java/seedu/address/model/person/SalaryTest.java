package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SalaryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Salary(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidSalary = "1500";
        assertThrows(IllegalArgumentException.class, () -> new Salary(invalidSalary));
    }

    @Test
    public void isValidEmail() {
        // null salary
        assertThrows(NullPointerException.class, () -> Salary.isValidSalary(null));

        //valid salary
        assertTrue(Salary.isValidSalary("0.00"));
        assertTrue(Salary.isValidSalary("10.00"));
        assertTrue(Salary.isValidSalary("100.00"));
        assertTrue(Salary.isValidSalary("10000.00"));
        assertTrue(Salary.isValidSalary("100000.00"));

        //invalid salary
        assertFalse(Salary.isValidSalary("")); //empty value
        assertFalse(Salary.isValidSalary(" ")); //space only
        assertFalse(Salary.isValidSalary("*")); //only non-alphanumeric characters
        assertFalse(Salary.isValidSalary("8+1500.00")); //contain non-alphanumeric characters
        assertFalse(Salary.isValidSalary("8000")); //no digit
        assertFalse(Salary.isValidSalary("1500.0")); //1 digit
        assertFalse(Salary.isValidSalary("1500.000")); //3 digit
    }

    @Test
    public void equals() {
        Salary salary = new Salary("1500.00");

        // same values -> returns true
        assertTrue(salary.equals(new Salary("1500.00")));

        // same object -> returns true
        assertTrue(salary.equals(salary));

        // null -> returns false
        assertFalse(salary.equals(null));

        // different types -> returns false
        assertFalse(salary.equals(5.0f));

        // different values -> returns false
        assertFalse(salary.equals(new Salary("1500.01")));
    }
}
