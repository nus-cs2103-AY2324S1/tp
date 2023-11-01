package seedu.address.logic.commands;

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
import seedu.address.model.employee.OvertimeHours;
import seedu.address.testutil.EmployeeBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for OvertimeCommand.
 */
class OvertimeCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    void execute_incrementOvertimeHours_success() {
        Employee employee = model.getFilteredEmployeeList().get(INDEX_FIRST_EMPLOYEE.getZeroBased());
        OvertimeHours overtimeHoursToChange = new OvertimeHours(VALID_OVERTIME_HOURS_BOB);
        OvertimeCommand overtimeCommand = new OvertimeCommand(employee.getId(), overtimeHoursToChange, "inc");

        Employee updatedEmployee = new EmployeeBuilder(employee).withOvertimeHours(VALID_OVERTIME_HOURS_BOB).build();

        String expectedMessage = String.format(OvertimeCommand.MESSAGE_OVERTIME_INCREASE_SUCCESS,
                Messages.formatOvertimeHours(updatedEmployee), overtimeHoursToChange);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setEmployee(model.getFilteredEmployeeList().get(0), updatedEmployee);

        assertCommandSuccess(overtimeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_decrementOvertimeHours_success() {
        Index indexLastEmployee = Index.fromOneBased(model.getFilteredEmployeeList().size());
        Employee lastEmployee = model.getFilteredEmployeeList().get(indexLastEmployee.getZeroBased());
        OvertimeHours overtimeHoursToChange = new OvertimeHours(VALID_OVERTIME_HOURS_BOB);
        OvertimeCommand overtimeCommand = new OvertimeCommand(lastEmployee.getId(), overtimeHoursToChange, "dec");

        int updatedOvertimeHours = lastEmployee.getOvertimeHours().value - overtimeHoursToChange.value; // should be 0
        Employee updatedEmployee = new EmployeeBuilder(lastEmployee)
                .withOvertimeHours(updatedOvertimeHours).build();

        String expectedMessage = String.format(OvertimeCommand.MESSAGE_OVERTIME_DECREASE_SUCCESS,
                Messages.formatOvertimeHours(updatedEmployee), overtimeHoursToChange);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setEmployee(lastEmployee, updatedEmployee);

        assertCommandSuccess(overtimeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_incrementOvertimeHours_failure() {
        Employee employee = model.getFilteredEmployeeList().get(INDEX_FIRST_EMPLOYEE.getZeroBased());
        OvertimeHours overtimeHoursToChange = new OvertimeHours(VALID_OVERTIME_HOURS_BOB);
        OvertimeCommand overtimeCommand = new OvertimeCommand(employee.getId(), overtimeHoursToChange, "dec");

        assertCommandFailure(overtimeCommand, model, OvertimeHours.MESSAGE_CONSTRAINTS);
    }

    @Test
    void execute_decrementOvertimeHours_failure() {
        Index indexLastEmployee = Index.fromOneBased(model.getFilteredEmployeeList().size());
        Employee lastEmployee = model.getFilteredEmployeeList().get(indexLastEmployee.getZeroBased());
        OvertimeHours overtimeHoursToChange = new OvertimeHours(VALID_OVERTIME_HOURS_BOB);
        OvertimeCommand overtimeCommand = new OvertimeCommand(lastEmployee.getId(), overtimeHoursToChange, "inc");

        assertCommandFailure(overtimeCommand, model, OvertimeHours.MESSAGE_CONSTRAINTS);
    }
}
