package seedu.classmanager.logic.commands;

import static seedu.classmanager.logic.Messages.MESSAGE_STUDENT_DOES_NOT_EXIST;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.classmanager.logic.CommandHistory;
import seedu.classmanager.logic.commands.exceptions.CommandException;
import seedu.classmanager.model.Model;
import seedu.classmanager.model.student.Student;
import seedu.classmanager.model.student.StudentNumber;
import seedu.classmanager.model.tag.Tag;

/**
 * Adds the tags to an existing student in Class Manager.
 */
public class AddTagCommand extends TagCommand {

    /**
     * Creates an AddTagCommand to add the specified {@code tags} to a student.
     * @param studentNumber of the student in the filtered student list to edit their tags.
     * @param tags of the student to be updated to.
     */
    public AddTagCommand(StudentNumber studentNumber, Set<Tag> tags) {
        super(studentNumber, tags);
    }

    /**
     * Adds certain tags to an existing student in Class Manager.
     * @param model {@code Model} which the command should operate on.
     * @param commandHistory The command history to record this command.
     * @return A {@code CommandResult} with the feedback message of the operation result.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) throws CommandException {
        List<Student> lastShownList = model.getFilteredStudentList();

        Student studentToTag;
        try {
            studentToTag = getStudentByStudentNumber(lastShownList, studentNumber);
        } catch (NullPointerException ive) {
            throw new CommandException(MESSAGE_STUDENT_DOES_NOT_EXIST);
        }
        Set<Tag> newTags = addTags(studentToTag.getTags(), super.tags);
        Student editedStudent = new Student(
                studentToTag.getName(), studentToTag.getPhone(), studentToTag.getEmail(),
                studentToTag.getStudentNumber(), studentToTag.getClassDetails(), newTags, studentToTag.getComment());

        model.setStudent(studentToTag, editedStudent);
        model.commitClassManager();

        return new CommandResult(generateSuccessMessage(editedStudent));
    }

    private Set<Tag> addTags(Set<Tag> studentTags, Set<Tag> tagsToAdd) {
        Set<Tag> newTags = new HashSet<>(studentTags);
        newTags.addAll(tagsToAdd);
        return newTags;
    }

    private String generateSuccessMessage(Student studentToEdit) {
        return String.format(MESSAGE_ADD_TAG_SUCCESS, studentToEdit.getName()) + tags;
    }
}
