package seedu.staffsnap.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_INTERVIEW;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.staffsnap.model.Model.PREDICATE_HIDE_ALL_APPLICANTS;
import static seedu.staffsnap.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.staffsnap.commons.core.index.Index;
import seedu.staffsnap.commons.util.CollectionUtil;
import seedu.staffsnap.commons.util.ToStringBuilder;
import seedu.staffsnap.logic.Messages;
import seedu.staffsnap.logic.commands.exceptions.CommandException;
import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.interview.Interview;

/**
 * Edits the details of an existing interview of an applicant.
 */
public class EditInterviewCommand extends Command {

    public static final String COMMAND_WORD = "editi";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the applicant's specified interview identified by the index number "
            + "used in the displayed applicant list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_INTERVIEW + "INTERVIEW_INDEX "
            + PREFIX_TYPE + "TYPE\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_INTERVIEW + "2 "
            + PREFIX_TYPE + "Behavioral";

    public static final String MESSAGE_EDIT_INTERVIEW_SUCCESS = "Edited interview of Applicant: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "This interview already exists for this applicant.";

    private final Index applicantIndex;
    private final Index interviewIndex;
    private final EditInterviewDescriptor editInterviewDescriptor;

    /**
     * @param applicantIndex of the applicant in the filtered applicant list to edit
     * @param interviewIndex of the interview in the applicant's interview list
     * @param editInterviewDescriptor details to edit the interview with
     */
    public EditInterviewCommand(
            Index applicantIndex, Index interviewIndex, EditInterviewDescriptor editInterviewDescriptor) {
        requireNonNull(applicantIndex);
        requireNonNull(interviewIndex);
        requireNonNull(editInterviewDescriptor);

        this.applicantIndex = applicantIndex;
        this.interviewIndex = interviewIndex;
        this.editInterviewDescriptor = new EditInterviewDescriptor(editInterviewDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (applicantIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
        }

        Applicant applicantToEdit = lastShownList.get(applicantIndex.getZeroBased());

        if (interviewIndex.getZeroBased() >= applicantToEdit.getInterviews().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
        }

        Interview interviewToEdit = applicantToEdit.getInterviews().get(interviewIndex.getZeroBased());
        Interview editedInterview = createEditedInterview(interviewToEdit, editInterviewDescriptor);

        if (!interviewToEdit.equals(editedInterview) && applicantToEdit.getInterviews().contains(editedInterview)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERVIEW);
        }

        applicantToEdit.deleteInterview(interviewToEdit);
        applicantToEdit.addInterview(editedInterview);

        model.updateFilteredApplicantList(PREDICATE_HIDE_ALL_APPLICANTS);
        model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        return new CommandResult(String.format(MESSAGE_EDIT_INTERVIEW_SUCCESS, Messages.format(applicantToEdit)));
    }

    /**
     * Creates and returns an {@code Interview} with the details of {@code interviewToEdit}
     * edited with {@code editInterviewDescriptor}.
     */
    private static Interview createEditedInterview(
            Interview interviewToEdit, EditInterviewDescriptor editInterviewDescriptor) {
        assert interviewToEdit != null;

        String updatedType = editInterviewDescriptor.getType().orElse(interviewToEdit.getType());

        return new Interview(updatedType);
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

        EditInterviewCommand otherEditCommand = (EditInterviewCommand) other;
        return applicantIndex.equals(otherEditCommand.applicantIndex)
                && interviewIndex.equals(otherEditCommand.interviewIndex)
                && editInterviewDescriptor.equals(otherEditCommand.editInterviewDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("applicantIndex", applicantIndex)
                .add("interviewIndex", interviewIndex)
                .add("editInterviewDescriptor", editInterviewDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the interview with. Each non-empty field value will replace the
     * corresponding field value of the interview.
     */
    public static class EditInterviewDescriptor {
        private String type;

        public EditInterviewDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code interviews} is used internally.
         */
        public EditInterviewDescriptor(EditInterviewDescriptor toCopy) {
            setType(toCopy.type);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(type);
        }

        public void setType(String type) {
            this.type = type;
        }

        public Optional<String> getType() {
            return Optional.ofNullable(type);
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
            return Objects.equals(type, otherEditInterviewDescriptor.type);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("type", type)
                    .toString();
        }
    }
}
