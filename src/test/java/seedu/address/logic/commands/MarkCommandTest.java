package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;
import static seedu.address.testutil.TypicalGroups.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupContainsKeywordsPredicate;
import seedu.address.model.group.tasks.TaskList;
import seedu.address.model.tutorial.Tutorial;

public class MarkCommandTest {

    private Group group;
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        group = new Group(1, new Tutorial("01"));
        // No need to use TaskInitializer as tasks are automatically initialised when group is created.
        model.addGroup(group);
        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
    }

    @Test
    public void execute_validTaskIndex_success() throws CommandException {
        int groupId = 1;
        int taskIndex = 0; // When printing message, CommandResult adds 1 to taskIndex.
        MarkCommand markCommand = new MarkCommand(1, taskIndex,
                new GroupContainsKeywordsPredicate(Arrays.asList(String.valueOf(groupId))));

        CommandResult result = markCommand.execute(model);

        assertEquals("Marked task number 1 for group 1\n"
                        + "✅ T 1. CS2101 Upload video of OP1. \n"
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
    public void execute_taskAlreadyMarked_success() throws CommandException {
        int groupId = 1;
        int taskIndex = 0; // Task is already marked
        MarkCommand markCommand = new MarkCommand(groupId, taskIndex,
                new GroupContainsKeywordsPredicate(Arrays.asList(String.valueOf(groupId))));

        CommandResult result = markCommand.execute(model);

        assertEquals("Marked task number 1 for group 1\n"
                        + "✅ T 1. CS2101 Upload video of OP1. \n"
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
        int groupId = 2; // Invalid groupId
        int taskIndex = 0;
        MarkCommand markCommand = new MarkCommand(groupId, taskIndex,
                new GroupContainsKeywordsPredicate(Arrays.asList(String.valueOf(groupId))));

        assertThrows(CommandException.class, () -> markCommand.execute(model));
    }

    @Test
    public void execute_invalidTaskIndex_throwsCommandException() {
        int groupId = 1;
        int taskIndex = 15; // Invalid task index
        MarkCommand markCommand = new MarkCommand(groupId, taskIndex,
                new GroupContainsKeywordsPredicate(Arrays.asList(String.valueOf(groupId))));

        assertThrows(CommandException.class, () -> markCommand.execute(model));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        int groupId = 1;
        int taskIndex = 0;
        MarkCommand markCommand = new MarkCommand(groupId, taskIndex,
                new GroupContainsKeywordsPredicate(Arrays.asList(String.valueOf(groupId))));

        assertThrows(NullPointerException.class, () -> markCommand.execute(null));
    }

    @Test
    public void execute_negativeTaskIndex_throwsCommandException() {
        int groupId = 1;
        int taskIndex = -1; // Negative task index
        MarkCommand markCommand = new MarkCommand(groupId, taskIndex,
                new GroupContainsKeywordsPredicate(Arrays.asList(String.valueOf(groupId))));

        assertThrows(CommandException.class, () -> markCommand.execute(model));
    }

    @Test
    public void isValidTaskIndex_validTaskIndex_returnsTrue() {
        int taskIndex = 1;
        TaskList taskList = group.getTasks();
        MarkCommand markCommand = new MarkCommand(1, taskIndex,
                new GroupContainsKeywordsPredicate(Arrays.asList(String.valueOf(1))));

        boolean isValid = markCommand.isValidTaskIndex(taskIndex, taskList);

        assertTrue(isValid);
    }

    @Test
    public void isValidTaskIndex_invalidTaskIndex_returnsFalse() {
        int taskIndex = 15; // Invalid task index
        TaskList taskList = group.getTasks();
        MarkCommand markCommand = new MarkCommand(1, taskIndex,
                new GroupContainsKeywordsPredicate(Arrays.asList(String.valueOf(1))));

        boolean isValid = markCommand.isValidTaskIndex(taskIndex, taskList);

        assertFalse(isValid);
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        int groupId = 1;
        int taskIndex = 0;
        MarkCommand markCommand = new MarkCommand(groupId, taskIndex,
                new GroupContainsKeywordsPredicate(Arrays.asList(String.valueOf(groupId))));

        assertTrue(markCommand.equals(markCommand));
    }

    @Test
    public void equals_differentObjectSameValues_returnsTrue() {
        int groupId = 1;
        int taskIndex = 0;
        MarkCommand markCommand1 = new MarkCommand(groupId, taskIndex,
                new GroupContainsKeywordsPredicate(Arrays.asList(String.valueOf(groupId))));
        MarkCommand markCommand2 = new MarkCommand(groupId, taskIndex,
                new GroupContainsKeywordsPredicate(Arrays.asList(String.valueOf(groupId))));

        assertTrue(markCommand1.equals(markCommand2));
    }

    @Test
    public void equals_differentObjectDifferentValues_returnsFalse() {
        int groupId1 = 1;
        int taskIndex1 = 0;
        int groupId2 = 2;
        int taskIndex2 = 1;
        MarkCommand markCommand1 = new MarkCommand(groupId1, taskIndex1,
                new GroupContainsKeywordsPredicate(Arrays.asList(String.valueOf(groupId1))));
        MarkCommand markCommand2 = new MarkCommand(groupId2, taskIndex2,
                new GroupContainsKeywordsPredicate(Arrays.asList(String.valueOf(groupId2))));

        assertFalse(markCommand1.equals(markCommand2));
    }
}
