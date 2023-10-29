package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERVIEWS;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;

/**
 * Edits the details of an existing interview in the address book.
 * Adapted from AB3's EditCommand
 */
public class EditInterviewCommand extends Command {

    public static final String COMMAND_WORD = "edit-i";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits an existing interview details in the address "
            + "book. Only changes to Job Role and Timing are supported.\n"
            + "Parameters: Interview Index (must be a positive integer) "
            + PREFIX_JOB_ROLE + "ROLE "
            + PREFIX_START_TIME + "START TIME "
            + PREFIX_END_TIME + "END TIME" + "\n"
            + "Example: " + COMMAND_WORD + " 3 "
            + PREFIX_JOB_ROLE + "Junior Software Engineer "
            + PREFIX_START_TIME + "03-11-2024 1600 "
            + PREFIX_END_TIME + "03-11-2024 1800";

    public static final String MESSAGE_EDIT_INTERVIEW_SUCCESS = "Edited Interview: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "This interview already exists in the address book.";
    public static final String MESSAGE_INVALID_TIME = "The interview start time must be before the end time, "
            + "the time must be between 0900 to 1700,\n"
            + "and the start time and end time must be on the same day!";

    private final Index index;
    private final EditInterviewDescriptor editInterviewDescriptor;

    /**
     * @param index of the interview in the filtered interview list to edit
     * @param editInterviewDescriptor details to edit the interview with
     */
    public EditInterviewCommand(Index index, EditInterviewDescriptor editInterviewDescriptor) {
        requireNonNull(index);
        requireNonNull(editInterviewDescriptor);

        this.index = index;
        this.editInterviewDescriptor = new EditInterviewDescriptor(editInterviewDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Interview> lastShownList = model.getFilteredInterviewList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
        }

        Interview interviewToEdit = lastShownList.get(index.getZeroBased());
        Interview editedInterview = createEditedInterview(interviewToEdit, editInterviewDescriptor);

        if (!interviewToEdit.isSameInterview(editedInterview) && model.hasInterview(editedInterview)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERVIEW);
        }

        if (!editedInterview.isValid()) {
            throw new CommandException(MESSAGE_INVALID_TIME);
        }

        model.setInterview(interviewToEdit, editedInterview);
        model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        model.updateFilteredInterviewList(PREDICATE_SHOW_ALL_INTERVIEWS);
        return new CommandResult(String.format(MESSAGE_EDIT_INTERVIEW_SUCCESS,
                Messages.formatInterview(editedInterview)));
    }

    /**
     * Creates and returns a {@code Interview} with the details of {@code interviewToEdit}
     * edited with {@code editInterviewDescriptor}.
     */
    private static Interview createEditedInterview(Interview interviewToEdit,
                                                   EditInterviewDescriptor editInterviewDescriptor) {
        assert interviewToEdit != null;

        String updatedJobRole = editInterviewDescriptor.getJobRole().orElse(interviewToEdit.getJobRole());
        LocalDateTime updatedStartTime = editInterviewDescriptor
                .getStartTime().orElse(interviewToEdit.getInterviewStartTime());
        LocalDateTime updatedEndTime = editInterviewDescriptor
                .getEndTime().orElse(interviewToEdit.getInterviewEndTime());
        boolean updatedDoneStatus = editInterviewDescriptor.hasBeenDone().orElse(interviewToEdit.isDone());

        return new Interview(interviewToEdit.getInterviewApplicant(),
                updatedJobRole, updatedStartTime, updatedEndTime, updatedDoneStatus);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditInterviewCommand)) {
            return false;
        }

        EditInterviewCommand otherEditInterviewCommand = (EditInterviewCommand) other;
        return index.equals(otherEditInterviewCommand.index)
                && editInterviewDescriptor.equals(otherEditInterviewCommand.editInterviewDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editInterviewDescriptor", editInterviewDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the interview with. Each non-empty field value will replace the
     * corresponding field value of the applicant.
     */
    public static class EditInterviewDescriptor {
        private String jobRole;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private boolean isDone;

        public EditInterviewDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditInterviewDescriptor(EditInterviewDescriptor toCopy) {
            setJobRole(toCopy.jobRole);
            setStartTime(toCopy.startTime);
            setEndTime(toCopy.endTime);
            setDoneStatus(toCopy.isDone);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(jobRole, startTime, endTime, isDone);
        }

        public void setJobRole(String role) {
            jobRole = role;
        }

        public Optional<String> getJobRole() {
            return Optional.ofNullable(jobRole);
        }

        public void setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
        }

        public Optional<LocalDateTime> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
        }

        public Optional<LocalDateTime> getEndTime() {
            return Optional.ofNullable(endTime);
        }

        public void setDoneStatus(boolean isDone) {
            this.isDone = isDone;
        }

        public Optional<Boolean> hasBeenDone() {
            return Optional.of(isDone);
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
            return Objects.equals(jobRole, otherEditInterviewDescriptor.jobRole)
                    && Objects.equals(startTime, otherEditInterviewDescriptor.startTime)
                    && Objects.equals(endTime, otherEditInterviewDescriptor.endTime)
                    && Objects.equals(isDone, otherEditInterviewDescriptor.isDone);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("role", jobRole)
                    .add("startTime", startTime)
                    .add("endTime", endTime)
                    .add("isDone", isDone)
                    .toString();
        }
    }
}
