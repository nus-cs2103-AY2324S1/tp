package seedu.staffsnap.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.staffsnap.logic.commands.DeleteCommand.MESSAGE_DELETE_APPLICANT_SUCCESS;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_INTERVIEW;

import java.util.List;

import seedu.staffsnap.commons.core.index.Index;
import seedu.staffsnap.commons.util.ToStringBuilder;
import seedu.staffsnap.logic.Messages;
import seedu.staffsnap.logic.commands.exceptions.CommandException;
import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.interview.Interview;

/**
 * Deletes an applicant's specified interview identified using it's displayed index from the applicant book.
 */
public class DeleteInterviewCommand extends Command {

    public static final String COMMAND_WORD = "deletei";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the applicant's specified interview identified by the index number "
            + "used in the displayed applicant list.\n"
            + "Parameters: INDEX (must be a positive integer) \n"
            + PREFIX_INTERVIEW + "INTERVIEW_INDEX\n"
            + "Example: " + COMMAND_WORD + " 1 i/2";

    public static final String MESSAGE_DELETE_INTERVIEW_SUCCESS = "Deleted interview from Applicant: %1$s";

    private final Index targetApplicantIndex;
    private final Index targetInterviewIndex;

    /**
     * Creates a DeleteInterviewCommand to delete the interview at the
     * specified {@code targetInterviewIndex} from the applicant at the
     * {@code targetApplicantIndex} in the displayed Applicant list.
     */
    public DeleteInterviewCommand(Index targetApplicantIndex, Index targetInterviewIndex) {
        this.targetApplicantIndex = targetApplicantIndex;
        this.targetInterviewIndex = targetInterviewIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (targetApplicantIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
        }

        Applicant applicantToDeleteFrom = lastShownList.get(targetApplicantIndex.getZeroBased());

        if (targetInterviewIndex.getZeroBased() >= applicantToDeleteFrom.getInterviews().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
        }

        Interview interviewToDelete = applicantToDeleteFrom.getInterviews().get(targetInterviewIndex.getZeroBased());

        applicantToDeleteFrom.deleteInterview(interviewToDelete);
        return new CommandResult(
                String.format(MESSAGE_DELETE_APPLICANT_SUCCESS, Messages.format(applicantToDeleteFrom)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteInterviewCommand)) {
            return false;
        }

        DeleteInterviewCommand otherDeleteCommand = (DeleteInterviewCommand) other;
        return targetApplicantIndex.equals(otherDeleteCommand.targetApplicantIndex)
                && targetInterviewIndex.equals(otherDeleteCommand.targetInterviewIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetApplicantIndex", targetApplicantIndex)
                .add("targetInterviewIndex", targetInterviewIndex)
                .toString();
    }
}
