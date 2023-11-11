package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_PAST_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Time;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.interview.Interview;

/**
 * Command to handle adding interviews to the address book.
 * Adapted from AB3's AddCommand class
 */
public class AddInterviewCommand extends Command {

    public static final String COMMAND_WORD = "add-i";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an interview to the address book.\n"
            + "Parameters: "
            + PREFIX_APPLICANT + "APPLICANT_ID "
            + PREFIX_JOB_ROLE + "ROLE "
            + PREFIX_START_TIME + "START TIME "
            + PREFIX_END_TIME + "END TIME" + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_APPLICANT + "18 "
            + PREFIX_JOB_ROLE + "Junior Software Engineer "
            + PREFIX_START_TIME + "03-11-2024 1500 "
            + PREFIX_END_TIME + "03-11-2024 1600";

    public static final String MESSAGE_SUCCESS = "New interview added: %1$s";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "Error: This is a duplicate interview";
    public static final String MESSAGE_APPLICANT_HAS_INTERVIEW =
            "Applicant already has an interview scheduled";
    public static final String MESSAGE_INVALID_TIME = "The interview start time must be before the end time, "
            + "the time must be between 0900 to 1700,\n"
            + "and the start time and end time must be on the same day!";
    public static final String MESSAGE_TIME_CLASH = "This interview clashes with another interview!";


    private final Index applicantIndex;
    private final String jobRole;
    private final Time startTime;
    private final Time endTime;

    /**
     * Creates an AddInterviewCommand to add the specified {@code Interview}
     */
    public AddInterviewCommand(Index applicantIndex, String jobRole,
                               Time startTime, Time endTime) {
        requireAllNonNull(applicantIndex, jobRole, startTime, endTime);

        this.applicantIndex = applicantIndex;
        this.jobRole = jobRole;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (applicantIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
        }

        Applicant applicant = lastShownList.get(applicantIndex.getZeroBased());

        if (applicant.hasInterview()) {
            throw new CommandException(MESSAGE_APPLICANT_HAS_INTERVIEW);
        }

        Applicant applicantWithInterview = applicant.withInterview();

        Interview toAdd = new Interview(applicantWithInterview, jobRole, startTime, endTime);

        if (!toAdd.isValid()) {
            throw new CommandException(MESSAGE_INVALID_TIME);
        }

        if (toAdd.getInterviewStartTime().isPast()) {
            throw new CommandException(MESSAGE_PAST_DATE);
        }

        if (model.hasInterview(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERVIEW);
        }

        if (model.hasInterviewClash(toAdd)) {
            throw new CommandException(MESSAGE_TIME_CLASH);
        }

        model.setApplicant(applicant, applicantWithInterview);
        model.addInterview(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatInterview(toAdd)));
    }

    @Override
    public String toString() {
        return "AddInterviewCommand{"
                + "jobRole='" + jobRole + '\''
                + "startTime=" + startTime + '\''
                + "endTime=" + endTime + '\''
                + '}';
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

        AddInterviewCommand otherAddICommand = (AddInterviewCommand) other;
        return applicantIndex.equals(otherAddICommand.applicantIndex)
                && jobRole.equals(otherAddICommand.jobRole)
                && startTime.equals(otherAddICommand.startTime)
                && endTime.equals(otherAddICommand.endTime);
    }
}
