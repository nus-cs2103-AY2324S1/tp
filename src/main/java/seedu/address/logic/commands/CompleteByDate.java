package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Completes and removes appointments from one or multiple person in address book by date input.
 * All persons with appointments date matching user input will be cleared.
 */
public class CompleteByDate extends CompleteCommand {
    private final LocalDate date;
    public CompleteByDate(LocalDate date) {
        this.date = date;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasAppointmentWithDate(date)) {
            throw new CommandException(MESSAGE_DATE_NO_APPOINTMENT);
        }

        model.clearAppointments(date);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(MESSAGE_COMPLETE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CompleteByDate)) {
            return false;
        }

        CompleteByDate otherCommand = (CompleteByDate) other;
        return this.date.equals(otherCommand.date);
    }
}
