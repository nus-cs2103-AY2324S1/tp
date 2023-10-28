package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEmployees.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIds.ID_EMPLOYEE_NOT_EXISTS;
import static seedu.address.testutil.TypicalIds.ID_FIRST_EMPLOYEE;
import static seedu.address.testutil.TypicalIds.ID_SECOND_EMPLOYEE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EMPLOYEE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Id;



/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ReportCommand}.
 */
public class ReportCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ReportCommand reportFirstCommand = new ReportCommand(ID_FIRST_EMPLOYEE);
        ReportCommand reportSecondCommand = new ReportCommand(ID_SECOND_EMPLOYEE);

        // same object -> returns true
        assertTrue(reportFirstCommand.equals(reportFirstCommand));

        // same values -> returns true
        ReportCommand deleteFirstCommandCopy = new ReportCommand(ID_FIRST_EMPLOYEE);
        assertTrue(reportFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(reportFirstCommand.equals(1));

        // null -> returns false
        assertFalse(reportFirstCommand.equals(null));

        // different employee -> returns false
        assertFalse(reportFirstCommand.equals(reportSecondCommand));
    }

    @Test
    public void execute_validId_success() {
        Employee employee = model.getFilteredEmployeeList().get(INDEX_FIRST_EMPLOYEE.getZeroBased());
        ReportCommand reportCommand = new ReportCommand(employee.getId());

        String expectedMessage = String.format(Messages.MESSAGE_REPORT_STRING, employee.getName(),
                employee.getOvertimeHours(), employee.getOvertimePay(), employee.getNumOfLeaves());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setEmployee(model.getFilteredEmployeeList().get(0), employee);

        assertCommandSuccess(reportCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidId_throwsCommandException() {
        Id invalidId = ID_EMPLOYEE_NOT_EXISTS;
        ReportCommand reportCommand = new ReportCommand(invalidId);

        assertCommandFailure(reportCommand, model, Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_ID);
    }

    @Test
    public void toStringMethod() {
        //Index targetIndex = Index.fromOneBased(1);
        ReportCommand reportCommand = new ReportCommand(ID_FIRST_EMPLOYEE);
        String expected = ReportCommand.class.getCanonicalName() + "{targetId=" + ID_FIRST_EMPLOYEE + "}";
        assertEquals(expected, reportCommand.toString());
    }
}
