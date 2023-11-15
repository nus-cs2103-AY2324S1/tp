package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.model.person.timetable.Schedule;
import seedu.address.model.tag.Tag;
import seedu.address.model.user.User;


/**
 * Edits the details of the user of the address book.
 */
public class EditUserCommand extends Command {

    public static final String COMMAND_WORD = "user";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits your details. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_BIRTHDAY + "BIRTHDAY]...\n"
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_USER_SUCCESS = "Edited Your Details: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    public static final String MESSAGE_DUPLICATE_USER = "No changes to user.";
    public static final String MESSAGE_DUPLICATE_PHONE = "This phone number already exists in the address book.";
    public static final String MESSAGE_DUPLICATE_EMAIL = "This email already exists in the address book.";

    private final EditUserDescriptor editUserDescriptor;

    /**
     * @param editUserDescriptor details to edit the user with
     */
    public EditUserCommand(EditUserDescriptor editUserDescriptor) {
        requireNonNull(editUserDescriptor);

        this.editUserDescriptor = new EditUserDescriptor(editUserDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        User userToEdit = model.getUser();
        User editedUser = createEditedUser(userToEdit, editUserDescriptor);

        if (userToEdit.equals(editedUser)) {
            throw new CommandException(MESSAGE_DUPLICATE_USER);
        }

        if (!userToEdit.isSamePhone(editedUser) && model.hasPhone(editedUser)) {
            throw new CommandException(MESSAGE_DUPLICATE_PHONE);
        }

        if (!userToEdit.isSameEmail(editedUser) && model.hasEmail(editedUser)) {
            throw new CommandException(MESSAGE_DUPLICATE_EMAIL);
        }

        model.setUser(editedUser);
        return new CommandResult(String.format(MESSAGE_EDIT_USER_SUCCESS,
            Messages.format(editedUser)), false, false, true, false);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code userToEdit}
     * edited with {@code editUserDescriptor}.
     */
    private static User createEditedUser(User userToEdit, EditUserDescriptor editUserDescriptor) {
        assert userToEdit != null;
        Name updatedName = editUserDescriptor.getName().orElse(userToEdit.getName());
        Phone updatedPhone = editUserDescriptor.getPhone().orElse(userToEdit.getPhone());
        Email updatedEmail = editUserDescriptor.getEmail().orElse(userToEdit.getEmail());
        Address updatedAddress = editUserDescriptor.getAddress().orElse(userToEdit.getAddress());
        Birthday updatedBirthday = editUserDescriptor.getBirthday().orElse(userToEdit.getBirthday());
        Schedule schedule = userToEdit.getSchedule();
        Set<Tag> updatedTags = editUserDescriptor.getTags().orElse(userToEdit.getTags());
        ArrayList<DatedEvent> updatedDatedEvents = editUserDescriptor.getDatedEvents()
                .orElse(userToEdit.getDatedEvents());
        return new User(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedBirthday,
                schedule, updatedTags, updatedDatedEvents);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditUserCommand)) {
            return false;
        }

        EditUserCommand otherEditUserCommand = (EditUserCommand) other;
        return editUserDescriptor.equals(otherEditUserCommand.editUserDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("editUserDescriptor", editUserDescriptor)
                .toString();
    }
}

