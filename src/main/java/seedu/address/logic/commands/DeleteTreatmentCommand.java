package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.treatment.Treatment;

/**
 * Deletes a treatment identified using it's displayed index from the address book.
 */
public class DeleteTreatmentCommand extends Command {

    public static final String COMMAND_WORD = "delete-treatment";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the treatment by its name.\n"
        + "Parameters: Treatment Name (it must be a exact match)\n"
        + "Example: " + COMMAND_WORD + " Braces";

    private final String targetTreatment;

    public DeleteTreatmentCommand(String target) {
        this.targetTreatment = target;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            Treatment treatmentToDelete = model.getTreatmentByName(targetTreatment);

            if (treatmentToDelete == null) {
                throw new CommandException(String.format(Messages.MESSAGE_NO_SUCH_TREATMENT,
                    targetTreatment));
            }

            model.deleteTreatment(treatmentToDelete);
            return new CommandResult(
                String.format(Messages.MESSAGE_DELETE_TREATMENT_SUCCESS,
                    Messages.format(treatmentToDelete)));

        } catch (Exception e) {
            throw new CommandException(
                "An error occurred while deleting the treatment: " + e.getMessage()
            );
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteTreatmentCommand)) {
            return false;
        }

        DeleteTreatmentCommand otherDeleteTreatmentCommand = (DeleteTreatmentCommand) other;
        return Objects.equals(targetTreatment, otherDeleteTreatmentCommand.targetTreatment);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("targetIndex", targetTreatment)
            .toString();
    }
}
