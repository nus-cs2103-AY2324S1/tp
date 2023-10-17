package seedu.staffsnap.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.staffsnap.model.Model.PREDICATE_HIDE_ALL_APPLICANTS;
import static seedu.staffsnap.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;

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
            + "by the index number used in the displayed applicant list. "
            + "Parameters: INDEX (must be a positive integer)\n"
            + PREFIX_TYPE + "TYPE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TYPE + "Technical";

    public static final String MESSAGE_SUCCESS = "New interview added to applicant: %1$s";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "This interview already exists for this applicant";

    private final Index index;
    private final Interview interviewToAdd;

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

        if (applicantToEdit.getInterviews().contains(interviewToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERVIEW);
        }

        applicantToEdit.addInterview(interviewToAdd);
        /*
         This is a workaround to javaFX not updating the list shown to the user unless the predicate is changed
         Possible fix in the future is to read the current predicate, then store it to be reused
         Might be an issue when implementing filter()
         TODO:
         store current predicate in temp variable
         use stored predicate when refreshing the filtered list
        */
        model.updateFilteredApplicantList(PREDICATE_HIDE_ALL_APPLICANTS);
        model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
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
