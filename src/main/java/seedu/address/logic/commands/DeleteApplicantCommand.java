/* package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Applicant;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Deletes an applicant identified using it's displayed index from the address book.

public class DeleteApplicantCommand extends Command {

    public static final String COMMAND_WORD = "deleteApplicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the applicant identified by the index number used in the applicant list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_APPLICANT_SUCCESS = "Deleted Applicant: %1$s";

    private final Index targetIndex;

    public DeleteApplicantCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
        }

        Applicant applicantToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteApplicant(applicantToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_APPLICANT_SUCCESS, Messages.format(applicantToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteApplicantCommand)) {
            return false;
        }

        DeleteApplicantCommand otherDeleteCommand = (DeleteApplicantCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
} */
