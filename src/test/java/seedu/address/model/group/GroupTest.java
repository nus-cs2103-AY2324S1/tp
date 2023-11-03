package seedu.address.model.group;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalGroups.CS;
import static seedu.address.testutil.TypicalGroups.CS2103T;

public class GroupTest {
    @Test
    public void isSameGroup() {
        // same object -> returns true
        assertTrue(CS.isSameGroup(CS));

        // null -> returns false
        assertFalse(CS.isSameGroup(null));

        // different group -> returns false
        assertFalse(CS.isSameGroup(CS2103T));

        // group name differs in case -> returns false
        assertFalse(CS.isSameGroup(new Group("cs")));

        // group name has trailing spaces -> returns false
        assertFalse(CS.isSameGroup(new Group(" CS")));
    }

    @Test
    public void equals() {
        // same name -> returns true
        Group CSCopy = new Group("CS");
        assertTrue(CS.equals(CSCopy));

        // same object -> returns true
        assertTrue(CS.equals(CS));

        // null -> returns false
        assertFalse(CS.equals(null));

        // different type -> returns false
        assertFalse(CS.equals(1));

        // different group -> returns false
        assertFalse(CS.equals(CS2103T));
    }

    @Test
    public void toStringMethod() {
        String expected = Group.class.getCanonicalName()
                + "{Group name=" + CS.getGroupName() + "}";
        assertEquals(expected, CS.toString());
    }
}
