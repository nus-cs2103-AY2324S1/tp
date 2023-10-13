package seedu.address.logic.commands;

import static seedu.address.logic.Messages.MESSAGE_STUDENT_DOES_NOT_EXIST;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentNumber;
import seedu.address.model.tag.Tag;

/**
 * Deletes the tags from an existing student in the address book.
 */
public class DeleteTagCommand extends TagCommand {

    public DeleteTagCommand(StudentNumber studentNumber, Set<Tag> tags) {
        super(studentNumber, tags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        Person studentToTag;
        try {
            studentToTag = getStudentByStudentNumber(lastShownList, studentNumber);
        } catch (NullPointerException ive) {
            throw new CommandException(MESSAGE_STUDENT_DOES_NOT_EXIST);
        }
        Set<Tag> newTags = deleteTags(studentToTag.getTags(), super.tags);
        Person editedStudent = new Person(
                studentToTag.getName(), studentToTag.getPhone(), studentToTag.getEmail(),
                studentToTag.getStudentNumber(), studentToTag.getClassNumber(), newTags);

        model.setPerson(studentToTag, editedStudent);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedStudent));
    }

    private Set<Tag> deleteTags(Set<Tag> studentTags, Set<Tag> tagsToDelete) {
        Set<Tag> newTags = new HashSet<>(studentTags);
        newTags.removeAll(tagsToDelete);
        return newTags;
    }

    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_DELETE_TAG_SUCCESS, personToEdit.getName()) + tags;
    }
}
