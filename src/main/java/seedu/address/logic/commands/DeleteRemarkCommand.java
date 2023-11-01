package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;

import java.util.List;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Id;
import seedu.address.model.remark.Remark;
import seedu.address.model.remark.RemarkList;

/**
 * Deletes a remark from an employee.
 */
public class DeleteRemarkCommand extends Command {
    public static final String COMMAND_WORD = "deleteremark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a remark for an employee from the employee book. "
            + "Parameters: "
            + PREFIX_ID + "EMPLOYEE ID "
            + PREFIX_REMARK + "REMARK\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "EID1234-5678 "
            + PREFIX_REMARK + "Good worker";

    public static final String MESSAGE_SUCCESS = "Remark deleted: %1$s";
    public static final String MISSING_REMARK = "No remark given!";
    public static final String MESSAGE_NONEXISTENT_REMARK = "This remark does not exist";
    private final Id targetId;
    private final Remark remark;

    /**
     * Creates a DeleteRemarkCommand to the specified {@code Employee}
     */
    public DeleteRemarkCommand(Id targetId, Remark remark) {
        this.targetId = targetId;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
        List<Employee> lastShownList = model.getFilteredEmployeeList();

        for (Employee employee : lastShownList) {
            if (employee.getId().equals(targetId)) {
                RemarkList remarkListToUpdate = employee.getRemarkList();
                RemarkList updatedList = deleteRemarkToList(remarkListToUpdate, remark);

                Employee employeeWithRemark = new Employee(employee.getName(), employee.getPosition(), employee.getId(),
                        employee.getPhone(), employee.getEmail(), employee.getSalary(), employee.getDepartments(),
                        employee.getOvertimeHours(), employee.getLeaveList(), updatedList);

                model.setEmployee(employee, employeeWithRemark);
                model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
                return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatRemarks(employeeWithRemark)));
            }
        }
        throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    /**
     * Deletes remark from the RemarkList.
     * @param currentList The RemarkList to be deleted from.
     * @param remark The Remark to be deleted.
     * @return RemarkList without deleted Remark.
     * @throws CommandException If the user input does not conform the expected format
     */
    private RemarkList deleteRemarkToList(RemarkList currentList, Remark remark) throws CommandException {
        if (!currentList.contains(remark)) {
            throw new CommandException(MESSAGE_NONEXISTENT_REMARK);
        } else {
            currentList.deleteRemark(remark);
        }
        return currentList;
    }
}
