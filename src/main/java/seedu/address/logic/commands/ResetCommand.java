package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.LeaveList;
import seedu.address.model.employee.OvertimeHours;

/**
 * Resets the given field of all employees to their default value.
 */
public class ResetCommand extends Command {
    public static final String COMMAND_WORD = "reset";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Resets the given field (overtime / leaves) of all employees to their default value. "
            + "Parameters: "
            + PREFIX_FIELD + "FIELD\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_FIELD + "overtime";

    public static final String MESSAGE_SUCCESS = "Successfully reset %1$s of all employees.";
    public static final String MESSAGE_NO_FIELD = "There needs to be a field to reset.";
    public static final String MESSAGE_WRONG_FIELD = "Field %1$s cannot be used to reset.";

    private final String field;

    /**
     * Constructs a Reset Command for the given field.
     */
    public ResetCommand(String field) {
        this.field = field;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(field);

        model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
        List<Employee> employeeList = model.getFilteredEmployeeList();

        switch(field.toLowerCase()) {
        case "":
            throw new CommandException(MESSAGE_NO_FIELD);
        case "overtime":
            for (Employee employee: employeeList) {
                model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
                Employee employeeWithDefaultOvertime = new Employee(employee.getName(), employee.getPosition(),
                        employee.getId(), employee.getPhone(), employee.getEmail(), employee.getSalary(),
                        employee.getDepartments(), new OvertimeHours(Employee.DEFAULT_OVERTIME_HOURS),
                        employee.getLeaveList());
                model.setEmployee(employee, employeeWithDefaultOvertime);
            }
            break;
        case "leaves":
            for (Employee employee: employeeList) {
                model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
                Employee employeeWithDefaultLeaveList = new Employee(employee.getName(), employee.getPosition(),
                        employee.getId(), employee.getPhone(), employee.getEmail(), employee.getSalary(),
                        employee.getDepartments(), employee.getOvertimeHours(), new LeaveList());
                model.setEmployee(employee, employeeWithDefaultLeaveList);
            }
            break;
        default:
            throw new CommandException(String.format(MESSAGE_WRONG_FIELD, field));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, field.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        //instanceof handles nulls
        if (!(other instanceof ResetCommand)) {
            return false;
        }

        ResetCommand e = (ResetCommand) other;
        return field.equals(e.field);
    }
}
