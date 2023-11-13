package seedu.letsgethired.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.testutil.Assert.assertThrows;

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
        // null role number
        assertThrows(NullPointerException.class, () -> Role.isValidRole(null));

        // invalid role numbers
        assertFalse(Role.isValidRole("")); // empty string
        assertFalse(Role.isValidRole(" ")); // spaces only

        // valid role numbers
        assertTrue(Role.isValidRole("Intern"));
        assertTrue(Role.isValidRole("Software Developer Intern"));
    }

    @Test
    public void equals() {
        Role role = new Role("Intern");

        // same values -> returns true
        assertTrue(role.equals(new Role("Intern")));

        // same object -> returns true
        assertTrue(role.equals(role));

        // null -> returns false
        assertFalse(role.equals(null));

        // different types -> returns false
        assertFalse(role.equals(5.0f));

        // different values -> returns false
        assertFalse(role.equals(new Role("SWE Intern")));
    }

    @Test
    public void compareTo() {
        Role role1 = new Role("Intern");
        Role role2 = new Role("Software Developer Intern");
        Role role3 = new Role("Software Developer Intern");

        // role1 < role2
        assertTrue(role1.compareTo(role2) < 0);

        // role2 = role3
        assertEquals(0, role2.compareTo(role3));

        // role2 > role1
        assertTrue(role2.compareTo(role1) > 0);
    }
}
