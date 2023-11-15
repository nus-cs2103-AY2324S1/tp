package seedu.cc.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_APPT_DATE;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_APPT_TIME;

import java.util.List;

import seedu.cc.commons.core.index.Index;
import seedu.cc.commons.util.ToStringBuilder;
import seedu.cc.logic.Messages;
import seedu.cc.logic.commands.Command;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.SwitchCommand;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.Model;
import seedu.cc.model.appointment.AppointmentEvent;
import seedu.cc.model.patient.Patient;

/**
 * Edits the details of an existing person in the clinic book.
 */
public class AddAppointmentEventCommand extends Command {

    public static final String COMMAND_WORD = "add-appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointmentEvent to the patient identified "
            + "by the index number used in the displayed patient list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_APPT_DATE + "DATE "
            + PREFIX_APPT_TIME + "TIME\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_APPT_DATE + "2023-10-01 "
            + PREFIX_APPT_TIME + "14:00";

    public static final String MESSAGE_ADD_APPOINTMENT_SUCCESS = "Successfully added an appointment event: \n%1$s";
    public static final String MESSAGE_INVALID_INPUT = "Invalid input. Please enter a valid patient "
        + "index, date, or time.";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in the clinic book.";

    private final Index index;
    private final AppointmentEvent appointmentEvent;
    private final SwitchCommand switchCommand;

    /**
     * Adds an appointment event to the patient at {@code index}.
     * @param index of the patient in the filtered patient list to edit
     * @param appointmentEvent details to edit the patient with
     */
    public AddAppointmentEventCommand(Index index, AppointmentEvent appointmentEvent) {
        requireNonNull(index);
        requireNonNull(appointmentEvent);
        this.index = index;
        this.appointmentEvent = appointmentEvent;
        this.switchCommand = new SwitchCommand(Index.fromZeroBased(2));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToAddAppt = lastShownList.get(index.getZeroBased());
        model.addAppointmentEventToPatient(patientToAddAppt, appointmentEvent);
        model.listAppointmentsEventForPatient(patientToAddAppt);
        switchCommand.execute(model);

        return new CommandResult(String.format(MESSAGE_ADD_APPOINTMENT_SUCCESS,
                Messages.format(appointmentEvent, patientToAddAppt)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAppointmentEventCommand)) {
            return false;
        }

        AddAppointmentEventCommand otherEditCommand = (AddAppointmentEventCommand) other;
        return index.equals(otherEditCommand.index)
                && appointmentEvent.equals(otherEditCommand.appointmentEvent);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("appointmentEvent", appointmentEvent)
                .toString();
    }
}
