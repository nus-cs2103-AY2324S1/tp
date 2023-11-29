package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.patients.Patient;

/**
 * Deletes a patient identified using it's displayed index from the address book.
 */
public class DeletePatientCommand extends Command {

    public static final String COMMAND_WORD = "delete-patient";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the patient identified by the index number used in the displayed patient list.\n"
        + "Parameters: ID (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    private final long targetId;

    public DeletePatientCommand(long targetId) {
        this.targetId = targetId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            Patient patientToDelete = model.getPatientById(targetId);

            if (patientToDelete == null) {
                throw new CommandException(String.format(Messages.MESSAGE_NO_SUCH_PATIENT, targetId));
            }

            model.deletePatient(patientToDelete);
            return new CommandResult(
                    String.format(Messages.MESSAGE_DELETE_PATIENT_SUCCESS, Messages.format(patientToDelete)));

        } catch (Exception e) {
            throw new CommandException("An error occurred while deleting the patient: " + e.getMessage()
                + " Please use list-patient or search-patient to get intended Patient on the screen first.");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeletePatientCommand)) {
            return false;
        }

        DeletePatientCommand otherDeletePatientCommand = (DeletePatientCommand) other;
        return Objects.equals(targetId, otherDeletePatientCommand.targetId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("targetIndex", targetId)
            .toString();
    }
}
