package seedu.application.model.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.testutil.Assert.assertThrows;

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
        assertFalse(Role.isValidRole("peter*")); // contains non-alphanumeric characters

        // valid role
        assertTrue(Role.isValidRole("peter jack")); // alphabets only
        assertTrue(Role.isValidRole("12345")); // numbers only
        assertTrue(Role.isValidRole("peter the 2nd")); // alphanumeric characters
        assertTrue(Role.isValidRole("Capital Tan")); // with capital letters
        assertTrue(Role.isValidRole("David Roger Jackson Ray Jr 2nd")); // long roles
    }

    @Test
    public void testEqualsAndHashcode() {
        Role role = new Role("Valid Role");

        // same values -> returns true
        assertTrue(role.equals(new Role("Valid Role")));
        assertEquals(role.hashCode(), new Role("Valid Role").hashCode());

        // same object -> returns true
        assertTrue(role.equals(role));

        // null -> returns false
        assertFalse(role.equals(null));

        // different types -> returns false
        assertFalse(role.equals(5.0f));

        // different values -> returns false
        assertFalse(role.equals(new Role("Other Valid Role")));
        assertNotEquals(role.hashCode(), new Role("Other Valid Role").hashCode());
    }
}
