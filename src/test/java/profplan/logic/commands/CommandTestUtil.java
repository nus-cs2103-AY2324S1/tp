package profplan.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static profplan.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static profplan.logic.parser.CliSyntax.PREFIX_DUEDATE;
import static profplan.logic.parser.CliSyntax.PREFIX_NAME;
import static profplan.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static profplan.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import profplan.commons.core.index.Index;
import profplan.logic.commands.exceptions.CommandException;
import profplan.model.Model;
import profplan.model.ProfPlan;
import profplan.model.task.Task;
import profplan.model.task.predicates.NameContainsKeywordsPredicate;
import profplan.testutil.Assert;
import profplan.testutil.EditTaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PRIORITY_AMY = "1";
    public static final String VALID_PRIORITY_BOB = "2";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_LINK_EXAMPLE = "www.exmaple.com";
    public static final String VALID_LINK_GOOGLE = "www.google.com";
    public static final String VALID_DESCRIPTION = "this is a valid description";
    public static final String EMPTY_DESCRIPTION = "";
    public static final String UNUSUAL_VALID_DESCRIPTION = "!@#$%^&*()_   ;'d;'~~~";

    public static final String DUEDATE_DESC = " " + PREFIX_DUEDATE + "10-12-2023";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PRIORITY_DESC_AMY = " " + PREFIX_PRIORITY + VALID_PRIORITY_AMY;
    public static final String PRIORITY_DESC_BOB = " " + PREFIX_PRIORITY + VALID_PRIORITY_BOB;
    public static final String DESCRIPTION_DESC_AMY = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION;
    public static final String DESCRIPTION_DESC_BOB = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PRIORITY_DESC = " " + PREFIX_PRIORITY + "911a"; // 'a' not allowed in priorities
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditTaskDescriptor DESC_AMY;
    public static final EditCommand.EditTaskDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditTaskDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPriority(VALID_PRIORITY_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditTaskDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPriority(VALID_PRIORITY_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the task list, filtered task list and selected task in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ProfPlan expectedProfPlan = new ProfPlan(actualModel.getProfPlan());
        List<Task> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTaskList());

        Assert.assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedProfPlan, actualModel.getProfPlan());
        assertEquals(expectedFilteredList, actualModel.getFilteredTaskList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s task list.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String[] splitName = task.getName().fullName.split("\\s+");
        model.updateFilteredTaskList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredTaskList().size());
    }

}
