package seedu.cc.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;
import static seedu.cc.logic.commands.appointmentcommands.EditAppointmentEventCommand.createEditedAppointmentEvent;
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
 * Edits the details of an existing person in the address book.
 */
public class DeletePrescriptionCommand extends Command {

    public static final String COMMAND_WORD = "delete-prescription";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the prescription to the appointment "
            + "by the index number used in the displayed appointment list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_PATIENT_INDEX + "PATIENT INDEX \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PATIENT_INDEX + "1 ";

    public static final String MESSAGE_ADD_APPOINTMENT_SUCCESS = "Successfully deleted a prescription: \n%1$s";
    public static final String MESSAGE_INVALID_INPUT = "Invalid input. Please enter a valid medicine.";
    private final Index patientIndex;
    private final Index eventIndex;
    private final EditAppointmentEventCommand.EditAppointmentEventDescriptor editAppointmentEventDescriptor;

    /**
     * Adds an appointment event to the patient at {@code index}.
     * @param eventIndex of the appointment event in the filtered appointment event list to edit
     * @param patientIndex of the patient in the filtered patient list to edit
     * @param editAppointmentEventDescriptor details to edit the appointment with
     */
    public DeletePrescriptionCommand(Index eventIndex, Index patientIndex,
                                     EditAppointmentEventCommand
                                             .EditAppointmentEventDescriptor editAppointmentEventDescriptor) {
        requireNonNull(eventIndex);
        requireNonNull(eventIndex);
        this.patientIndex = patientIndex;
        this.eventIndex = eventIndex;
        this.editAppointmentEventDescriptor = editAppointmentEventDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();
        List<AppointmentEvent> lastShownAppointmentList = model.getFilteredAppointmentList();

        if (patientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        if (eventIndex.getZeroBased() >= lastShownAppointmentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_EVENT_DISPLAYED_INDEX);
        }

        if (patientIndex.getZeroBased() >= lastShownAppointmentList.size()) {
            throw new CommandException(Messages.MESSAGE_PATIENT_NOT_DISPLAYED_INDEX_ERROR);
        }

        Patient patientToAddPrescription = lastShownList.get(patientIndex.getZeroBased());
        AppointmentEvent appointmentEvent = lastShownAppointmentList.get(eventIndex.getZeroBased());

        AppointmentEvent editedEvent = createEditedAppointmentEvent(appointmentEvent, editAppointmentEventDescriptor);


        model.setAppointmentEventForPatient(patientToAddPrescription, appointmentEvent, editedEvent);

        return new CommandResult(String.format(MESSAGE_ADD_APPOINTMENT_SUCCESS,
                Messages.format(appointmentEvent, patientToAddPrescription)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeletePrescriptionCommand)) {
            return false;
        }

        DeletePrescriptionCommand otherAddPrescriptionCommand = (DeletePrescriptionCommand) other;
        return this.patientIndex.equals(otherAddPrescriptionCommand.patientIndex)
                && this.eventIndex.equals(otherAddPrescriptionCommand.eventIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("eventIndex", eventIndex)
                .add("patientIndex", patientIndex)
                .toString();
    }
}
