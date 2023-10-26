package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.note.Note;
import seedu.address.model.person.ContactID;
import seedu.address.model.person.Person;

/**
 * The command handler for {@code add note} command
 */
public class AddNoteCommand extends AddCommand {
    public static final String SECONDARY_COMMAND_WORD = "note";
    public static final String MESSAGE_SUCCESS = "New note added: ";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + SECONDARY_COMMAND_WORD
            + ": Adds a note to a contact from the contact list.\n"
            + "Usage:  add note -id CONTACT_ID -tit NOTE_TITLE -con NOTE_CONTENT";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Can not find the target contact with ID: ";

    private final Note toAdd;
    private final int contactId;

    /**
     * Creates an AddNoteCommand to add the specified {@code Note}
     */
    public AddNoteCommand(int contactId, Note note) {
        requireNonNull(note);
        this.contactId = contactId;
        this.toAdd = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person person = model.findPersonByUserFriendlyId(ContactID.fromInt(this.contactId));
        if (person == null) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND + this.contactId);
        }
        person.addNote(this.toAdd);

        return new CommandResult(MESSAGE_SUCCESS + toAdd.getTitle());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddNoteCommand)) {
            return false;
        }

        AddNoteCommand otherAddNoteCommand = (AddNoteCommand) other;

        boolean equalToAdd = toAdd.equals(otherAddNoteCommand.toAdd);
        boolean equalContactId = (contactId == otherAddNoteCommand.contactId);
        return equalToAdd && equalContactId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .add("contactId", contactId)
                .toString();
    }
}
