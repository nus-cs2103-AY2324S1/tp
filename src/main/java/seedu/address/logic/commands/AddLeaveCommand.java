package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_ANNUAL_LEAVE_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_ANNUAL_LEAVE_ON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_ANNUAL_LEAVE_TO;

import java.time.LocalDate;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.AnnualLeave;
import seedu.address.model.person.Person;
/**
 * Adds leave to an existing employee in the list.
 */
public class AddLeaveCommand extends Command {

    public static final String COMMAND_WORD = "addleave";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds leave to an employee.\n"
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

    public static final String MESSAGE_SUCCESS = "Leave has been added for employee: %1$s!\n";
    private final Index index;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Creates an AddLeaveCommand to add leave to the specified employee at {@code index}
     * @param index of the employee in the filtered employee list to edit
     * @param startDate The date of leave to add to the specified employee at {@code index}
     */
    public AddLeaveCommand(Index index, LocalDate startDate) {
        requireNonNull(index);
        this.index = index;
        this.startDate = startDate;
        this.endDate = null;
    }

    /**
     * Creates an AddLeaveCommand to add a range of leave to the employee at specified {@code index}
     * from the startDate to the endDate.
     * @param index of the employee in the filtered employee list to edit
     * @param startDate The starting date of leave to add to the specified employee at {@code index}
     * @param endDate The ending date of leave to add to the specified employee at {@code index}
     */
    public AddLeaveCommand(Index index, LocalDate startDate, LocalDate endDate) {
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
        Person employeeToAddLeave = lastShownList.get(index.getZeroBased());
        if (startDate.isBefore(LocalDate.now())) {
            throw new CommandException(String.format(AnnualLeave.MESSAGE_EXPIRED_LEAVE));
        }
        if (employeeToAddLeave.getAnnualLeave().containsDuplicateLeave(startDate, endDate)) {
            throw new CommandException(AnnualLeave.MESSAGE_DUPLICATE_LEAVE);
        }
        if (endDate == null) {
            if (employeeToAddLeave.getAnnualLeave().isValidAddLeave(startDate, startDate)) {
                employeeToAddLeave.getAnnualLeave().addLeave(startDate);
            } else {
                throw new CommandException(AnnualLeave.MESSAGE_LEAVE_CONSTRAINTS);
            }
        } else {
            if (!endDate.isAfter(startDate)) {
                throw new CommandException(AnnualLeave.MESSAGE_INVALID_LEAVE);
            }
            if (employeeToAddLeave.getAnnualLeave().isValidAddLeave(startDate, endDate)) {
                employeeToAddLeave.getAnnualLeave().addLeave(startDate, endDate);
            } else {
                throw new CommandException(AnnualLeave.MESSAGE_LEAVE_CONSTRAINTS);
            }
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS + this.getLeaveStatusMessage(employeeToAddLeave),
                Messages.format(employeeToAddLeave)), true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddLeaveCommand)) {
            return false;
        }

        AddLeaveCommand otherAddLeaveCommand = (AddLeaveCommand) other;
        return this.equals(otherAddLeaveCommand);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAddLeave", index)
                .toString();
    }

    /**
     * Returns a string of message to indicate the number of days of leave left for an employee for the current year.
     * @param employee The employee that has their leave added.
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
