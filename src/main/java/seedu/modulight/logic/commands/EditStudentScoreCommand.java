package seedu.modulight.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.modulight.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.modulight.logic.parser.CliSyntax.PREFIX_MARKS;
import static seedu.modulight.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.modulight.commons.core.index.Index;
import seedu.modulight.commons.util.CollectionUtil;
import seedu.modulight.commons.util.ToStringBuilder;
import seedu.modulight.logic.Messages;
import seedu.modulight.logic.commands.exceptions.CommandException;
import seedu.modulight.model.Model;
import seedu.modulight.model.gradedcomponent.GcName;
import seedu.modulight.model.gradedcomponent.GradedComponent;
import seedu.modulight.model.gradedcomponent.model.GradedComponentBook;
import seedu.modulight.model.student.Student;
import seedu.modulight.model.student.StudentId;
import seedu.modulight.model.student.StudentName;
import seedu.modulight.model.student.model.StudentBook;
import seedu.modulight.model.studentscore.StudentScore;
import seedu.modulight.model.studentscore.model.StudentScoreBook;
import seedu.modulight.model.tag.Tag;


/**
 * Command Class for editing StudentScore
 */
public class EditStudentScoreCommand extends Command {
    public static final String COMMAND_WORD = "editScore";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the StudentScore identified "
            + "by the index number used in the displayed StudentScore list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: " + "INDEX "
            + "[" + PREFIX_MARKS + "SCORE] "
            + "[" + PREFIX_COMMENT + "COMMENT] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MARKS + "35.4 "
            + PREFIX_COMMENT + "Good JOB!";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited student score: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT_SCORE = "This studentScore already "
            + "exists in the address book.";
    public static final String SCORE_VALUE_NOT_VALID = "Score should be >= 0 and "
            + "<= the associated component's maximum marks.";
    public static final String MESSAGE_INVALID_SCORE_DISPLAYED_INDEX = "The score index provided is invalid.";

    private final EditStudentScoreDescriptor editStudentScoreDescriptor;
    private final Index index;
    private final boolean useFiltered;

    /**
     * Constructor for EditStudentScoreCommand.
     *
     * @param index the Index of the StudentScore that want to be edited
     * @param editStudentScoreDescriptor the editStudentScoreDescriptor
     */
    public EditStudentScoreCommand(Index index, EditStudentScoreDescriptor editStudentScoreDescriptor,
                                   boolean useFiltered) {
        requireNonNull(index);
        requireNonNull(editStudentScoreDescriptor);

        this.index = index;
        this.editStudentScoreDescriptor = editStudentScoreDescriptor;
        this.useFiltered = useFiltered;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        StudentBook studentBook = model.getStudentBook();
        GradedComponentBook gradedComponentBook = model.getGradedComponentBook();
        StudentScoreBook studentScoreBook = model.getStudentScoreBook();

        List<StudentScore> lastShownList = model.getFilteredStudentScoreList();
        if (!useFiltered) {
            lastShownList = studentScoreBook.getStudentScoreList();
        }

        boolean isLessThanOne = index.getZeroBased() < 0;
        boolean isLargerThanSize = index.getZeroBased() >= lastShownList.size();
        if (isLessThanOne || isLargerThanSize) {
            throw new CommandException(MESSAGE_INVALID_SCORE_DISPLAYED_INDEX);
        }

        StudentScore studentScoreToEdit = lastShownList.get(index.getZeroBased());
        StudentScore editedStudentScore = createEditedStudentScore(studentScoreToEdit,
                editStudentScoreDescriptor);

        if (!studentScoreToEdit.isSameScore(editedStudentScore)
                && studentScoreBook.hasStudentScore(editedStudentScore)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT_SCORE);
        }

        GradedComponent gc = gradedComponentBook.getGradedComponentByName(editedStudentScore.getGcName());
        Student student = studentBook.getStudentById(editedStudentScore.getStudentId());

        if (editedStudentScore.getScore() > gc.getMaxMarks().maxMarks || editedStudentScore.getScore() < 0) {
            throw new CommandException(SCORE_VALUE_NOT_VALID);
        }

        studentScoreBook.setStudentScore(studentScoreToEdit, editedStudentScore);

