package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class RoleTest {

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
        // null role -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> Role.isValidRole(null));

        // invalid role -> returns false
        assertFalse(Role.isValidRole("")); // empty string
        assertFalse(Role.isValidRole(" ")); // spaces only
        assertFalse(Role.isValidRole("^")); // only non-alphanumeric characters
        assertFalse(Role.isValidRole("engineer*")); // contains non-alphanumeric characters
        assertFalse(Role.isValidRole("123456789012345678901234567890123456789012345678901234567"
                + "89012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345"
                + "678901234567890123456789012345678901234567890a")); // 201 characters

        // valid role -> returns true
        assertTrue(Role.isValidRole("data analyst")); // alphabets only
        assertTrue(Role.isValidRole("12345")); // numbers only
        assertTrue(Role.isValidRole("2nd assistant")); // alphanumeric characters
        assertTrue(Role.isValidRole("Data Scientist")); // with capital letters
        assertTrue(Role.isValidRole("Data Scientist and Machine Learning Engineer")); // long roles
        assertTrue(Role.isValidRole("123456789012345678901234567890123456789012345678901234567"
                + "89012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345"
                + "678901234567890123456789012345678901234567890")); // 200 characters
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
    }
    @Test
    public void compareTo_sameRole_returnsZero() {
        Role roleA = new Role("Developer");
        Role roleB = new Role("Developer");

        assertEquals(0, roleA.compareTo(roleB));
    }

    @Test
    public void compareTo_roleALessThanRoleB_returnsNegative() {
        Role roleA = new Role("Analyst");
        Role roleB = new Role("Developer");

        assertEquals(true, roleA.compareTo(roleB) < 0);
    }

    @Test
    public void compareTo_roleAGreaterThanRoleB_returnsPositive() {
        Role roleA = new Role("Manager");
        Role roleB = new Role("Developer");

        assertEquals(true, roleA.compareTo(roleB) > 0);
    }

    @Test
    public void compareTo_rolesWithSpaces_correctOrder() {
        Role roleA = new Role("Assistant Manager");
        Role roleB = new Role("Developer");

        assertEquals(true, roleA.compareTo(roleB) < 0);
    }

    @Test
    public void compareTo_rolesWithUppercase_correctOrder() {
        Role roleA = new Role("Assistant Manager");
        Role roleB = new Role("developer");

        assertEquals(true, roleA.compareTo(roleB) < 0);
    }
}
