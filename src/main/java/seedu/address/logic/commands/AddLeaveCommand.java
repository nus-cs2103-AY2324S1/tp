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
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANNUAL_LEAVE;

public class AddLeaveCommand extends Command {
    public static final String COMMAND_WORD = "addleave";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds leave to an employee.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + PREFIX_ADD_ANNUAL_LEAVE_ON + "DATE "
            + "OR "
            + PREFIX_ADD_ANNUAL_LEAVE_FROM + "DATE "
            + PREFIX_ADD_ANNUAL_LEAVE_TO + "DATE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ADD_ANNUAL_LEAVE_ON + "01/01/2023 "
            + "OR "
            + PREFIX_ADD_ANNUAL_LEAVE_FROM + "01/01/2023 "
            + PREFIX_ADD_ANNUAL_LEAVE_TO + "05/01/2023 \n";

    public static final String MESSAGE_SUCCESS = "Leave has been added for employee: %1$s!";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Index index;

    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddLeaveCommand(Index index, LocalDate startDate) {
        requireNonNull(index);
        this.index = index;
        this.startDate = startDate;
        this.endDate = null;
    }

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
        if (endDate == null) {
            if (employeeToAddLeave.getAnnualLeave().isValidLeave(startDate, startDate)) {
                employeeToAddLeave.getAnnualLeave().addLeave(startDate);
            } else {
                throw new CommandException(AnnualLeave.MESSAGE_LEAVE_CONSTRAINTS);
            }
        } else {
            if (employeeToAddLeave.getAnnualLeave().isValidLeave(startDate, endDate)) {
                employeeToAddLeave.getAnnualLeave().addLeave(startDate, endDate);
            } else {
                throw new CommandException(AnnualLeave.MESSAGE_LEAVE_CONSTRAINTS);
            }
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(employeeToAddLeave)));
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
}
