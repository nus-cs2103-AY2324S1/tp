package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.note.Note;
import seedu.address.model.note.NoteID;
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

    private final NoteID noteIdToDelete;
    private final ContactID contactId;
    /**
     * Creates a DeleteNoteCommand to delete the specified {@code Note}
     */
    public DeleteNoteCommand(ContactID contactId, NoteID noteIdToDelete) {
        this.contactId = contactId;
        this.noteIdToDelete = noteIdToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person person = model.findPersonByUserFriendlyId(this.contactId);
        if (person == null) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND + this.contactId);
        }
        assert this.contactId.getId() > 0 : "Invalid contact ID";

        Note deletedNote = person.removeNoteByUserFriendlyId(this.noteIdToDelete);
        if (deletedNote == null) {
            throw new CommandException(MESSAGE_NOTE_NOT_FOUND + this.noteIdToDelete);
        }

        assert this.noteIdToDelete.getId() > 0 : "Invalid note ID";

        return new CommandResult(MESSAGE_SUCCESS + this.noteIdToDelete + ". " + deletedNote.getTitle());
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

        boolean equalNoteIdToDelete = noteIdToDelete.equals(otherDeleteNoteCommand.noteIdToDelete);
        boolean equalContactId = contactId.equals(otherDeleteNoteCommand.contactId);
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
