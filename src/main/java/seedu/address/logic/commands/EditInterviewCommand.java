package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMING;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERVIEWS;

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
            + "book. Only changes to Job Role and Timing are supported\n"
            + "Parameters: Interview Index (must be a positive integer) "
            + PREFIX_JOB_ROLE + "ROLE "
            + PREFIX_TIMING + "TIMING" + "\n"
            + "Example: " + COMMAND_WORD + " 3 "
            + PREFIX_JOB_ROLE + "Junior Software Engineer "
            + PREFIX_TIMING + "2023-10-24 18:00";

    public static final String MESSAGE_EDIT_INTERVIEW_SUCCESS = "Edited Interview: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "This interview already exists in the address book.";

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

        if (!interviewToEdit.isNotValidOrNewInterview(editedInterview) && model.hasInterview(editedInterview)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERVIEW);
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
        String updatedTiming = editInterviewDescriptor.getInterviewTime().orElse(interviewToEdit.getInterviewTiming());
        boolean updatedDoneStatus = editInterviewDescriptor.hasBeenDone().orElse(interviewToEdit.isDone());

        return new Interview(interviewToEdit.getInterviewApplicant(), updatedJobRole, updatedTiming, updatedDoneStatus);
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
        /** TODO Change from 'String' to proper 'Date/Time' once natural DT is implemented*/
        private String interviewTiming;
        private boolean isDone;

        public EditInterviewDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditInterviewDescriptor(EditInterviewDescriptor toCopy) {
            setJobRole(toCopy.jobRole);
            setInterviewTime(toCopy.interviewTiming);
            setDoneStatus(toCopy.isDone);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(jobRole, interviewTiming, isDone);
        }

        public void setJobRole(String role) {
            jobRole = role;
        }

        public Optional<String> getJobRole() {
            return Optional.ofNullable(jobRole);
        }

        public void setInterviewTime(String timing) {
            interviewTiming = timing;
        }

        public Optional<String> getInterviewTime() {
            return Optional.ofNullable(interviewTiming);
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
                    && Objects.equals(interviewTiming, otherEditInterviewDescriptor.interviewTiming)
                    && Objects.equals(isDone, otherEditInterviewDescriptor.isDone);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("role", jobRole)
                    .add("interviewTiming", interviewTiming)
                    .add("isDone", isDone)
                    .toString();
        }
    }
}
