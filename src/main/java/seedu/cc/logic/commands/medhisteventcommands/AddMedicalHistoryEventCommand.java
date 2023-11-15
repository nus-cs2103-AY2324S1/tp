package seedu.cc.logic.commands.medhisteventcommands;

import static java.util.Objects.requireNonNull;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_MEDICAL_CONDITION;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_TREATMENT;

import java.util.List;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.Messages;
import seedu.cc.logic.commands.Command;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.SwitchCommand;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.Model;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.patient.Patient;

/**
 * Adds a medical history event to the patient's medical history.
 */
public class AddMedicalHistoryEventCommand extends Command {

    public static final String COMMAND_WORD = "add-medical-history";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a medical history event to the patient's "
            + "medical history.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_DATE + "YYYY-MM-DD "
            + PREFIX_MEDICAL_CONDITION + "MEDICAL CONDITION "
            + PREFIX_TREATMENT + "TREATMENT "
            + "\nExample: " + COMMAND_WORD + " 1 "
            + PREFIX_DATE + "2023-10-01 "
            + PREFIX_MEDICAL_CONDITION + "Diabetes "
            + PREFIX_TREATMENT + "Insulin";

    public static final String MESSAGE_SUCCESS = "New medical history event added: \n%1$s";
    public static final String MESSAGE_DATE_IN_FUTURE = "Medical History Date should not be in the future";

    private final MedicalHistoryEvent eventToAdd;
    private final Index patientIndex;
    private final SwitchCommand switchCommand;

    /**
     * Creates an AddMedicalHistoryEventCommand to add the specified {@code MedicalHistoryEvent}.
     */
    public AddMedicalHistoryEventCommand(MedicalHistoryEvent eventToAdd, Index patientIndex) {
        requireNonNull(eventToAdd);
        requireNonNull(patientIndex);
        this.eventToAdd = eventToAdd;
        this.patientIndex = patientIndex;
        this.switchCommand = new SwitchCommand(Index.fromZeroBased(1));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (patientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToAddMedicalHistoryEvent = lastShownList.get(patientIndex.getZeroBased());
        model.addMedicalHistoryEvent(patientToAddMedicalHistoryEvent, eventToAdd);
        model.listMedicalHistoryEvents(patientToAddMedicalHistoryEvent);
        switchCommand.execute(model);

        return new CommandResult(String.format(MESSAGE_SUCCESS,
                Messages.format(eventToAdd, patientToAddMedicalHistoryEvent)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddMedicalHistoryEventCommand)) {
            return false;
        }

        // state check
        AddMedicalHistoryEventCommand e = (AddMedicalHistoryEventCommand) other;
        return eventToAdd.equals(e.eventToAdd)
                && patientIndex.equals(e.patientIndex);
    }
}
