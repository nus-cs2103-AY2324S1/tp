package seedu.address.logic.commands;

import static seedu.address.logic.Messages.MESSAGE_STUDENT_DOES_NOT_EXIST;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentNumber;
import seedu.address.model.tag.Tag;

/**
 * Adds the tags to an existing student in the address book.
 */
public class AddTagCommand extends TagCommand {

    public AddTagCommand(StudentNumber studentNumber, Set<Tag> tags) {
        super(studentNumber, tags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
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
                studentToTag.getStudentNumber(), studentToTag.getClassDetails(), newTags);

        model.setStudent(studentToTag, editedStudent);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_PERSONS);

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
