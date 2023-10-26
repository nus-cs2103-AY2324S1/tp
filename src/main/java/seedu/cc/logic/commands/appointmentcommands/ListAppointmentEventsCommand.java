package seedu.cc.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.Messages;
import seedu.cc.logic.commands.Command;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.Model;
import seedu.cc.model.patient.Patient;

/**
 * Lists all appointments of a patient.
 */
public class ListAppointmentEventsCommand extends Command {
    public static final String COMMAND_WORD = "list-appointments";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all appointments of a patient identified by"
            + "the index number used in the displayed patient list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_SUCCESS = "Listed all appointments for a patient";

    private final Index patientIndex;

    /**
     * Creates a ListAppointmentEventsCommand to list the appointments of the specified {@code Patient}.
     */
    public ListAppointmentEventsCommand(Index patientIndex) {
        this.patientIndex = patientIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (patientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patient = lastShownList.get(patientIndex.getZeroBased());
        model.listAppointmentsEventForPatient(patient);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
