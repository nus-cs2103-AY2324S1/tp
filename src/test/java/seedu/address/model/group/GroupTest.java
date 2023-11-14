package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMBER1_GROUP1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMBER1_GROUP2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMBER2_GROUP1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMBER2_GROUP2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMBER3_GROUP1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMBER3_GROUP2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NUMBER_GROUP1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NUMBER_GROUP2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP2;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.GROUP1;
import static seedu.address.testutil.TypicalGroups.GROUP2;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.group.tasks.TaskList;
import seedu.address.testutil.GroupBuilder;

public class GroupTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Group group = new GroupBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> group.getMembers().remove(0));
    }

    @Test
    public void isSameGroup() {
        // same object -> returns true
        assertTrue(GROUP1.isSameGroup(GROUP1));

        // null -> returns false
        assertFalse(GROUP1.isSameGroup(null));

        // same number, all other attributes different -> returns true
        Group editedGroup1 = new GroupBuilder(GROUP1).withNumber(VALID_NUMBER_GROUP1)
                .withTutorial(VALID_TUTORIAL_GROUP1)
                .withMembers(VALID_MEMBER1_GROUP2, VALID_MEMBER2_GROUP2).withTasks(VALID_TASK1).build();
        assertTrue(GROUP1.isSameGroup(editedGroup1));

        // different number, all other attributes same -> returns false
        editedGroup1 = new GroupBuilder(GROUP1).withNumber(VALID_NUMBER_GROUP2).build();
        assertFalse(GROUP1.isSameGroup(editedGroup1));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Group group1Copy = new GroupBuilder(GROUP1).build();
        assertTrue(GROUP1.equals(group1Copy));

        // same object -> returns true
        assertTrue(GROUP1.equals(GROUP1));

        // null -> returns false
        assertFalse(GROUP1.equals(null));

        // different type -> returns false
        assertFalse(GROUP1.equals(5));

        // different group -> returns false
        assertFalse(GROUP1.equals(GROUP2));

        // different number -> returns false
        Group editedGroup1 = new GroupBuilder(GROUP1).withNumber(VALID_NUMBER_GROUP2).build();
        assertFalse(GROUP1.equals(editedGroup1));

        // different tutorial -> returns false
        editedGroup1 = new GroupBuilder(GROUP1).withTutorial(VALID_TUTORIAL_GROUP2).build();
        assertFalse(GROUP1.equals(editedGroup1));

        // different members -> returns false
        editedGroup1 = new GroupBuilder(GROUP1)
                .withMembers(VALID_MEMBER1_GROUP2, VALID_MEMBER2_GROUP2, VALID_MEMBER3_GROUP2).build();
        assertFalse(GROUP1.equals(editedGroup1));
    }

    @Test
    public void toStringMethod() {
        String expected = Group.class.getCanonicalName() + "{group number="
                + GROUP1.getNumber() + ", tutorial=" + GROUP1.getTutorial()
                + ", members=" + GROUP1.getMembers() + ", tasks=" + GROUP1.getTasks() + "}";
        assertEquals(expected, GROUP1.toString());
    }

    @Test
    public void addMemberTest() {
        Group editedGroup1 = new GroupBuilder(GROUP1)
                .withMembers(VALID_MEMBER1_GROUP1, VALID_MEMBER2_GROUP1).build();
        editedGroup1.addMember(VALID_MEMBER3_GROUP1);
        assertEquals(editedGroup1, GROUP1);
    }

    @Test
    public void removeMemberTest() {
        Group editedGroup1 = new GroupBuilder(GROUP1).build();
        editedGroup1.removeMember(VALID_MEMBER1_GROUP1);
        assertNotEquals(editedGroup1, GROUP1);
    }

    @Test
    public void hasMemberTest() {
        assertTrue(GROUP1.hasMember(VALID_MEMBER1_GROUP1));
    }

    @Test
    public void isFullTest() {
        // not full -> returns false
        assertFalse(GROUP1.isFull());

        // full -> returns true
        Group editedGroup1 = new GroupBuilder(GROUP1).build();
        editedGroup1.addMember(VALID_MEMBER1_GROUP2);
        editedGroup1.addMember(VALID_MEMBER2_GROUP2);
        assertTrue(editedGroup1.isFull());
    }

    @Test
    public void isValidGroupNumberTest() {
        // non-numerical returns false
        assertFalse(Group.isValidGroupNumber("One"));

        // negative number returns false
        assertFalse(Group.isValidGroupNumber("-1"));

        // floating point number returns false
        assertFalse(Group.isValidGroupNumber("1.0"));

        // positive integer returns true
        assertTrue(Group.isValidGroupNumber("1"));
    }

    @Test
    public void addTasksTest() {
        Group editedGroup1 = new GroupBuilder(GROUP1).build();
        editedGroup1.addTasks(new TaskList(List.of(VALID_TASK3)));
        assertTrue(editedGroup1.getTasks()
                .isTaskInAllTasks(VALID_TASK3.getTaskType(), VALID_TASK3.getTask()));
    }
}
