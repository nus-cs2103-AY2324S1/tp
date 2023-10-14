package seedu.staffsnap.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.staffsnap.testutil.Assert.assertThrows;

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

        // blank department
        assertFalse(Department.isValidDepartment("")); // empty string
        assertFalse(Department.isValidDepartment(" ")); // spaces only

        // missing parts
        assertFalse(Department.isValidDepartment("@example.com")); // missing local part
        assertFalse(Department.isValidDepartment("peterjackexample.com")); // missing '@' symbol
        assertFalse(Department.isValidDepartment("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(Department.isValidDepartment("peterjack@-")); // invalid domain name
        assertFalse(Department.isValidDepartment("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(Department.isValidDepartment("peter jack@example.com")); // spaces in local part
        assertFalse(Department.isValidDepartment("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(Department.isValidDepartment(" peterjack@example.com")); // leading space
        assertFalse(Department.isValidDepartment("peterjack@example.com ")); // trailing space
        assertFalse(Department.isValidDepartment("peterjack@@example.com")); // double '@' symbol
        assertFalse(Department.isValidDepartment("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(Department.isValidDepartment("-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(Department.isValidDepartment("peterjack-@example.com")); // local part ends with a hyphen
        // local part has two consecutive periods
        assertFalse(Department.isValidDepartment("peter..jack@example.com"));
        assertFalse(Department.isValidDepartment("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(Department.isValidDepartment("peterjack@.example.com")); // domain name starts with a period
        assertFalse(Department.isValidDepartment("peterjack@example.com.")); // domain name ends with a period
        assertFalse(Department.isValidDepartment("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(Department.isValidDepartment("peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(Department.isValidDepartment("peterjack@example.c")); // top level domain has less than two chars

        // valid department
        assertTrue(Department.isValidDepartment("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(Department.isValidDepartment("PeterJack.1190@example.com")); // period in local part
        assertTrue(Department.isValidDepartment("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(Department.isValidDepartment("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(Department.isValidDepartment("a@bc")); // minimal
        assertTrue(Department.isValidDepartment("test@localhost")); // alphabets only
        assertTrue(Department.isValidDepartment("123@145")); // numeric local part and domain name
        // mixture of alphanumeric and special characters
        assertTrue(Department.isValidDepartment("a1+be.d@example1.com"));
        assertTrue(Department.isValidDepartment("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(Department.isValidDepartment("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(Department.isValidDepartment("e1234567@u.nus.edu")); // more than one period in domain
    }

    @Test
    public void equals() {
        Department department = new Department("valid@department");

        // same values -> returns true
        assertTrue(department.equals(new Department("valid@department")));

        // same object -> returns true
        assertTrue(department.equals(department));

        // null -> returns false
        assertFalse(department.equals(null));

        // different types -> returns false
        assertFalse(department.equals(5.0f));

        // different values -> returns false
        assertFalse(department.equals(new Department("other.valid@department")));
    }
}
