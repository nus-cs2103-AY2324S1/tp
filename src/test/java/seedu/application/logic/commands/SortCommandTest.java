package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.application.model.job.Company.COMPANY_SPECIFIER;
import static seedu.application.model.job.Role.ROLE_SPECIFIER;
import static seedu.application.testutil.TypicalJobs.getTypicalApplicationBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;
import seedu.application.model.job.*;

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
    public void execute_listIsSortedByRoles_showsSortedListByRole() {
        FieldComparator fieldComparator = new FieldComparator(ROLE_SPECIFIER);
        expectedModel.sortJobs(fieldComparator);
        assertCommandSuccess(new SortCommand(fieldComparator), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsSortedByCompany_showsSortedListByCompany() {
        FieldComparator fieldComparator = new FieldComparator(COMPANY_SPECIFIER);
        expectedModel.sortJobs(fieldComparator);
        assertCommandSuccess(new SortCommand(fieldComparator), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsSortedByDeadline_showsSortedListByDeadline() {
        FieldComparator fieldComparator = new FieldComparator(Deadline.DEADLINE_SPECIFIER);
        expectedModel.sortJobs(fieldComparator);
        assertCommandSuccess(new SortCommand(fieldComparator), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsSortedByStatus_showsSortedListByStatus() {
        FieldComparator fieldComparator = new FieldComparator(Status.STATUS_SPECIFIER);
        expectedModel.sortJobs(fieldComparator);
        assertCommandSuccess(new SortCommand(fieldComparator), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        SortCommand listByRoleCommand = new SortCommand(new FieldComparator(ROLE_SPECIFIER));

        // same object -> returns true
        assertEquals(listByRoleCommand, listByRoleCommand);

        // same values -> returns true
        SortCommand listByRoleCommandCopy = new SortCommand(new FieldComparator(ROLE_SPECIFIER));
        assertEquals(listByRoleCommand, listByRoleCommandCopy);

        // different types -> returns false
        assertNotEquals(listByRoleCommand, 5.0f);

        // null -> returns false
        assertNotEquals(listByRoleCommand, null);

        // different person -> returns false
        SortCommand listByCompanyCommand = new SortCommand(new FieldComparator(COMPANY_SPECIFIER));
        assertNotEquals(listByRoleCommand, listByCompanyCommand);
    }
}
