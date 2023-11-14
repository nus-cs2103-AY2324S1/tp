package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DepartmentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Department(null));
    }

    @Test
    public void constructor_invalidDepartment_throwsIllegalArgumentException() {
        String invalidDepartment = "";
        assertThrows(IllegalArgumentException.class, () -> new Department(invalidDepartment));
    }

    @Test
    public void isValidDepartment() {
        // null department
        assertThrows(NullPointerException.class, () -> Department.isValidDepartment(null));

        // invalid department
        assertFalse(Department.isValidDepartment("")); // empty string
        assertFalse(Department.isValidDepartment(" ")); // spaces only
        assertFalse(Department.isValidDepartment("^")); // only non-alphanumeric characters
        assertFalse(Department.isValidDepartment("Finance*")); // contains non-alphanumeric characters

        // valid department
        assertTrue(Department.isValidDepartment("Finance")); // alphabets only
        assertTrue(Department.isValidDepartment("12")); // numbers only
        assertTrue(Department.isValidDepartment("Human Resources 2")); // alphanumeric characters
        assertTrue(Department.isValidDepartment("The second department for accounting")); // long department names
    }

    @Test
    public void equals() {
        Department department = new Department("Valid Department");

        // same values -> returns true
        assertTrue(department.equals(new Department("Valid Department")));

        // same object -> returns true
        assertTrue(department.equals(department));

        // null -> returns false
        assertFalse(department.equals(null));

        // different types -> returns false
        assertFalse(department.equals(5.0f));

        // different values -> returns false
        assertFalse(department.equals(new Department("Other Valid Department")));
    }
}
