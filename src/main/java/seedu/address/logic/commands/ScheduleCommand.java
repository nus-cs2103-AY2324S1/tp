package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * ScheduleCommand represents a command to schedule a new appointment.
 */
public class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Schedules a new appointment. "
            + "Parameters: "
            + PREFIX_NAME + "STUDENT "
            + PREFIX_DATE + "DATE "
            + PREFIX_START_TIME + "STARTTIME "
            + PREFIX_END_TIME + "ENDTIME "
            + PREFIX_DESCRIPTION + "DESCRIPTION\n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_DATE + "2023-12-31 "
            + PREFIX_START_TIME + "16:30 "
            + PREFIX_END_TIME + "17:30 "
            + PREFIX_DESCRIPTION + "First Session";

    public static final String MESSAGE_SUCCESS = "New appointment scheduled: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment clashes with an existing appointment";

    private final Appointment toAdd;

    /**
     * Creates a new ScheduleCommand to schedule the specified appointment.
     *
     * @param appointment The appointment to be scheduled. Must not be null.
     */
    public ScheduleCommand(Appointment appointment) {
        requireNonNull(appointment);
        toAdd = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasAppointment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.addAppointment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ScheduleCommand)) {
            return false;
        }

        ScheduleCommand otherScheduleCommand = (ScheduleCommand) other;
        return toAdd.equals(otherScheduleCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
