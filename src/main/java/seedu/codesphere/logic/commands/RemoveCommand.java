//@@author tiongjjyi
package seedu.codesphere.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_PENDING_QUESTION;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.codesphere.model.Model.PREDICATE_SHOW_ALL_COURSES;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.codesphere.commons.core.index.Index;
import seedu.codesphere.commons.util.CollectionUtil;
import seedu.codesphere.commons.util.ToStringBuilder;
import seedu.codesphere.logic.Messages;
import seedu.codesphere.logic.commands.exceptions.CommandException;
import seedu.codesphere.logic.stagemanager.StageManager;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.student.Email;
import seedu.codesphere.model.student.Name;
import seedu.codesphere.model.student.PendingQuestion;
import seedu.codesphere.model.student.Remark;
import seedu.codesphere.model.student.Student;
import seedu.codesphere.model.tag.Tag;

/**
 * Removes the remark or pending question of an existing student in the address book.
 */
public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Remove the remark or the "
            + "pending question of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing remark or pending question will be removed. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_REMARK + "] "
            + "[" + PREFIX_PENDING_QUESTION + "] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_REMARK + " "
            + PREFIX_PENDING_QUESTION;

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Remark / Pending Question removed from student: \n%1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field of remark or "
            + "pending question to be removed must be provided." + "\nExample: remove 1 r/ pq/";
    public static final String MESSAGE_DUPLICATE_STUDENT = "Possible duplicate student: "
            + "Email already exist in the student list.\n"
            + "Check student details again.";

    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * @param index of the student in the filtered student list to edit
     * @param editStudentDescriptor details to edit the student with
     */
    public RemoveCommand(Index index, EditStudentDescriptor editStudentDescriptor) {
        requireNonNull(index);
        requireNonNull(editStudentDescriptor);

        this.index = index;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        StageManager stageManager = StageManager.getInstance();
        Course course = stageManager.getSelectedCourse();
        List<Student> lastShownList = course.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor);

        if (!studentToEdit.isSameStudent(editedStudent) && course.hasStudent(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        course.setStudent(studentToEdit, editedStudent);
        model.updateFilteredCourseList(PREDICATE_SHOW_ALL_COURSES);
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, Messages.format(editedStudent)));
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        Email updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        Remark updatedRemark = editStudentDescriptor.getRemark().orElse(studentToEdit.getRemark());
        PendingQuestion updatedPq = editStudentDescriptor.getPendingQuestion()
                .orElse(studentToEdit.getPendingQuestion());
        Tag updatedTag = editStudentDescriptor.getTag().orElse(studentToEdit.getTag());

        return new Student(updatedName, updatedEmail, updatedRemark, updatedPq, updatedTag);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemoveCommand)) {
            return false;
        }

        RemoveCommand otherRemoveCommand = (RemoveCommand) other;
        return index.equals(otherRemoveCommand.index)
                && editStudentDescriptor.equals(otherRemoveCommand.editStudentDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editStudentDescriptor", editStudentDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor {
        private Name name;
        private Email email;
        private Remark remark;
        private PendingQuestion pendingQuestion;
        private Tag tag;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setEmail(toCopy.email);
            setRemark(toCopy.remark);
            setPendingQuestion(toCopy.pendingQuestion);
            setTag(toCopy.tag);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(remark, pendingQuestion);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setRemark(Remark remark) {
            this.remark = remark;
        }


        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
        }

        public void setPendingQuestion(PendingQuestion pendingQuestion) {
            this.pendingQuestion = pendingQuestion;
        }

        public Optional<PendingQuestion> getPendingQuestion() {
            return Optional.ofNullable(pendingQuestion);
        }


        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTag(Tag tag) {
            this.tag = tag;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Tag> getTag() {
            return Optional.ofNullable(tag);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditStudentDescriptor)) {
                return false;
            }

            EditStudentDescriptor otherEditStudentDescriptor = (EditStudentDescriptor) other;
            return Objects.equals(name, otherEditStudentDescriptor.name)
                    && Objects.equals(email, otherEditStudentDescriptor.email)
                    && Objects.equals(remark, otherEditStudentDescriptor.remark)
                    && Objects.equals(pendingQuestion, otherEditStudentDescriptor.pendingQuestion)
                    && Objects.equals(tag, otherEditStudentDescriptor.tag);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("email", email)
                    .add("remark", remark)
                    .add("pending question", pendingQuestion)
                    .add("tag", tag)
                    .toString();
        }
    }
}
