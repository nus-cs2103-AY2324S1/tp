package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.note.Note;
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
        Person person = model.findPersonByUserFriendlyId(this.contactId);
        if (person == null) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND + this.contactId);
        }
        person.addNote(this.toAdd);

        return new CommandResult(MESSAGE_SUCCESS + toAdd.getTitle());
    }
}
