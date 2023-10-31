package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Id;
import seedu.address.model.employee.OvertimeHours;

import java.util.List;

/**
 * Increases the overtime hours of an employee.
 */
public class OvertimeCommand extends Command{
    public static final String COMMAND_WORD = "overtime";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Increases the overtime hours of an employee by the specified amount.\n"
            + "Parameters: " + PREFIX_ID + "ID (must be in the exact EID format) "
            + PREFIX_OPERATION + "OPERATION "
            + PREFIX_AMOUNT + "AMOUNT (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + " EID1234-5678 "
            + PREFIX_OPERATION + "inc "
            + PREFIX_AMOUNT + "2";

    public static final String MESSAGE_OVERTIME_EMPLOYEE_SUCCESS = "Overtime hours of employee %1$s increased by %2$s";

    private final Id targetId;

    private final OvertimeHours overtimeHours;

    public OvertimeCommand(Id targetId, OvertimeHours overtimeHours) {
        this.targetId = targetId;
        this.overtimeHours = overtimeHours;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (overtimeHours.value <= 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_OVERTIME_HOURS);
        }

        List<Employee> lastShownList = model.getFilteredEmployeeList();
        Employee employeeToEdit = lastShownList.stream().filter(employee -> employee.getId().equals(targetId))
                .findFirst().orElse(null);
        
        if (employeeToEdit != null) {
            Employee editedEmployee = getEditedEmployee(employeeToEdit);
            model.setEmployee(employeeToEdit, editedEmployee);
            return new CommandResult(String.format(MESSAGE_OVERTIME_EMPLOYEE_SUCCESS, 
                    Messages.format(editedEmployee), overtimeHours));
        }
        throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_ID);
    }

    private Employee getEditedEmployee(Employee employeeToEdit) {
        OvertimeHours updatedOvertimeHours = new OvertimeHours(
                employeeToEdit.getOvertimeHours().value - overtimeHours.value);
        return new Employee(employeeToEdit.getName(), employeeToEdit.getPosition(),
                employeeToEdit.getId(), employeeToEdit.getPhone(), employeeToEdit.getEmail(),
                employeeToEdit.getSalary(), employeeToEdit.getDepartments(), updatedOvertimeHours,
                employeeToEdit.getLeaveList());
    }
}