        gc.deleteScore(studentScoreToEdit);
        student.deleteScore(studentScoreToEdit);
        gc.addScore(editedStudentScore);
        student.addScore(editedStudentScore);

        studentBook.setStudent(student, student);
        gradedComponentBook.setGradedComponent(gc, gc);

        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS,
                Messages.formatStudentScore(editedStudentScore)));
    }

    private static StudentScore createEditedStudentScore(StudentScore studentScoreToEdit,
                                                            EditStudentScoreDescriptor editStudentScoreDescriptor) {
        assert studentScoreToEdit != null;
        assert editStudentScoreDescriptor != null;


        StudentId updatedSid = editStudentScoreDescriptor.getStudentId()
                .orElse(studentScoreToEdit.getStudentId());
        GcName updatedGcName = editStudentScoreDescriptor.getGcName()
                .orElse(studentScoreToEdit.getGcName());
        float updatedScore = editStudentScoreDescriptor.getScore()
                .orElse(studentScoreToEdit.getScore());
        String updatedComment = editStudentScoreDescriptor.getComment()
                .orElse(studentScoreToEdit.getComment());
        Set<Tag> updatedTags = editStudentScoreDescriptor.getTags().orElse(studentScoreToEdit.getTags());
        StudentScore sc = new StudentScore(updatedSid,
                updatedGcName, updatedScore, updatedComment, updatedTags);
        sc.setStudent(studentScoreToEdit.getStudent());
        sc.setGradedComponent(studentScoreToEdit.getGradedComponent());
        return sc;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditStudentScoreCommand)) {
            return false;
        }

        EditStudentScoreCommand otherEditCommand = (EditStudentScoreCommand) other;
        return editStudentScoreDescriptor.equals(otherEditCommand.editStudentScoreDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("editStudentScoreDescriptor", editStudentScoreDescriptor)
                .toString();
    }

    /**
     * Static Class to represent StudentStore Descriptor
     *
     */
    public static class EditStudentScoreDescriptor {
        private StudentId sid;
        private StudentName name;
        private GcName gcName;
        private Float score;
        private String comment;

        private Set<Tag> tags;

        /**
         * Empty constructor for EditStudentScoreDescriptor.
         *
         */
        public EditStudentScoreDescriptor() {
        }

        /**
         * Constructor for EditStudentScoreDescriptor.
         * @param toCopy EditStudentScoreDescriptor to copy from.
         */
        public EditStudentScoreDescriptor(EditStudentScoreDescriptor toCopy) {
            setStudentId(toCopy.sid);
            setName(toCopy.name);
            setGcName(toCopy.gcName);
            setScore(toCopy.score);
            setComment(toCopy.comment);
            setTags(toCopy.tags);
        }
        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(score, comment, tags);
        }

        public void setStudentId(StudentId sid) {
            this.sid = sid;
        }

        public Optional<StudentId> getStudentId() {
            return Optional.ofNullable(this.sid);
        }

        public void setName(StudentName name) {
            this.name = name;
        }

        public Optional<StudentName> getName() {
            return Optional.ofNullable(this.name);
        }

        public void setGcName(GcName gcName) {
            this.gcName = gcName;
        }

        public Optional<GcName> getGcName() {
            return Optional.ofNullable(this.gcName);
        }

        public void setScore(float score) {
            this.score = score;
        }
        public Optional<Float> getScore() {
            return Optional.ofNullable(this.score);
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
        public Optional<String> getComment() {
            return Optional.ofNullable(this.comment);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditStudentScoreDescriptor)) {
                return false;
            }

            EditStudentScoreDescriptor otherEditStudentScoreDescriptor =
                    (EditStudentScoreDescriptor) other;

            // null safe equal
            boolean equalSid = this.sid == otherEditStudentScoreDescriptor.sid;
            boolean equalGcName = this.gcName == otherEditStudentScoreDescriptor.gcName;
            if (equalSid && equalGcName) {
                return true;
            }

            return sid.equals(otherEditStudentScoreDescriptor.sid)
                    && gcName.equals((otherEditStudentScoreDescriptor).gcName);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("student id", sid)
                    .add("student name", name)
                    .add("component name", gcName)
                    .add("score", score)
                    .add("comment", comment)
                    .add("tags", tags)
                    .toString();
        }
    }
}
