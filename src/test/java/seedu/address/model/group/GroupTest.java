package seedu.address.model.group;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GroupTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Group(null));
    }

    @Test
    public void constructor_invalidGroupName_throwsIllegalArgumentException() {
        String invalidGroupName = "";
        assertThrows(IllegalArgumentException.class, () -> new Group(invalidGroupName));
    }

    @Test
    public void isValidGroupName() {
        // null group name
        assertThrows(NullPointerException.class, () -> Group.isValidGroupName(null));
    }

    @Test
    public void groupsNotEqualTest() {
        Group group1 = new Group("group1");
        Group group2 = new Group("group2");
        assert(!group1.equals(group2));
    }

    @Test
    public void groupsEqualTest() {
        Group group1 = new Group("group1");
        Group group2 = new Group("group1");
        assert(group1.equals(group2));
    }

    @Test
    public void groupsNotEqualDifferentTypeTest() {
        Group group1 = new Group("group1");
        String group2 = "group1";

        assert(!group1.equals(group2));
    }

}
