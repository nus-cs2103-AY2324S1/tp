package seedu.address.model.person;

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
    public void isValidRoleType() {
        // null roletype
        assertThrows(NullPointerException.class, () -> Role.isValidRoleType(null));

        // invalid roletype
        assertFalse(Role.isValidRoleType("")); // empty string
        assertFalse(Role.isValidRoleType(" ")); // spaces only
        assertFalse(Role.isValidRoleType("student")); // lowercase
        assertFalse(Role.isValidRoleType("TAProfessor")); // two at once

        // valid roletype
        assertTrue(Role.isValidRoleType("Student")); // alphabets only
        assertTrue(Role.isValidRoleType("TA")); // numbers only
        assertTrue(Role.isValidRoleType("Professor")); // alphanumeric characters
    }

    @Test
    public void equals() {
        Role role = new Role("Professor");

        // same values -> returns true
        assertTrue(role.equals(new Role("Professor")));

        // same object -> returns true
        assertTrue(role.equals(role));

        // null -> returns false
        assertFalse(role.equals(null));

        // different types -> returns false
        assertFalse(role.equals(5.0f));

        // different values -> returns false
        assertFalse(role.equals(new Role("TA")));
    }
}
