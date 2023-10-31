package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.AnnualLeave;
import seedu.address.model.person.Person;

import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_ANNUAL_LEAVE_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_ANNUAL_LEAVE_ON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_ANNUAL_LEAVE_TO;

public class DeleteLeaveCommand extends Command {

    public static final String COMMAND_WORD = "deleteleave";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes leave from an employee.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + PREFIX_ADD_ANNUAL_LEAVE_ON + " DATE     "
            + "OR     "
            + PREFIX_ADD_ANNUAL_LEAVE_FROM + " DATE "
            + PREFIX_ADD_ANNUAL_LEAVE_TO + " DATE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ADD_ANNUAL_LEAVE_ON + " 01/01/2023     "
            + "OR     "
            + PREFIX_ADD_ANNUAL_LEAVE_FROM + " 01/01/2023 "
            + PREFIX_ADD_ANNUAL_LEAVE_TO + " 05/01/2023 \n";

    public static final String MESSAGE_SUCCESS = "Leave has been deleted for employee: %1$s!\n";
    private final Index index;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Creates a DeleteLeaveCommand to delete leave from specified employee at {@code index}
     * @param index of the employee in the filtered employee list to edit
     * @param startDate The date of leave to be deleted from the specified employee at {@code index}
     */
    public DeleteLeaveCommand(Index index, LocalDate startDate) {
        requireNonNull(index);
        this.index = index;
        this.startDate = startDate;
        this.endDate = null;
    }

    /**
     * Creates an DeleteLeaveCommand to delete a range of leave from the employee at specified {@code index}
     * from the startDate to the endDate.
     * @param index of the employee in the filtered employee list to edit
     * @param startDate The starting date of leave to be deleted from the specified employee at {@code index}
     * @param endDate The ending date of leave to be deleted from to the specified employee at {@code index}
     */
    public DeleteLeaveCommand(Index index, LocalDate startDate, LocalDate endDate) {
        requireNonNull(index);
        this.index = index;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person employeeToDeleteLeave = lastShownList.get(index.getZeroBased());
        if (startDate.isBefore(LocalDate.now())) {
            throw new CommandException(String.format(AnnualLeave.MESSAGE_DELETE_EXPIRED_LEAVE));
        }
        if (endDate == null) {
            if (!employeeToDeleteLeave.getAnnualLeave().containsAllLeave(startDate, startDate)) {
                throw new CommandException(AnnualLeave.MESSAGE_DELETE_INVALID_LEAVE);
            }
            employeeToDeleteLeave.getAnnualLeave().deleteLeave(startDate);
        } else {
            if (!endDate.isAfter(startDate)) {
                throw new CommandException(AnnualLeave.MESSAGE_INVALID_LEAVE);
            } else {
                if (!employeeToDeleteLeave.getAnnualLeave().containsAllLeave(startDate, endDate)) {
                    throw new CommandException(AnnualLeave.MESSAGE_DELETE_INVALID_LEAVE);
                }
                employeeToDeleteLeave.getAnnualLeave().deleteLeave(startDate, endDate);
            }
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS + this.getLeaveStatusMessage(employeeToDeleteLeave),
                Messages.format(employeeToDeleteLeave)), false, false, true, false, false, false);
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
        return this.equals(otherDeleteLeaveCommand);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toDeleteLeave", index)
                .toString();
    }

    /**
     * Returns a string of message to indicate the number of days of leave left for an employee for the current year.
     * @param employee The employee that has their leave deleted.
     * @return The message that shows remaining days of leave for an employee for the current year.
     */
    public String getLeaveStatusMessage(Person employee) {
        requireNonNull(employee);
        return "Number of leaves left for this year: " + employee.getAnnualLeave().numOfLeaveLeftForCurrYear()
                + " / "
                + employee.getAnnualLeave().value + "\nNumber of leaves left for next year: "
                + employee.getAnnualLeave().numOfLeaveLeftForNextYear()
                + " / "
                + employee.getAnnualLeave().value;
    }
}
