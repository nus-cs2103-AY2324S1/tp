package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Avatar;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Linkedin;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Tag;

public class UpdatePhotoCommand extends Command {

    public static final String COMMAND_WORD = "updatephoto";
    public static final String MESSAGE_SUCCESS = "Photo updated";
    private final int zeroBasedIdx;
    private final String path;

    public UpdatePhotoCommand(int idx, String path) {
        zeroBasedIdx = idx - 1;
        this.path = path;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (zeroBasedIdx < 0 || zeroBasedIdx >= model.getFilteredPersonList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(zeroBasedIdx);
        Person editedPerson = copyPerson(personToEdit);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    private Person copyPerson(Person personToEdit) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        Optional<Birthday> updatedBirthday = personToEdit.getBirthday();
        Optional<Linkedin> linkedin = personToEdit.getLinkedin();
        Optional<Email> secondaryEmail = personToEdit.getSecondaryEmail();
        Optional<Telegram> telegram = personToEdit.getTelegram();
        Set<Tag> updatedTags = personToEdit.getTags();
        Optional<Integer> id = personToEdit.getId();
        List<Note> notes = personToEdit.getNotes();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedBirthday,
                linkedin, secondaryEmail, telegram, updatedTags, id, new Avatar(path), notes);
    }
}

