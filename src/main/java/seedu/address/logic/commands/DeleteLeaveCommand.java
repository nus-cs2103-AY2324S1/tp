package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
 * Deletes a leave period on an employee
 */
public class DeleteLeaveCommand extends Command {
    public static final String COMMAND_WORD = "deleteleave";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a leave period of an employee to the employee book. "
            + "Parameters: "
            + PREFIX_ID + "EMPLOYEE ID "
            + PREFIX_FROM + "START DATE "
            + PREFIX_TO + "END DATE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "EID1234-5678 "
            + PREFIX_FROM + "2023-12-26 "
            + PREFIX_TO + "2023-12-28";

    public static final String MESSAGE_SUCCESS = "Leave period deleted: %1$s";
    public static final String MISSING_DATE = "No date given! "
            + "Leaves must have date in yyyy-MM-dd format.";
    public static final String MESSAGE_INVALID_DATE_ORDER = "START DATE should not be after END DATE!";
    public static final String MESSAGE_NO_LEAVES_FOUND = "There are no leaves to be deleted in the specified period";
    private final Id targetId;
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Creates a DeleteLeaveCommand to delete the specified {@code Leave}
     */
    public DeleteLeaveCommand(Id targetId, LocalDate from, LocalDate to) {
        requireAllNonNull(from, to);

        this.targetId = targetId;
        this.from = from;
        this.to = to;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Employee> employeeList = model.getAddressBook().getEmployeeList();

        for (Employee employee : employeeList) {
            if (employee.getId().equals(targetId)) {
                LeaveList leaveListToUpdate = employee.getLeaveList();
                LeaveList updatedList = deleteLeavesFromList(leaveListToUpdate, from, to);

                Employee employeeWithLeave = new Employee(employee.getName(), employee.getPosition(), employee.getId(),
                        employee.getPhone(), employee.getEmail(), employee.getSalary(), employee.getDepartments(),
                        employee.getOvertimeHours(), updatedList, employee.getRemarkList());

                model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
                model.setEmployee(employee, employeeWithLeave);
                return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatLeaves(employeeWithLeave)));
            }
        }
        throw new CommandException(MESSAGE_INVALID_EMPLOYEE_DISPLAYED_ID);
    }

    private ArrayList<Leave> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        ArrayList<Leave> dates = new ArrayList<>();
        long numOfDays = ChronoUnit.DAYS.between(startDate, endDate);
        for (int i = 0; i <= numOfDays; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            dates.add(new Leave(currentDate));
        }
        return dates;
    }

    /**
     * Adds all dates from the start to end date to the LeaveList as Leaves.
     * @param currentList The LeaveList to add to
     * @param startDate The start date of the leave period
     * @param endDate The end date of the leave period
     * @return LeaveList with leaves added
     * @throws CommandException if the user input does not conform the expected format
     */
    private LeaveList deleteLeavesFromList(LeaveList currentList, LocalDate startDate, LocalDate endDate)
            throws CommandException {
        int count = 0;
        ArrayList<Leave> deleteDatesPeriod = getDatesBetween(startDate, endDate);
        for (Leave leaveDate : deleteDatesPeriod) {
            if (currentList.deleteLeave(leaveDate)) {
                count = count + 1;
            }
        }
        if (count == 0) {
            throw new CommandException(MESSAGE_NO_LEAVES_FOUND);
        }
        return currentList;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteLeaveCommand)) {
            return false;
        }

        DeleteLeaveCommand otherDeleteLeaveCommand = (DeleteLeaveCommand) other;
        return targetId.equals(otherDeleteLeaveCommand.targetId)
                && from.equals(otherDeleteLeaveCommand.from)
                && to.equals(otherDeleteLeaveCommand.to);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("id", targetId)
                .toString();
    }
}
