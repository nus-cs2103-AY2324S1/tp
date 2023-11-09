package seedu.staffsnap.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.List;

import seedu.staffsnap.commons.core.index.Index;
import seedu.staffsnap.commons.util.ToStringBuilder;
import seedu.staffsnap.logic.Messages;
import seedu.staffsnap.logic.commands.exceptions.CommandException;
import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.interview.Interview;

/**
 * Adds an interview to an applicant.
 */
public class AddInterviewCommand extends Command {

    public static final String COMMAND_WORD = "addi";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an interview to an applicant identified "
            + "by the index number used in the displayed applicant list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TYPE + "TYPE" + " "
            + "[" + PREFIX_RATING + "RATING]\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_TYPE + "technical" + " "
            + PREFIX_RATING + "8.0";

    public static final String MESSAGE_SUCCESS = "New interview added to applicant: %1$s";
    public static final String MESSAGE_INTERVIEW_LIMIT_REACHED = "This applicant has reached the interview limit of 5";

    private final Index index;
    private Interview interviewToAdd;

    /**
     * Creates an AddInterviewCommand to add the specified {@code Interview}
     */
    public AddInterviewCommand(Index index, Interview interview) {
        requireNonNull(index);
        requireNonNull(interview);
        this.index = index;
        this.interviewToAdd = interview;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
        }

        Applicant applicantToEdit = lastShownList.get(index.getZeroBased());

        if (applicantToEdit.getInterviews().size() == 5) {
            throw new CommandException(MESSAGE_INTERVIEW_LIMIT_REACHED);
        }


        while (applicantToEdit.getInterviews().contains(interviewToAdd)
                || interviewToAdd.isContainedIn(applicantToEdit.getInterviews())) {
            interviewToAdd = interviewToAdd.incrementName();
        }

        applicantToEdit.addInterview(interviewToAdd);
        applicantToEdit.getScore().updateScoreAfterAdd(interviewToAdd);
        model.refreshApplicantList();

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(applicantToEdit)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddInterviewCommand)) {
            return false;
        }

        AddInterviewCommand otherAddCommand = (AddInterviewCommand) other;
        return interviewToAdd.equals(otherAddCommand.interviewToAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("interviewToAdd", interviewToAdd)
                .toString();
    }
}
