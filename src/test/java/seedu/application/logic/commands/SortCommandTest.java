package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.application.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_JOB_TYPE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.application.testutil.TypicalJobs.getTypicalApplicationBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;
import seedu.application.model.job.FieldComparator;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortCommand.
 */
public class SortCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getApplicationBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsSortedByCompany_showsSortedListByCompany() {
        FieldComparator fieldComparator = new FieldComparator(PREFIX_COMPANY);
        expectedModel.sortJobs(fieldComparator);
        assertCommandSuccess(new SortCommand(fieldComparator), model,
            SortCommand.MESSAGE_SUCCESS, SortCommand.CLEARS_DETAILS_PANEL, expectedModel);
    }

    @Test
    public void execute_listIsSortedByRoles_showsSortedListByRole() {
        FieldComparator fieldComparator = new FieldComparator(PREFIX_ROLE);
        expectedModel.sortJobs(fieldComparator);
        assertCommandSuccess(new SortCommand(fieldComparator), model,
            SortCommand.MESSAGE_SUCCESS, SortCommand.CLEARS_DETAILS_PANEL, expectedModel);
    }

    @Test
    public void execute_listIsSortedByStatus_showsSortedListByStatus() {
        FieldComparator fieldComparator = new FieldComparator(PREFIX_STATUS);
        expectedModel.sortJobs(fieldComparator);
        assertCommandSuccess(new SortCommand(fieldComparator), model,
            SortCommand.MESSAGE_SUCCESS, SortCommand.CLEARS_DETAILS_PANEL, expectedModel);
    }

    @Test
    public void execute_listIsSortedByIndustry_showsSortedListByIndustry() {
        FieldComparator fieldComparator = new FieldComparator(PREFIX_INDUSTRY);
        expectedModel.sortJobs(fieldComparator);
        assertCommandSuccess(new SortCommand(fieldComparator), model,
            SortCommand.MESSAGE_SUCCESS, SortCommand.CLEARS_DETAILS_PANEL, expectedModel);
    }

    @Test
    public void execute_listIsSortedByDeadline_showsSortedListByDeadline() {
        FieldComparator fieldComparator = new FieldComparator(PREFIX_DEADLINE);
        expectedModel.sortJobs(fieldComparator);
        assertCommandSuccess(new SortCommand(fieldComparator), model,
            SortCommand.MESSAGE_SUCCESS, SortCommand.CLEARS_DETAILS_PANEL, expectedModel);
    }

    @Test
    public void execute_listIsSortedByJobType_showsSortedListByJobType() {
        FieldComparator fieldComparator = new FieldComparator(PREFIX_JOB_TYPE);
        expectedModel.sortJobs(fieldComparator);
        assertCommandSuccess(new SortCommand(fieldComparator), model,
            SortCommand.MESSAGE_SUCCESS, SortCommand.CLEARS_DETAILS_PANEL, expectedModel);
    }

    @Test
    public void equals() {
        SortCommand listByRoleCommand = new SortCommand(new FieldComparator(PREFIX_ROLE));

        // same object -> returns true
        assertEquals(listByRoleCommand, listByRoleCommand);

        // same values -> returns true
        SortCommand listByRoleCommandCopy = new SortCommand(new FieldComparator(PREFIX_ROLE));
        assertEquals(listByRoleCommand, listByRoleCommandCopy);

        // different types -> returns false
        assertNotEquals(listByRoleCommand, 5.0f);

        // null -> returns false
        assertNotEquals(listByRoleCommand, null);

        // different person -> returns false
        SortCommand listByCompanyCommand = new SortCommand(new FieldComparator(PREFIX_COMPANY));
        assertNotEquals(listByRoleCommand, listByCompanyCommand);
    }
}
