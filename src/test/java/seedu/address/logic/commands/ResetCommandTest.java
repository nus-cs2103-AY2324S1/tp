package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;
import static seedu.address.testutil.TypicalEmployees.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EMPLOYEE;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.LeaveList;
import seedu.address.model.employee.OvertimeHours;

public class ResetCommandTest {
    private static final String OVERTIME_FIELD = "overtime";
    private static final String LEAVES_FIELD = "leaves";
    private static final String INVALID_FIELD = "invalid field";
    private static final String NO_FIELD = "";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model emptyModel = new ModelManager(new AddressBook(), new UserPrefs());

    @Test
    void isValidField_nullField_throwsNullPointerException() {
        ResetCommand resetCommand = new ResetCommand("overtime");
        assertThrows(NullPointerException.class, () -> resetCommand.isValidField(null));
    }

    @Test
    void isValidField_invalidField_throwsCommandException() {
        ResetCommand resetCommand = new ResetCommand("overtime");

        // empty field
        assertThrows(CommandException.class, () -> resetCommand.isValidField(NO_FIELD));

        // invalid field
        assertThrows(CommandException.class, () -> resetCommand.isValidField(INVALID_FIELD));
    }

    @Test
    void isValidField_validField_doesNotThrow() {
        ResetCommand resetCommand = new ResetCommand("overtime");
        assertAll(() -> {
            // overtime
            resetCommand.isValidField(OVERTIME_FIELD);

            // leaves
            resetCommand.isValidField(LEAVES_FIELD);
        });
    }

    @Test
    void resetOvertime_nullModelNullEmployee_throwsNullPointerException() {
        ResetCommand resetCommand = new ResetCommand("overtime");
        Employee employee = model.getFilteredEmployeeList().get(INDEX_FIRST_EMPLOYEE.getZeroBased());

        // null model
        assertThrows(NullPointerException.class, () -> resetCommand.resetOvertime(null, employee));

        // null employee
        assertThrows(NullPointerException.class, () -> resetCommand.resetOvertime(model, null));

        // null model and employee
        assertThrows(NullPointerException.class, () -> resetCommand.resetOvertime(null, null));
    }

    @Test
    void resetOvertime_success() {
        ResetCommand resetCommand = new ResetCommand("overtime");
        Employee employee = model.getFilteredEmployeeList().get(INDEX_FIRST_EMPLOYEE.getZeroBased());
        Employee employeeWithDefaultOvertime = new Employee(employee.getName(), employee.getPosition(),
                employee.getId(), employee.getPhone(), employee.getEmail(), employee.getSalary(),
                employee.getDepartments(), new OvertimeHours(Employee.DEFAULT_OVERTIME_HOURS), employee.getLeaveList(),
                employee.getRemarkList());
        resetCommand.resetOvertime(model, employee);
        assertEquals(employeeWithDefaultOvertime,
                model.getFilteredEmployeeList().get(INDEX_FIRST_EMPLOYEE.getZeroBased()));
    }

    @Test
    void resetLeaves_nullModelNullEmployee_throwsNullPointerException() {
        ResetCommand resetCommand = new ResetCommand("leaves");
        Employee employee = model.getFilteredEmployeeList().get(INDEX_FIRST_EMPLOYEE.getZeroBased());

        // null model
        assertThrows(NullPointerException.class, () -> resetCommand.resetLeaves(null, employee));

        // null employee
        assertThrows(NullPointerException.class, () -> resetCommand.resetLeaves(model, null));

        // null model and employee
        assertThrows(NullPointerException.class, () -> resetCommand.resetLeaves(null, null));
    }

    @Test
    void resetLeaves_success() {
        ResetCommand resetCommand = new ResetCommand("leaves");
        Employee employee = model.getFilteredEmployeeList().get(INDEX_FIRST_EMPLOYEE.getZeroBased());
        Employee employeeWithDefaultLeaveList = new Employee(employee.getName(), employee.getPosition(),
                employee.getId(), employee.getPhone(), employee.getEmail(), employee.getSalary(),
                employee.getDepartments(), employee.getOvertimeHours(), new LeaveList(),
                employee.getRemarkList());
        resetCommand.resetOvertime(model, employee);
        assertEquals(employeeWithDefaultLeaveList,
                model.getFilteredEmployeeList().get(INDEX_FIRST_EMPLOYEE.getZeroBased()));
    }

    @Test
    void execute_resetOvertime_success() {
        ResetCommand resetCommand = new ResetCommand(OVERTIME_FIELD);

        String expectedMessage = String.format(ResetCommand.MESSAGE_SUCCESS, OVERTIME_FIELD);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        List<Employee> lastShownList = expectedModel.getFilteredEmployeeList();
        for (Employee employee: lastShownList) {
            Employee employeeWithDefaultOvertime = new Employee(employee.getName(), employee.getPosition(),
                    employee.getId(), employee.getPhone(), employee.getEmail(), employee.getSalary(),
                    employee.getDepartments(), new OvertimeHours(Employee.DEFAULT_OVERTIME_HOURS),
                    employee.getLeaveList(), employee.getRemarkList());
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
        expectedModel.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
        List<Employee> lastShownList = expectedModel.getFilteredEmployeeList();
        for (Employee employee: lastShownList) {
            Employee employeeWithDefaultLeaves = new Employee(employee.getName(), employee.getPosition(),
                    employee.getId(), employee.getPhone(), employee.getEmail(), employee.getSalary(),
                    employee.getDepartments(), employee.getOvertimeHours(), new LeaveList(), employee.getRemarkList());
            expectedModel.setEmployee(employee, employeeWithDefaultLeaves);
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

    @Test
    void execute_emptyEmployeeList_success() {
        ResetCommand resetCommand = new ResetCommand(OVERTIME_FIELD);

        String expectedMessage = String.format(ResetCommand.MESSAGE_SUCCESS, OVERTIME_FIELD);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        List<Employee> lastShownList = expectedModel.getFilteredEmployeeList();
        for (Employee employee: lastShownList) {
            Employee employeeWithDefaultOvertime = new Employee(employee.getName(), employee.getPosition(),
                    employee.getId(), employee.getPhone(), employee.getEmail(), employee.getSalary(),
                    employee.getDepartments(), new OvertimeHours(Employee.DEFAULT_OVERTIME_HOURS),
                    employee.getLeaveList(), employee.getRemarkList());
            expectedModel.setEmployee(employee, employeeWithDefaultOvertime);
            emptyModel.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
        }

        assertCommandSuccess(resetCommand, emptyModel, expectedMessage, expectedModel);
    }

}
