package seedu.address.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_PRIORITY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDescription;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.person.Person;
import seedu.address.model.tag.PriorityTag;

/**
 * Changes the priority of an existing appointment
 */
public class TriageCommand extends Command {
    public static final String COMMAND_WORD = "triage";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes the priority of the appointment identified "
            + "by the index number used in the displayed appointment list.\n"
            + "Parameters: INDEX "
            + PREFIX_APPOINTMENT_PRIORITY + "PRIORITY\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_APPOINTMENT_PRIORITY + "high";

    public static final String MESSAGE_SUCCESS = "Patient appointment priority changed: %1$s";

    private final Index index;

    private final PriorityTag priorityTag;

    /**
     * Creates a TriageCommand to change the priority
     * for the specified {@code Appointment}
     * @param index of the appointment in the filtered appointment list to edit
     * @param priorityTag the new priority
     */
    public TriageCommand(Index index, PriorityTag priorityTag) {
        requireNonNull(index);
        this.index = index;
        this.priorityTag = priorityTag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToTriage = lastShownList.get(index.getZeroBased());
        Appointment triagedAppointment = createChangedPriorityAppointment(appointmentToTriage, priorityTag);

        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);

        model.setAppointment(appointmentToTriage, triagedAppointment);
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, Messages.format(triagedAppointment)), false, false, false, true);
    }

    private static Appointment createChangedPriorityAppointment(Appointment appointmentToTriage,
                                                                PriorityTag priorityTag) {
        assert appointmentToTriage != null;

        Person patient = appointmentToTriage.getPerson();
        AppointmentDescription description = appointmentToTriage.getAppointmentDescription();
        AppointmentTime appointmentTime = appointmentToTriage.getAppointmentTime();

        return new Appointment(patient, appointmentTime, description, priorityTag);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TriageCommand)) {
            return false;
        }

        TriageCommand e = (TriageCommand) other;
        return index.equals(e.index);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", index)
                .add("priorityTag", priorityTag)
                .toString();
    }
}
