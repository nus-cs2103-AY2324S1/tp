package seedu.address.logic.commands;

import static seedu.address.logic.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Deletes the tags of an existing student in the address book.
 */
public class DeleteTagCommand extends TagCommand {

    public DeleteTagCommand(Index index, Set<Tag> tags) {
        super(index, tags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Set<Tag> newTags = deleteTags(personToEdit.getTags(), super.tags);
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), newTags);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
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
