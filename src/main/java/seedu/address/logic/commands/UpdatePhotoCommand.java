package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVATAR;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Avatar;
import seedu.address.model.person.Balance;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Linkedin;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Tag;

/**
 * Updates the photo of an existing contact in the address book.
 */
public class UpdatePhotoCommand extends Command {

    public static final String COMMAND_WORD = "updatephoto";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates photo of a contact by "
            + "specifying his/her contact and path to the desired photo.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_AVATAR + "[PATH]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_AVATAR + "D:/image/cute_cat.png";

    public static final String MESSAGE_SUCCESS = "Photo updated";
    private final Index index;
    private String path;

    /**
     * Creates an UpdatePhotoCommand to replace the current photo
     * of a specific contact by the photo given by the path.
     *
     * @param index Index of the contact to update photo
     * @param path String path to the photo to be used
     */
    public UpdatePhotoCommand(Index index, String path) {
        this.index = index;
        this.path = path;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() < 0 || index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        Avatar avatar;
        try {
            avatar = new Avatar(path, model.getDataDirectoryPath());
        } catch (FileNotFoundException e) {
            throw new CommandException("Invalid file path provided");
        } catch (IOException e) {
            throw new CommandException(String.format("Error while reading image: %s", e.getMessage()));
        }

        Person editedPerson = createPersonWithAvatar(personToEdit, avatar);
        model.setPerson(personToEdit, editedPerson);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    private Person createPersonWithAvatar(Person personToEdit, Avatar avatar) {
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
        ObservableList<Note> notes = personToEdit.getNotes();
        Balance balance = personToEdit.getBalance();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedBirthday,
                linkedin, secondaryEmail, telegram, updatedTags, id, avatar, notes, balance);
    }
}







