package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OLD;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;

import java.time.LocalDate;
import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Id;
import seedu.address.model.employee.Leave;
import seedu.address.model.employee.LeaveList;

/**
 * Edits a leave date of an employee.
 */
public class EditLeaveCommand extends Command {
    public static final String COMMAND_WORD = "editleave";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits a leave date of an employee in the employee book. "
            + "Parameters: "
            + PREFIX_ID + "EMPLOYEE ID "
            + PREFIX_OLD + "OLD DATE "
            + PREFIX_NEW + "NEW DATE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "EID1234-5678 "
            + PREFIX_OLD + "2023-12-26 "
            + PREFIX_NEW + "2023-12-28";

    public static final String MESSAGE_SUCCESS = "Leave date has been successfully edited from %1$s to %2$s. "
            + "Here is the new leave list for:\n\n%3$s";
    public static final String MISSING_DATE = "No date given! "
            + "Leaves must have date in yyyy-MM-dd format.";
    public static final String MESSAGE_NON_EXISTENT_LEAVE = "This old leave currently does not exist.";
    public static final String MESSAGE_DUPLICATE_LEAVE = "This new leave overlaps with an existing leave.";
    private final Id targetId;
    private final LocalDate oldDate;
    private final LocalDate newDate;

    /**
     * Creates an EditLeaveCommand to edit the specified {@code Leave}
     */
    public EditLeaveCommand(Id targetId, LocalDate oldDate, LocalDate newDate) {
        requireAllNonNull(targetId, oldDate, newDate);

        this.targetId = targetId;
        this.oldDate = oldDate;
        this.newDate = newDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Employee> employeeList = model.getAddressBook().getEmployeeList();

        for (Employee employee : employeeList) {
            if (employee.getId().equals(targetId)) {
                LeaveList leaveListToUpdate = employee.getLeaveList();
                LeaveList updatedList = editLeaveInList(leaveListToUpdate, oldDate, newDate);

                Employee editedEmployee = new Employee(employee.getName(), employee.getPosition(), employee.getId(),
                        employee.getPhone(), employee.getEmail(), employee.getSalary(), employee.getDepartments(),
                        employee.getOvertimeHours(), updatedList, employee.getRemarkList());

                model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
                model.setEmployee(employee, editedEmployee);
                return new CommandResult(String.format(MESSAGE_SUCCESS, oldDate, newDate,
                        Messages.formatLeaves(editedEmployee)));
            }
        }
        throw new CommandException(MESSAGE_INVALID_EMPLOYEE_DISPLAYED_ID);
    }

    /**
     * Edits the Leave with the old date in the current list to the Leave with the new date.
     *
     * @param currentList The current LeaveList to be edited
     * @param oldDate The old date of the leave to be edited
     * @param newDate The new date of the leave to be edited to.
     * @return New LeaveList with edited leave date
     * @throws CommandException If user input does not conform to expected format
     */
    private LeaveList editLeaveInList(LeaveList currentList, LocalDate oldDate, LocalDate newDate)
            throws CommandException {
        LeaveList updatedList = new LeaveList();

        // check if old date exists in current leave list
        if (!currentList.contains(new Leave(oldDate))) {
            throw new CommandException(MESSAGE_NON_EXISTENT_LEAVE);
        }

        // check if new date doesn't already exist in current leave list
        if (currentList.contains(new Leave(newDate))) {
            throw new CommandException(MESSAGE_DUPLICATE_LEAVE);
        }

        for (int i = 0; i < currentList.getSize(); i++) {
            if (currentList.getLeave(i).equals(new Leave(oldDate))) {
                updatedList.addLeave(new Leave(newDate));
            } else {
                updatedList.addLeave(currentList.getLeave(i));
            }
        }

        return updatedList;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditLeaveCommand)) {
            return false;
        }

        EditLeaveCommand otherEditLeaveCommand = (EditLeaveCommand) other;
        return targetId.equals(otherEditLeaveCommand.targetId)
                && oldDate.equals(otherEditLeaveCommand.oldDate)
                && newDate.equals(otherEditLeaveCommand.newDate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("id", targetId)
                .add("oldDate", oldDate)
                .add("newDate", newDate)
                .toString();
    }
}
