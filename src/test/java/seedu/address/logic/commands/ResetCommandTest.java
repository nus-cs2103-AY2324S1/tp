package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;
import static seedu.address.testutil.TypicalEmployees.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.LeaveList;
import seedu.address.model.employee.OvertimeHours;

public class ResetCommandTest {
    private static final String OVERTIME_FIELD = "overtime hours";
    private static final String LEAVES_FIELD = "allocated leaves";
    private static final String INVALID_FIELD = "invalid field";
    private static final String NO_FIELD = "";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_resetOvertime_success() {
        ResetCommand resetCommand = new ResetCommand(OVERTIME_FIELD);

        String expectedMessage = String.format(ResetCommand.MESSAGE_SUCCESS, OVERTIME_FIELD);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        List<Employee> lastShownList = expectedModel.getFilteredEmployeeList();
        for (Employee employee: lastShownList) {
            Employee employeeWithDefaultOvertime = new Employee(employee.getName(), employee.getPosition(),
                    employee.getId(), employee.getPhone(), employee.getEmail(), employee.getSalary(),
                    employee.getDepartments(), employee.getIsOnLeave(),
                    new OvertimeHours(Employee.DEFAULT_OVERTIME_HOURS), employee.getLeaveList());
            expectedModel.setEmployee(employee, employeeWithDefaultOvertime);
            model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
        }

        assertCommandSuccess(resetCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_resetLeaves_success() {
        ResetCommand resetCommand = new ResetCommand(LEAVES_FIELD);

        String expectedMessage = String.format(ResetCommand.MESSAGE_SUCCESS, LEAVES_FIELD);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        List<Employee> lastShownList = expectedModel.getFilteredEmployeeList();
        for (Employee employee: lastShownList) {
            Employee employeeWithDefaultLeaves = new Employee(employee.getName(), employee.getPosition(),
                    employee.getId(), employee.getPhone(), employee.getEmail(), employee.getSalary(),
                    employee.getDepartments(), employee.getIsOnLeave(),
                    employee.getOvertimeHours(), new LeaveList());
            expectedModel.setEmployee(employee, employeeWithDefaultLeaves);
            model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
        }

        assertCommandSuccess(resetCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_invalidField_failure() {
        ResetCommand resetCommand = new ResetCommand(INVALID_FIELD);

        String expectedMessage = String.format(ResetCommand.MESSAGE_WRONG_FIELD, INVALID_FIELD);

        assertCommandFailure(resetCommand, model, expectedMessage);
    }

    @Test
    void execute_missingField_failure() {
        ResetCommand resetCommand = new ResetCommand(NO_FIELD);

        String expectedMessage = ResetCommand.MESSAGE_NO_FIELD;

        assertCommandFailure(resetCommand, model, expectedMessage);
    }

}
