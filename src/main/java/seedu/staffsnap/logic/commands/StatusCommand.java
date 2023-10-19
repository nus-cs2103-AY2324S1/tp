package seedu.staffsnap.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.staffsnap.commons.core.index.Index;
import seedu.staffsnap.logic.Messages;
import seedu.staffsnap.logic.commands.exceptions.CommandException;
import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.applicant.Status;



/**
 * Edits the details of an existing applicant in the applicant book.
 */
public class StatusCommand extends Command {

    public static final String COMMAND_WORD = "status";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the status of the applicant identified "
            + "by the index number used in the displayed applicant list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) " + "[u(ndecided)/o(ffered)/r(ejected)].";

    public static final String MESSAGE_EDIT_STATUS_SUCCESS = "Edited Applicant Status: %1$s";
    public static final String MESSAGE_NO_STATUS = "Missing Status, please follow the following parameters."
            + "Parameters: INDEX (must be a positive integer) "
            + "[u(ndecided)/o(ffered)/r(ejected)].";

    private final Index index;
    private final Status status;

    /**
     * @param index  of the applicant in the filtered applicant list to edit
     * @param status The status to assign to the applicant
     */
    public StatusCommand(Index index, Status status) {
        requireNonNull(index);
        requireNonNull(status);

        this.index = index;
        this.status = status;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
        }

        Applicant applicantToEdit = lastShownList.get(index.getZeroBased());
        applicantToEdit.setStatus(this.status);
        System.out.println(applicantToEdit.getStatus());

        return new CommandResult(String.format(MESSAGE_EDIT_STATUS_SUCCESS, Messages.format(applicantToEdit)));
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StatusCommand)) {
            return false;
        }

        StatusCommand otherStatusCommand = (StatusCommand) other;
        return index.equals(otherStatusCommand.index) && status.equals(otherStatusCommand.status);
    }

    @Override
    public String toString() {
        return "StatusCommand{" + "index=" + index + ", status=" + status + '}';
    }
}
