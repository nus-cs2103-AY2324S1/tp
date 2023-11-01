package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPERATION;

import java.util.List;
import java.util.Objects;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Id;
import seedu.address.model.employee.OvertimeHours;

/**
 * Updates the overtime hours of an employee.
 */
public class OvertimeCommand extends Command {
    public static final String COMMAND_WORD = "overtime";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Increases/Decreases (inc/dec) the overtime hours of an employee by the specified amount.\n"
            + "Parameters: " + PREFIX_ID + "ID (must be in the exact EID format) "
            + PREFIX_OPERATION + "OPERATION "
            + PREFIX_AMOUNT + "AMOUNT (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "EID1234-5678 "
            + PREFIX_OPERATION + "inc "
            + PREFIX_AMOUNT + "2";

    public static final String MESSAGE_OVERTIME_INCREASE_SUCCESS = "Overtime hours of employee %1$s increased by %2$s";
    public static final String MESSAGE_OVERTIME_DECREASE_SUCCESS = "Overtime hours of employee %1$s decreased by %2$s";
    public static final String MISSING_OPERATION_AMOUNT = "Operation and amount must be specified";
    private final Id targetId;
    private final OvertimeHours overtimeHoursToChange;
    private final String operation;

    /**
     * Creates an OvertimeCommand to update the overtime hours of the specified {@code Employee}
     */
    public OvertimeCommand(Id targetId, OvertimeHours overtimeHoursToChange, String operation) {
        this.targetId = targetId;
        this.overtimeHoursToChange = overtimeHoursToChange;
        this.operation = operation;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Employee> lastShownList = model.getFilteredEmployeeList();
        Employee employeeToUpdate = lastShownList.stream().filter(employee -> employee.getId().equals(targetId))
                .findFirst().orElse(null);

        if (employeeToUpdate != null) {
            Employee updatedEmployee = updateEmployeeOvertime(employeeToUpdate);
            model.setEmployee(employeeToUpdate, updatedEmployee);
            if (Objects.equals(operation, "dec")) {
                return new CommandResult(String.format(MESSAGE_OVERTIME_DECREASE_SUCCESS,
                        Messages.formatOvertimeHours(updatedEmployee), updatedEmployee.getOvertimeHours()));
            } else if (Objects.equals(operation, "inc")) {
                return new CommandResult(String.format(MESSAGE_OVERTIME_INCREASE_SUCCESS,
                        Messages.formatOvertimeHours(updatedEmployee), updatedEmployee.getOvertimeHours()));
            }
        }
        throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_ID);
    }

    private Employee updateEmployeeOvertime(Employee employeeToEdit) throws CommandException {
        OvertimeHours updatedOvertimeHours = updateOvertimeHours(employeeToEdit.getOvertimeHours());
        return new Employee(employeeToEdit.getName(), employeeToEdit.getPosition(),
                employeeToEdit.getId(), employeeToEdit.getPhone(), employeeToEdit.getEmail(),
                employeeToEdit.getSalary(), employeeToEdit.getDepartments(), updatedOvertimeHours,
                employeeToEdit.getLeaveList());
    }

    private OvertimeHours updateOvertimeHours(OvertimeHours overtimeHours) throws CommandException {
        int updatedHours = 0;
        if (Objects.equals(operation, "inc")) {
            updatedHours = overtimeHours.value + overtimeHoursToChange.value;
        } else if (Objects.equals(operation, "dec")) {
            updatedHours = overtimeHours.value - overtimeHoursToChange.value;
        }
        if (!OvertimeHours.isValidOvertimeHours(updatedHours)) {
            throw new CommandException(OvertimeHours.MESSAGE_CONSTRAINTS);
        }
        return new OvertimeHours(updatedHours);
    }
}
