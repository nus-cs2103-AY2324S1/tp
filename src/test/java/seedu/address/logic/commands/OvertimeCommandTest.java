package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OVERTIME_HOURS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEmployees.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EMPLOYEE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Id;
import seedu.address.model.employee.OvertimeHours;
import seedu.address.testutil.EmployeeBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for OvertimeCommand.
 */
class OvertimeCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void execute_incrementOvertimeHoursUnfilteredList_success() {
        Employee employee = model.getAddressBook().getEmployeeList().get(INDEX_FIRST_EMPLOYEE.getZeroBased());
        OvertimeHours changeInOvertimeHours = new OvertimeHours(VALID_OVERTIME_HOURS_BOB);
        OvertimeCommand overtimeCommand = new OvertimeCommand(employee.getId(), changeInOvertimeHours, true);

        Employee updatedEmployee = new EmployeeBuilder(employee).withOvertimeHours(VALID_OVERTIME_HOURS_BOB).build();

        String expectedMessage = String.format(OvertimeCommand.MESSAGE_OVERTIME_INCREASE_SUCCESS,
                Messages.formatOvertimeHours(updatedEmployee), changeInOvertimeHours);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setEmployee(model.getAddressBook().getEmployeeList().get(0), updatedEmployee);

        assertCommandSuccess(overtimeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_decrementOvertimeHoursUnfilteredList_success() {
        Index indexLastEmployee = Index.fromOneBased(model.getAddressBook().getEmployeeList().size());
        Employee lastEmployee = model.getAddressBook().getEmployeeList().get(indexLastEmployee.getZeroBased());
        OvertimeHours changeInOvertimeHours = new OvertimeHours(VALID_OVERTIME_HOURS_BOB);
        OvertimeCommand overtimeCommand = new OvertimeCommand(lastEmployee.getId(), changeInOvertimeHours, false);

        int updatedOvertimeHours = lastEmployee.getOvertimeHours().value - changeInOvertimeHours.value; // should be 0
        Employee updatedEmployee = new EmployeeBuilder(lastEmployee)
                .withOvertimeHours(updatedOvertimeHours).build();

        String expectedMessage = String.format(OvertimeCommand.MESSAGE_OVERTIME_DECREASE_SUCCESS,
                Messages.formatOvertimeHours(updatedEmployee), changeInOvertimeHours);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setEmployee(lastEmployee, updatedEmployee);

        assertCommandSuccess(overtimeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_incrementOvertimeHours_failure() {
        Index indexLastEmployee = Index.fromOneBased(model.getAddressBook().getEmployeeList().size());
        Employee lastEmployee = model.getAddressBook().getEmployeeList().get(indexLastEmployee.getZeroBased());
        OvertimeHours changeInOvertimeHours = new OvertimeHours(VALID_OVERTIME_HOURS_BOB);
        OvertimeCommand overtimeCommand = new OvertimeCommand(lastEmployee.getId(), changeInOvertimeHours, true);

        assertCommandFailure(overtimeCommand, model, OvertimeHours.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void execute_decrementOvertimeHours_failure() {
        Employee employee = model.getAddressBook().getEmployeeList().get(INDEX_FIRST_EMPLOYEE.getZeroBased());
        OvertimeHours changeInOvertimeHours = new OvertimeHours(VALID_OVERTIME_HOURS_BOB);
        OvertimeCommand overtimeCommand = new OvertimeCommand(employee.getId(), changeInOvertimeHours, false);

        assertCommandFailure(overtimeCommand, model, OvertimeHours.MESSAGE_CONSTRAINTS);
    }

    @Test
    void execute_invalidId_failure() {
        OvertimeHours changeInOvertimeHours = new OvertimeHours(VALID_OVERTIME_HOURS_BOB);
        OvertimeCommand overtimeCommand = new OvertimeCommand(new Id("EID0000-0000"), changeInOvertimeHours, true);
        assertCommandFailure(overtimeCommand, model, Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_ID);
    }

    @Test
    public void equals() {
        OvertimeHours changeInOvertimeHours = new OvertimeHours(VALID_OVERTIME_HOURS_BOB);
        Id idBob = new Id(VALID_ID_BOB);
        OvertimeCommand overtimeCommand = new OvertimeCommand(idBob, changeInOvertimeHours, true);

        // same object -> returns true
        assertTrue(overtimeCommand.equals(overtimeCommand));

        // same values -> returns true
        OvertimeCommand overtimeCommandCopy = new OvertimeCommand(idBob, changeInOvertimeHours, true);
        assertTrue(overtimeCommand.equals(overtimeCommandCopy));

        // different types -> returns false
        assertFalse(overtimeCommand.equals(new ClearCommand()));

        // null -> returns false
        assertFalse(overtimeCommand.equals(null));

        // different employee -> returns false
        Id idAmy = new Id(VALID_ID_AMY);
        OvertimeCommand differentEmployeeCommand = new OvertimeCommand(idAmy, changeInOvertimeHours,
                false);
        assertFalse(overtimeCommand.equals(differentEmployeeCommand));

        // different change in overtime hours -> returns false
        OvertimeCommand differentChangeInOvertimeHoursCommand = new OvertimeCommand(idBob,
                new OvertimeHours(VALID_OVERTIME_HOURS_BOB + 1), true);
        assertFalse(overtimeCommand.equals(differentChangeInOvertimeHoursCommand));

        // different isIncrement -> returns false
        OvertimeCommand differentIsIncrementCommand = new OvertimeCommand(idBob, changeInOvertimeHours,
                false);
        assertFalse(overtimeCommand.equals(differentIsIncrementCommand));
    }

    @Test
    public void toStringMethod() {
        OvertimeHours changeInOvertimeHours = new OvertimeHours(VALID_OVERTIME_HOURS_BOB);
        Id idBob = new Id(VALID_ID_BOB);
        OvertimeCommand overtimeCommand = new OvertimeCommand(idBob, changeInOvertimeHours, true);
        String expectedString = OvertimeCommand.class.getCanonicalName() + "{targetId=" + idBob
                + ", changeInOvertimeHours=" + changeInOvertimeHours + ", isIncrement=true}";
        assertEquals(expectedString, overtimeCommand.toString());
    }
}
