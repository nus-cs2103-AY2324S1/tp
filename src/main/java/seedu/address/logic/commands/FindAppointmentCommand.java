package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * Finds and lists all appointments in the address book whose attributes match the specified predicate.
 * Keyword matching is case-insensitive.
 * Extends the abstract class {@link Command}.
 */
public class FindAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "find-appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all appointments that involve the "
            + "the specified case-sensitive NRIC and displays them as a list with index numbers.\n"
            + "Parameters: INDEX ...\n"
            + "Example: " + COMMAND_WORD + " T1234567Z";

    private final Predicate<Appointment> predicate;
    /**
     * Creates a new FindAppointmentCommand with the given predicate.
     *
     * @param predicate The predicate used to filter appointments.
     */
    public FindAppointmentCommand(Predicate<Appointment> predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(predicate);
        List<Appointment> searchList = new ArrayList<>();
        searchList.addAll(model.getFilteredAppointmentList());
        return new CommandResult(
                String.format(Messages.MESSAGE_APPOINTMENTS_FOUND_OVERVIEW, searchList.size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindAppointmentCommand)) {
            return false;
        }

        FindAppointmentCommand otherFindAppointmentCommand = (FindAppointmentCommand) other;
        return predicate.equals(otherFindAppointmentCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
