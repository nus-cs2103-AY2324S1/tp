package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.ContactID;
import seedu.address.model.person.Person;

/**
 * The command handler for {@code delete note} command
 */
public class DeleteNoteCommand extends DeleteCommand {
    public static final String SECONDARY_COMMAND_WORD = "note";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + SECONDARY_COMMAND_WORD + ": Deletes a note from a contact.\n"
            + "Usage:  delete note -id CONTACT_ID -nid NOTE_ID";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Can not find the target contact with ID: ";
    public static final String MESSAGE_SUCCESS = "Successfully deleted note: ";
    public static final String MESSAGE_NOTE_NOT_FOUND = "Note not found: ID = ";

    private final int noteIdToDelete;
    private final int contactId;
    /**
     * Creates an DeleteEventCommand to delete the specified {@code Event}
     */
    public DeleteNoteCommand(int contactId, int noteIdToDelete) {
        this.contactId = contactId;
        this.noteIdToDelete = noteIdToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person person = model.findPersonByUserFriendlyId(ContactID.fromInt(this.contactId));
        if (person == null) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND + this.contactId);
        }
        boolean success = person.removeNoteByUserFriendlyId(this.noteIdToDelete);
        if (!success) {
            throw new CommandException(MESSAGE_NOTE_NOT_FOUND + this.noteIdToDelete);
        }

        return new CommandResult(MESSAGE_SUCCESS + this.noteIdToDelete);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteNoteCommand)) {
            return false;
        }

        DeleteNoteCommand otherDeleteNoteCommand = (DeleteNoteCommand) other;

        boolean equalNoteIdToDelete = (noteIdToDelete == otherDeleteNoteCommand.noteIdToDelete);
        boolean equalContactId = (contactId == otherDeleteNoteCommand.contactId);
        return equalNoteIdToDelete && equalContactId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("noteIdToDelete", noteIdToDelete)
                .add("contactId", contactId)
                .toString();
    }
}
