package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.note.Note;
import seedu.address.ui.AppState;

/**
 * Changes the note of an existing contact in the address book.
 */
public class AddNoteCommand extends Command {

    public static final String COMMAND_WORD = "addnote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Adds note to the contact identified "
        + "by the index number used in the last contact listing.\n"
        + "Parameters: " + PREFIX_INDEX + " (must be a positive integer) "
        + PREFIX_NOTE + " [NOTE]\n"
        + "Example: " + COMMAND_WORD + " " + PREFIX_INDEX + " 1 "
        + PREFIX_NOTE + " Likes to swim.";

    public static final String MESSAGE_ADD_NOTE_SUCCESS = "Added note to Person: %1$s";
    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Removed note from Person: %1$s";
    private static final Logger logger = LogsCenter.getLogger(AddNoteCommand.class);
    private final Index index;
    private final Note note;

    /**
     * @param index of the person in the filtered person list to edit the note
     * @param note  of the person to be updated to
     */
    public AddNoteCommand(Index index, Note note) {
        requireAllNonNull(index, note);

        this.index = index;
        this.note = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Contact> lastShownList = model.getFilteredContactList();

        if (index.getZeroBased() >= lastShownList.size()) {
            logger.warning("The index is out of bounds: " + index.getZeroBased());
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contactToEdit = lastShownList.get(index.getZeroBased());

        ArrayList<Note> mutableNotesList = new ArrayList<>(contactToEdit.getNotes());

        if (mutableNotesList.contains(note)) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_NOTES);
        }

        mutableNotesList.add(note);

        Contact editedContact = Contact.editContactNotes(contactToEdit, mutableNotesList);

        model.setContact(contactToEdit, editedContact);
        model.updateFilteredContactList(Model.PREDICATE_SHOW_ALL_CONTACTS);

        AppState appState = AppState.getInstance();
        appState.setContact(editedContact);

        return new CommandResult(generateSuccessMessage(editedContact), false, false);
    }

    /**
     * Generates a command execution success message based on whether
     * the note is added to or removed from
     * {@code contactToEdit}.
     */
    private String generateSuccessMessage(Contact contactToEdit) {
        String message = !note.note.isEmpty() ? MESSAGE_ADD_NOTE_SUCCESS : MESSAGE_DELETE_NOTE_SUCCESS;
        return String.format(message, Messages.formatContact(contactToEdit));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddNoteCommand)) {
            return false;
        }

        // state check
        AddNoteCommand e = (AddNoteCommand) other;
        return index.equals(e.index)
            && note.equals(e.note);
    }
}
