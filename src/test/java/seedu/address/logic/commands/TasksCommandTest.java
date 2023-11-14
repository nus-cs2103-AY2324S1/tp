package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupContainsKeywordsPredicate;
import seedu.address.model.tutorial.Tutorial;

public class TasksCommandTest {

    private Model model;
    private Group group;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        group = new Group(1, new Tutorial("01"));
        model.addGroup(group);
        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
    }

    @Test
    public void execute_validGroup_success() throws CommandException {
        int groupId = 1;
        TasksCommand tasksCommand = new TasksCommand(groupId,
            new GroupContainsKeywordsPredicate(List.of(String.valueOf(groupId))));

        CommandResult result = tasksCommand.execute(model);

        assertEquals(String.format(TasksCommand.MESSAGE_SUCCESS, groupId)
                + "\n❌ T 1. CS2101 Upload video of OP1. \n"
                + "❌ T 2. CS2101 Complete peer review for OP2. \n"
                + "❌ D 3. CS2101 Submit slides for OP2. 29/10/2023 2359\n"
                + "❌ D 4. CS2101 Complete peer review. 02/11/2023 2359\n"
                + "❌ T 5. CS2101 Research on the SCQA framework. \n"
                + "❌ D 6. CS2101 Plan for OP2. 24/10/2023 2359\n"
                + "❌ D 7. CS2101 Submit UG. 11/11/2023 2359\n"
                + "❌ T 8. CS2103T Complete mid semester review form. \n"
                + "❌ D 9. CS2103T Add demo screenshots to project notes. 20/11/2023 2359\n"
                + "❌ D 10. CS2103T Release v1.3.trial jar file. 27/10/2023 2359\n"
                + "❌ D 11. CS2103T Wrap up milestone 1.3. 03/11/2023 2359\n"
                + "❌ D 12. CS2103T Finalise TP. 17/11/2023 2359\n"
                + "❌ T 13. CS2103T Update DG for each feature. \n",
            result.getFeedbackToUser());
    }

    @Test
    public void execute_groupNotFound_throwsCommandException() {
        int groupId = 999; // Non-existent group
        TasksCommand tasksCommand = new TasksCommand(groupId,
            new GroupContainsKeywordsPredicate(List.of(String.valueOf(groupId))));

        assertThrows(CommandException.class, () -> tasksCommand.execute(model));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        int groupId = 1;
        TasksCommand tasksCommand = new TasksCommand(groupId,
            new GroupContainsKeywordsPredicate(List.of(String.valueOf(groupId))));

        assertThrows(NullPointerException.class, () -> tasksCommand.execute(null));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        int groupId = 1;
        TasksCommand tasksCommand = new TasksCommand(groupId,
            new GroupContainsKeywordsPredicate(List.of(String.valueOf(groupId))));

        assertTrue(tasksCommand.equals(tasksCommand));
    }

    @Test
    public void equals_differentObjectSameValues_returnsTrue() {
        int groupId = 1;
        TasksCommand tasksCommand1 = new TasksCommand(groupId,
            new GroupContainsKeywordsPredicate(List.of(String.valueOf(groupId))));
        TasksCommand tasksCommand2 = new TasksCommand(groupId,
            new GroupContainsKeywordsPredicate(List.of(String.valueOf(groupId))));

        assertTrue(tasksCommand1.equals(tasksCommand2));
    }

    @Test
    public void equals_differentObjectDifferentValues_returnsFalse() {
        int groupId1 = 1;
        int groupId2 = 2;
        TasksCommand tasksCommand1 = new TasksCommand(groupId1,
            new GroupContainsKeywordsPredicate(List.of(String.valueOf(groupId1))));
        TasksCommand tasksCommand2 = new TasksCommand(groupId2,
            new GroupContainsKeywordsPredicate(List.of(String.valueOf(groupId2))));

        assertFalse(tasksCommand1.equals(tasksCommand2));
    }
}
