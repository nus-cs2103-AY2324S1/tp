package seedu.letsgethired.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_NOTE_INSERT;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.letsgethired.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.util.Pair;
import seedu.letsgethired.commons.core.index.Index;
import seedu.letsgethired.logic.commands.exceptions.CommandException;
import seedu.letsgethired.model.InternTracker;
import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.application.CompanyContainsFieldKeywordsPredicate;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.testutil.EditInternApplicationDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_COMPANY_JANE_STREET = "Jane Street";
    public static final String VALID_COMPANY_BYTEDANCE = "ByteDance";
    public static final String VALID_ROLE_FULL_STACK = "Full Stack Developer";
    public static final String VALID_ROLE_BACK_END = "Back End Developer";
    public static final String VALID_CYCLE_SUMMER = "Summer 2024";
    public static final String VALID_CYCLE_WINTER = "Winter 2023";
    public static final String VALID_STATUS_ACCEPTED = "Accepted";
    public static final String VALID_STATUS_REJECTED = "Rejected";
    public static final String VALID_NOTE_JANE_STREET = "Jane Street is the leading market maker in the APAC region";
    public static final String VALID_NOTE_BYTEDANCE = "Bytedance requires back end developers to know the MERN stack";
    public static final String VALID_SORT_ORDER_ASCENDING = "a";
    public static final String VALID_SORT_ORDER_DESCENDING = "d";
    public static final String VALID_DEADLINE = "25 Oct 2023";
    public static final String COMPANY_DESC_JANE_STREET = " " + PREFIX_COMPANY + VALID_COMPANY_JANE_STREET;
    public static final String COMPANY_DESC_BYTEDANCE = " " + PREFIX_COMPANY + VALID_COMPANY_BYTEDANCE;
    public static final String ROLE_DESC_FULL_STACK = " " + PREFIX_ROLE + VALID_ROLE_FULL_STACK;
    public static final String ROLE_DESC_BACK_END = " " + PREFIX_ROLE + VALID_ROLE_BACK_END;
    public static final String CYCLE_DESC_SUMMER = " " + PREFIX_CYCLE + VALID_CYCLE_SUMMER;
    public static final String CYCLE_DESC_WINTER = " " + PREFIX_CYCLE + VALID_CYCLE_WINTER;
    public static final String STATUS_DESC_ACCEPTED = " " + PREFIX_STATUS + VALID_STATUS_ACCEPTED;
    public static final String NOTE_DESC_JANE_STREET = " " + PREFIX_NOTE_INSERT + VALID_NOTE_JANE_STREET;
    public static final String NOTE_DESC_BYTEDANCE = " " + PREFIX_NOTE_INSERT + VALID_NOTE_BYTEDANCE;
    public static final String STATUS_DESC_REJECTED = " " + PREFIX_STATUS + VALID_STATUS_REJECTED;
    public static final String COMPANY_SORT_ORDER_ASCENDING = " " + PREFIX_COMPANY + VALID_SORT_ORDER_ASCENDING;
    public static final String CYCLE_SORT_ORDER_DESCENDING = " " + PREFIX_CYCLE + VALID_SORT_ORDER_DESCENDING;
    public static final String ROLE_SORT_ORDER_ASCENDING = " " + PREFIX_ROLE + VALID_SORT_ORDER_ASCENDING;
    public static final String STATUS_SORT_ORDER_DESCENDING = " " + PREFIX_STATUS + VALID_SORT_ORDER_DESCENDING;
    public static final String DEADLINE_SORT_ORDER_DESCENDING = " " + PREFIX_DEADLINE + VALID_SORT_ORDER_DESCENDING;
    public static final String MULTIPLE_SORT_ORDERS = " " + PREFIX_DEADLINE + VALID_SORT_ORDER_DESCENDING
            + " " + PREFIX_CYCLE + VALID_SORT_ORDER_DESCENDING;
    public static final String INVALID_COMPANY_DESC =
            " " + PREFIX_COMPANY + "Jane Street/"; // '/' not allowed in company names
    public static final String INVALID_ROLE_DESC = " " + PREFIX_ROLE + " "; // empty string is not allowed in roles
    public static final String INVALID_CYCLE_DESC = " " + PREFIX_CYCLE + "Summer!2023"; // '!' not allowed in cycles
    public static final String INVALID_STATUS_DESC = " " + PREFIX_STATUS; // empty string not allowed in status
    public static final String INVALID_COMPANY_SORT_ORDER = " " + PREFIX_COMPANY + "z";
    public static final String INVALID_ROLE_SORT_ORDER = " " + PREFIX_ROLE + "!";
    public static final String INVALID_CYCLE_SORT_ORDER = " " + PREFIX_CYCLE + "b";
    public static final String INVALID_STATUS_SORT_ORDER = " " + PREFIX_STATUS + "?";
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditInternApplicationDescriptor DESC_JANE_STREET;
    public static final EditCommand.EditInternApplicationDescriptor DESC_BYTEDANCE;

    static {
        DESC_JANE_STREET = new EditInternApplicationDescriptorBuilder()
                .withCompany(VALID_COMPANY_JANE_STREET)
                .withRole(VALID_ROLE_FULL_STACK)
                .withCycle(VALID_CYCLE_SUMMER)
                .withStatus(VALID_STATUS_ACCEPTED)
                .withDeadline(VALID_DEADLINE)
                .build();
        DESC_BYTEDANCE = new EditInternApplicationDescriptorBuilder()
                .withCompany(VALID_COMPANY_BYTEDANCE)
                .withRole(VALID_ROLE_BACK_END)
                .withCycle(VALID_CYCLE_WINTER)
                .withStatus(VALID_STATUS_REJECTED)
                .withDeadline(VALID_DEADLINE)
                .build();
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
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            InternApplication expectedApplication, Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, expectedApplication);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the intern tracker, filtered intern application list and
     * selected intern application in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        InternTracker expectedInternTracker = new InternTracker(actualModel.getInternTracker());
        List<InternApplication> expectedFilteredList = new ArrayList<>(actualModel.getFilteredInternApplicationList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedInternTracker, actualModel.getInternTracker());
        assertEquals(expectedFilteredList, actualModel.getFilteredInternApplicationList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the intern application at the given {@code targetIndex} in the
     * {@code model}'s intern tracker.
     */
    public static void showInternApplicationAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredInternApplicationList().size());

        InternApplication internApplication = model.getFilteredInternApplicationList().get(targetIndex.getZeroBased());
        final String searchString = internApplication.getCompany().value;
        model.updateFilteredInternApplicationList(new CompanyContainsFieldKeywordsPredicate(Arrays.asList(
                new Pair<>(PREFIX_COMPANY, searchString))));

        assertEquals(1, model.getFilteredInternApplicationList().size());
    }
}
