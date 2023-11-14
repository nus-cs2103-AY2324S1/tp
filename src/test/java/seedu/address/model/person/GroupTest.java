package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GroupTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Group(null));
    }

    @Test
    public void constructor_invalidGroup_throwsIllegalArgumentException() {
        String invalidGroup = "group with a space";
        assertThrows(IllegalArgumentException.class, () -> new Group(invalidGroup));
    }

    @Test
    public void isValidGroup() {
        // null group
        assertThrows(NullPointerException.class, () -> Group.isValidGroup(null));

        // invalid group
        assertFalse(Group.isValidGroup("group 1")); // space
        assertFalse(Group.isValidGroup("group-1")); // contains non-alphanumeric

        // valid group
        assertTrue(Group.isValidGroup("123")); // exactly 3 numbers
        assertTrue(Group.isValidGroup("groupone")); // exactly letters
        assertTrue(Group.isValidGroup("group1")); // exactly mix of letters and numbers
    }

    @Test
    public void equals() {
        Group grp = new Group("Group1");

        // same values -> returns true
        assertTrue(grp.equals(new Group("Group1")));

        // same object -> returns true
        assertTrue(grp.equals(grp));

        // null -> returns false
        assertFalse(grp.equals(null));

        // different types -> returns false
        assertFalse(grp.equals(5.0f));

        // different values -> returns false
        assertFalse(grp.equals(new Group("Group2")));
    }

    @Test
    public void hashCodeTest() {
        Group grp = new Group("Group1");
        assertTrue(grp.hashCode() == grp.hashCode());
    }

    @Test
    public void toStringTest() {
        Group grp = new Group("Group1");
        assertTrue(grp.toString().equals("Group1"));
    }
}
