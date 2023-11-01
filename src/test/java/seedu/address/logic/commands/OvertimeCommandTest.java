package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalEmployees.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.employee.Employee;
import seedu.address.testutil.EmployeeBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for OvertimeCommand.
 */
class OvertimeCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    void execute_incrementOvertimeHours_success() {
        Employee editedEmployee = new EmployeeBuilder().build();
        OvertimeCommand overtimeCommand = new OvertimeCommand(editedEmployee.getId(), editedEmployee.getOvertimeHours(), "inc");
    }
}