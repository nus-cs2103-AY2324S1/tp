package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoleTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Role(null));
    }

    @Test
    public void constructor_invalidRole_throwsIllegalArgumentException() {
        String invalidRole = "";
        assertThrows(IllegalArgumentException.class, () -> new Role(invalidRole));
    }

    @Test
    public void isValidRole() {
        // null role
        assertThrows(NullPointerException.class, () -> Role.isValidRole(null));

        // invalid role
        assertFalse(Role.isValidRole("")); // empty string
        assertFalse(Role.isValidRole(" ")); // spaces only
        assertFalse(Role.isValidRole("^")); // only non-alphanumeric characters
        assertFalse(Role.isValidRole("software*engineer")); // contains non-alphanumeric characters

        // valid role
        assertTrue(Role.isValidRole("software engineer")); // alphabets only
        assertTrue(Role.isValidRole("12345")); // numbers only
        assertTrue(Role.isValidRole("web3 developer")); // alphanumeric characters
        assertTrue(Role.isValidRole("Software Engineer")); // with capital letters
        assertTrue(Role.isValidRole("Machine Learning and Artificial Intelligence Engineer")); // long roles
    }

    @Test
    public void equals() {
        Role role = new Role("Valid Role");

        // same values -> returns true
        assertTrue(role.equals(new Role("Valid Role")));

        // same object -> returns true
        assertTrue(role.equals(role));

        // null -> returns false
        assertFalse(role.equals(null));

        // different types -> returns false
        assertFalse(role.equals(5.0f));

        // different values -> returns false
        assertFalse(role.equals(new Role("Other Valid Role")));

        // different case -> returns true
        assertTrue(role.equals(new Role("Valid ROLE")));

        // different case -> returns true
        assertTrue(role.equals(new Role("valid role")));

        role = new Role("Valid Name 123");

        // Alphanumerics preserved -> returns true
        assertTrue(role.equals(new Role("Valid Name 123")));

        role = new Role("Valid Name 2");

        //Does not strip numbers
        assertFalse(role.equals(new Role("Valid Name ")));

        assertFalse(role.equals(new Role("Valid Name")));
    }
}
