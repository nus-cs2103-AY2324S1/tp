package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.application.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_ADDRESS;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATETIME;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_TYPE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_JOB_SOURCE;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.application.commons.core.index.Index;
import seedu.application.commons.util.CollectionUtil;
import seedu.application.commons.util.ToStringBuilder;
import seedu.application.logic.Messages;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;
import seedu.application.model.job.Job;
import seedu.application.model.job.interview.Interview;
import seedu.application.model.job.interview.InterviewAddress;
import seedu.application.model.job.interview.InterviewDateTime;
import seedu.application.model.job.interview.InterviewType;

/**
 * Edits an interview to a job in the application book.
 */

public class InterviewEditCommand extends InterviewCommand {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = InterviewCommand.COMMAND_WORD + " " + COMMAND_WORD
        + ": Edits an interview of a job. \n"
        + "Parameters: INDEX (of interview) "
        + PREFIX_JOB_SOURCE + "INDEX (of job)"
        + "[" + PREFIX_INTERVIEW_TYPE + "INTERVIEW TYPE]"
        + "[" + PREFIX_INTERVIEW_DATETIME + "INTERVIEW DATE AND TIME]"
        + "[" + PREFIX_INTERVIEW_ADDRESS + "INTERVIEW ADDRESS]\n"
        + "Example: \n"
        + "1 " + PREFIX_JOB_SOURCE + "2 "
        + PREFIX_INTERVIEW_TYPE + "Technical "
        + PREFIX_INTERVIEW_DATETIME + "Dec 31 2030 1200 "
        + PREFIX_INTERVIEW_ADDRESS + "Home\n"
        + "edits 1st interview from 2nd job";

    public static final String MESSAGE_SUCCESS = "Interview successfully edited: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided. \n"
        + PREFIX_INTERVIEW_TYPE + " for Interview Type\n"
        + PREFIX_INTERVIEW_DATETIME + " for Interview Date and Time\n"
        + PREFIX_INTERVIEW_ADDRESS + " for Interview Address\n";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "This interview already exists for the job";

    private final Index jobIndex;
    private final Index interviewIndex;
    private final EditInterviewDescriptor editInterviewDescriptor;

    /**
     * @param jobIndex                index of the job in the filtered job list to edit
     * @param interviewIndex          index of the interview in the job to edit.
     * @param editInterviewDescriptor details to edit the interview with
     */
    public InterviewEditCommand(Index jobIndex, Index interviewIndex, EditInterviewDescriptor editInterviewDescriptor) {
        requireAllNonNull(jobIndex, interviewIndex);
        requireNonNull(editInterviewDescriptor);

        this.jobIndex = jobIndex;
        this.interviewIndex = interviewIndex;
        this.editInterviewDescriptor = new EditInterviewDescriptor(editInterviewDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Job> lastShownList = model.getFilteredJobList();

        if (jobIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
        }

        Job jobToEditInterview = lastShownList.get(jobIndex.getZeroBased());

        if (interviewIndex.getZeroBased() >= jobToEditInterview.interviewLength()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERVIEW);
        }

        Interview interviewToEdit = jobToEditInterview.getInterview(interviewIndex);
        Interview editedInterview = createEditedInterview(interviewToEdit, editInterviewDescriptor);

        if (jobToEditInterview.hasInterview(editedInterview)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERVIEW);
        }

        jobToEditInterview.setInterview(interviewToEdit, editedInterview);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(editedInterview)),
            false, false, false, jobIndex.getZeroBased());
    }

    /**
     * Creates and returns a {@code Interview} with the details of {@code interviewToEdit}
     * edited with {@code editInterviewDescriptor}.
     */
    private static Interview createEditedInterview(Interview interviewToEdit,
                                                   EditInterviewDescriptor editInterviewDescriptor) {
        assert interviewToEdit != null;
        InterviewType updatedType = editInterviewDescriptor.getType()
            .orElse(interviewToEdit.getInterviewType());
        InterviewDateTime updatedDateTime = editInterviewDescriptor.getDateTime()
            .orElse(interviewToEdit.getInterviewDateTime());
        InterviewAddress updatedAddress = editInterviewDescriptor.getAddress()
            .orElse(interviewToEdit.getInterviewAddress());
        return new Interview(updatedType, updatedDateTime, updatedAddress);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InterviewEditCommand)) {
            return false;
        }

        InterviewEditCommand otherInterviewEditCommand = (InterviewEditCommand) other;
        return jobIndex.equals(otherInterviewEditCommand.jobIndex)
            && interviewIndex.equals(otherInterviewEditCommand.interviewIndex)
            && editInterviewDescriptor.equals(otherInterviewEditCommand.editInterviewDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("jobIndex", jobIndex)
            .add("interviewIndex", interviewIndex)
            .add("editInterviewDescriptor", editInterviewDescriptor)
            .toString();
    }

    /**
     * Stores the details to edit the interview with. Each non-empty field value will replace the
     * corresponding field value of the interview.
     */
    public static class EditInterviewDescriptor {
        private InterviewType interviewType;
        private InterviewDateTime interviewDateTime;
        private InterviewAddress interviewAddress;

        public EditInterviewDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditInterviewDescriptor(EditInterviewDescriptor toCopy) {
            setType(toCopy.interviewType);
            setDateTime(toCopy.interviewDateTime);
            setAddress(toCopy.interviewAddress);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(interviewType, interviewDateTime, interviewAddress);
        }

        public void setType(InterviewType interviewType) {
            this.interviewType = interviewType;
        }

        public Optional<InterviewType> getType() {
            return Optional.ofNullable(interviewType);
        }

        public void setDateTime(InterviewDateTime interviewDateTime) {
            this.interviewDateTime = interviewDateTime;
        }

        public Optional<InterviewDateTime> getDateTime() {
            return Optional.ofNullable(interviewDateTime);
        }

        public void setAddress(InterviewAddress interviewAddress) {
            this.interviewAddress = interviewAddress;
        }

        public Optional<InterviewAddress> getAddress() {
            return Optional.ofNullable(interviewAddress);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditInterviewDescriptor)) {
                return false;
            }

            EditInterviewDescriptor otherEditInterviewDescriptor = (EditInterviewDescriptor) other;
            return Objects.equals(interviewType, otherEditInterviewDescriptor.interviewType)
                && Objects.equals(interviewDateTime, otherEditInterviewDescriptor.interviewDateTime)
                && Objects.equals(interviewAddress, otherEditInterviewDescriptor.interviewAddress);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                .add("interview type", interviewType)
                .add("interview date time", interviewDateTime)
                .add("interview address", interviewAddress)
                .toString();
        }
    }

}
