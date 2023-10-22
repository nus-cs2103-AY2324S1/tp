package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.application.logic.commands.CommandTestUtil.showJobAtIndex;
import static seedu.application.model.job.Company.COMPANY_SPECIFIER;
import static seedu.application.model.job.Role.ROLE_SPECIFIER;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST_JOB;
import static seedu.application.testutil.TypicalJobs.getTypicalApplicationBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;
import seedu.application.model.job.*;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getApplicationBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(new FieldComparator(FieldComparator.EMPTY_COMPARATOR_SPECIFIER)),
            model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showJobAtIndex(model, INDEX_FIRST_JOB);
        assertCommandSuccess(new ListCommand(new FieldComparator(FieldComparator.EMPTY_COMPARATOR_SPECIFIER)),
            model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsSortedByRoles_showsSortedListByRoles() {
        FieldComparator fieldComparator = new FieldComparator(ROLE_SPECIFIER);
        expectedModel.sortJobs(fieldComparator);
        assertCommandSuccess(new ListCommand(fieldComparator), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsSortedByCompany_showsSortedListByCompany() {
        FieldComparator fieldComparator = new FieldComparator(COMPANY_SPECIFIER);
        expectedModel.sortJobs(fieldComparator);
        assertCommandSuccess(new ListCommand(fieldComparator), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsSortedByDeadline_showsSortedListByDeadline() {
        FieldComparator fieldComparator = new FieldComparator(Deadline.DEADLINE_SPECIFIER);
        expectedModel.sortJobs(fieldComparator);
        assertCommandSuccess(new ListCommand(fieldComparator), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsSortedByStatus_showsSortedListByStatus() {
        FieldComparator fieldComparator = new FieldComparator(Status.STATUS_SPECIFIER);
        expectedModel.sortJobs(fieldComparator);
        assertCommandSuccess(new ListCommand(fieldComparator), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        ListCommand listByRoleCommand = new ListCommand(new FieldComparator(ROLE_SPECIFIER));

        // same object -> returns true
        assertTrue(listByRoleCommand.equals(listByRoleCommand));

        // same values -> returns true
        ListCommand listByRoleCommandCopy = new ListCommand(new FieldComparator(ROLE_SPECIFIER));
        assertTrue(listByRoleCommand.equals(listByRoleCommandCopy));

        // different types -> returns false
        assertFalse(listByRoleCommand.equals(1));

        // null -> returns false
        assertFalse(listByRoleCommand.equals(null));

        // different person -> returns false
        ListCommand listByCompanyCommand = new ListCommand(new FieldComparator(COMPANY_SPECIFIER));
        assertFalse(listByRoleCommand.equals(listByCompanyCommand));
    }
}
