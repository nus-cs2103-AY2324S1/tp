package seedu.cc.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_PATIENT_INDEX;

import java.util.List;

import seedu.cc.commons.core.index.Index;
import seedu.cc.commons.util.ToStringBuilder;
import seedu.cc.logic.Messages;
import seedu.cc.logic.commands.Command;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.Model;
import seedu.cc.model.appointment.AppointmentEvent;
import seedu.cc.model.patient.Patient;

/**
 * Deletes an appointment event identified using it's displayed index from the address book.
 */
public class DeleteAppointmentEventCommand extends Command {

    public static final String COMMAND_WORD = "delete-appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the appointment identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PATIENT_INDEX + "PATIENT INDEX ";

    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Deleted Appointment:\n %1$s";

    private final Index eventIndex;

    private final Index patientIndex;

    /**
     * Deletes the appointment event at {@code eventIndex} from the patient at {@code patientIndex}.
     * @param eventIndex of the appointment event in the filtered appointment event list to delete
     * @param patientIndex of the patient in the filtered patient list to delete the appointment event from
     */
    public DeleteAppointmentEventCommand(Index eventIndex, Index patientIndex) {
        this.eventIndex = eventIndex;
        this.patientIndex = patientIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastPatientShownList = model.getFilteredPatientList();

        if (patientIndex.getZeroBased() >= lastPatientShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient personToDeleteAppointment = lastPatientShownList.get(patientIndex.getZeroBased());

        List<AppointmentEvent> lastShownList = model.getFilteredAppointmentList();

        if (eventIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_EVENT_DISPLAYED_INDEX);
        }

        AppointmentEvent appointmentEventToDelete = lastShownList.get(eventIndex.getZeroBased());

        if (!personToDeleteAppointment.hasAppointmentEvent(appointmentEventToDelete)) {
            throw new CommandException("This Appointment event does not exist for this patient");
        }

        model.deleteAppointmentEventForPatient(personToDeleteAppointment, appointmentEventToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_APPOINTMENT_SUCCESS,
                Messages.format(appointmentEventToDelete, personToDeleteAppointment)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteAppointmentEventCommand)) {
            return false;
        }

        DeleteAppointmentEventCommand otherDeleteCommand = (DeleteAppointmentEventCommand) other;
        return eventIndex.equals(otherDeleteCommand.eventIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("eventIndex", eventIndex)
                .add("patientIndex", patientIndex)
                .toString();
    }
}
