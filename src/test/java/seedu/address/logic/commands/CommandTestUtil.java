package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TaskWise;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.DescriptionContainsKeywordsPredicate;
import seedu.address.model.task.Task;
import seedu.address.testutil.EditTaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_DESC_DG = "Developer Guide";
    public static final String VALID_DESC_UG = "User Guide";
    public static final String VALID_DESC_TEST = "test";
    public static final String VALID_DEADLINE_DG_STRING = "14-11-2023 23:59";
    public static final Deadline VALID_DEADLINE_DG_DEADLINE = Deadline.of(
            LocalDateTime.parse("2023-11-14T23:59:00"));
    public static final String VALID_DEADLINE_UG_STRING = "14-11-2023";
    public static final Deadline VALID_DEADLINE_UG_DEADLINE = Deadline.of(
            LocalDateTime.parse("2023-11-14T00:00:00"));
    public static final String VALID_PRIORITY_DG = "high";
    public static final String VALID_PRIORITY_UG = "medium";
    public static final String VALID_NOTE_DG = "Ensure diagrams are correct";
    public static final String VALID_NOTE_UG = "Ensure links are not broken";
    public static final String VALID_MEMBER_DG = "David";
    public static final String VALID_MEMBER_UG = "Charlie";

    public static final String TASK_DESC_DG = " " + PREFIX_DESCRIPTION + VALID_DESC_DG;
    public static final String TASK_DESC_UG = " " + PREFIX_DESCRIPTION + VALID_DESC_UG;
    public static final String TASK_DESC_TEST = " " + PREFIX_DESCRIPTION + VALID_DESC_TEST;
    public static final String DEADLINE_DESC_DG = " " + PREFIX_DEADLINE + VALID_DEADLINE_DG_STRING;
    public static final String DEADLINE_DESC_UG = " " + PREFIX_DEADLINE + VALID_DEADLINE_UG_STRING;
    public static final String PRIORITY_DESC_DG = " " + PREFIX_PRIORITY + VALID_PRIORITY_DG;
    public static final String PRIORITY_DESC_UG = " " + PREFIX_PRIORITY + VALID_PRIORITY_UG;
    public static final String MEMBER_DESC_DG = " " + PREFIX_MEMBER + VALID_MEMBER_DG;
    public static final String MEMBER_DESC_UG = " " + PREFIX_MEMBER + VALID_MEMBER_UG;
    public static final String NOTE_DESC_DG = " " + PREFIX_NOTE + VALID_NOTE_DG;
    public static final String INVALID_TASK_DESC = " " + PREFIX_DESCRIPTION + "Invalid Task/";
    // '/' not allowed in descriptions
    public static final String INVALID_DEADLINE_DESC = " " + PREFIX_DEADLINE + "2-2-2023";
    public static final String INVALID_PRIORITY_DESC = " " + PREFIX_PRIORITY + "hii";
    public static final String INVALID_MEMBER_DESC = " " + PREFIX_MEMBER + "/";
    // '/' not allowed in members
    public static final String INVALID_NOTE_DESC = " " + PREFIX_NOTE + "///";
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditTaskDescriptor DESC_DG;
    public static final EditCommand.EditTaskDescriptor DESC_UG;

    static {
        DESC_DG = new EditTaskDescriptorBuilder().withDescription(VALID_DESC_DG)
                .withMembers(VALID_MEMBER_DG).build();
        DESC_UG = new EditTaskDescriptorBuilder().withDescription(VALID_DESC_UG)
                .withMembers(VALID_MEMBER_DG, VALID_MEMBER_UG).build();
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
     * - the address book, filtered task list and selected task in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        TaskWise expectedTaskWise = new TaskWise(actualModel.getTaskWise());
        List<Task> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTaskList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTaskWise, actualModel.getTaskWise());
        assertEquals(expectedFilteredList, actualModel.getFilteredTaskList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String[] splitName = task.getDescription().fullDescription.split("\\s+");
        model.updateFilteredTaskList(new DescriptionContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredTaskList().size());
    }

}
