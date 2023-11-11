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
        isValidField(field.toLowerCase());

        List<Employee> employeeList = model.getAddressBook().getEmployeeList();

        for (Employee employee : employeeList) {
            if (field.equalsIgnoreCase("overtime")) {
                resetOvertime(model, employee);
            } else if (field.equalsIgnoreCase("leaves")) {
                resetLeaves(model, employee);
            }
        }

        model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
        return new CommandResult(String.format(MESSAGE_SUCCESS, field.toLowerCase()));
    }

    /**
     * Checks whether field is valid.
     *
     * @param field Field based on user's input
     * @throws CommandException If user's input is not 'overtime' or 'leaves'
     */
    public void isValidField(String field) throws CommandException {
        requireAllNonNull(field);

        if (field.isEmpty()) {
            throw new CommandException(MESSAGE_NO_FIELD);
        }

        if (!field.equals("overtime") && !field.equals("leaves")) {
            throw new CommandException(String.format(MESSAGE_WRONG_FIELD, field));
        }
    }

    /**
     * Resets the overtime hours of given employee in given model.
     *
     * @param model The model containing the employee
     * @param employee The employee to be edited
     */
    public void resetOvertime(Model model, Employee employee) {
        requireAllNonNull(model, employee);
        Employee employeeWithDefaultOvertime = new Employee(employee.getName(), employee.getPosition(),
                employee.getId(), employee.getPhone(), employee.getEmail(), employee.getSalary(),
                employee.getDepartments(), new OvertimeHours(Employee.DEFAULT_OVERTIME_HOURS), employee.getLeaveList(),
                employee.getRemarkList());
        model.setEmployee(employee, employeeWithDefaultOvertime);
    }

    /**
     * Resets the leave list of given employee in given model.
     *
     * @param model The model containing the employee
     * @param employee The employee to be edited
     */
    public void resetLeaves(Model model, Employee employee) {
        requireAllNonNull(model, employee);
        Employee employeeWithDefaultLeaveList = new Employee(employee.getName(), employee.getPosition(),
                employee.getId(), employee.getPhone(), employee.getEmail(), employee.getSalary(),
                employee.getDepartments(), employee.getOvertimeHours(), new LeaveList(),
                employee.getRemarkList());
        model.setEmployee(employee, employeeWithDefaultLeaveList);
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
