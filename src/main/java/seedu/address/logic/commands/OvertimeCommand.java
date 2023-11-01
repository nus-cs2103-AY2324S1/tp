package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPERATION;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Id;
import seedu.address.model.employee.OvertimeHours;

import java.util.List;
import java.util.Objects;

/**
 * Increases the overtime hours of an employee.
 */
public class OvertimeCommand extends Command{
    public static final String COMMAND_WORD = "overtime";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Increases/Decreases the overtime hours of an employee by the specified amount.\n"
            + "Parameters: " + PREFIX_ID + "ID (must be in the exact EID format) "
            + PREFIX_OPERATION + "OPERATION "
            + PREFIX_AMOUNT + "AMOUNT (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + " EID1234-5678 "
            + PREFIX_OPERATION + "inc "
            + PREFIX_AMOUNT + "2";

    public static final String MESSAGE_OVERTIME_EMPLOYEE_SUCCESS = "Overtime hours of employee %1$s increased by %2$s";
    public static final String MISSING_OPERATION_AMOUNT = "Operation and amount must be specified";
    private final Id targetId;
    private final int hoursToEdit;
    private final String operation;

    public OvertimeCommand(Id targetId, int hoursToEdit, String operation) {
        this.targetId = targetId;
        this.hoursToEdit = hoursToEdit;
        this.operation = operation;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (hoursToEdit <= 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_OVERTIME_HOURS);
        }

        List<Employee> lastShownList = model.getFilteredEmployeeList();
        Employee employeeToEdit = lastShownList.stream().filter(employee -> employee.getId().equals(targetId))
                .findFirst().orElse(null);
        
        if (employeeToEdit != null) {
            Employee editedEmployee = editEmployeeOvertime(employeeToEdit);
            model.setEmployee(employeeToEdit, editedEmployee);
            return new CommandResult(String.format(MESSAGE_OVERTIME_EMPLOYEE_SUCCESS, 
                    Messages.format(editedEmployee), hoursToEdit));
        }
        throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_ID);
    }

    private Employee editEmployeeOvertime(Employee employeeToEdit) {
        int updatedHours = 0;
        if (Objects.equals(operation, "inc")) {
            updatedHours = employeeToEdit.getOvertimeHours().value + hoursToEdit;
        } else if (Objects.equals(operation, "dec")) {
            updatedHours = employeeToEdit.getOvertimeHours().value - hoursToEdit;
        }
        OvertimeHours updatedOvertimeHours = new OvertimeHours(updatedHours);
        return new Employee(employeeToEdit.getName(), employeeToEdit.getPosition(),
                employeeToEdit.getId(), employeeToEdit.getPhone(), employeeToEdit.getEmail(),
                employeeToEdit.getSalary(), employeeToEdit.getDepartments(), updatedOvertimeHours,
                employeeToEdit.getLeaveList());
    }
}
