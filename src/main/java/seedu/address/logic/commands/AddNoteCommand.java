package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;

/**
 * Adds a note to the person in the command book.
 */
public class AddNoteCommand extends Command {

    public static final String COMMAND_WORD = "addnote";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a note to the person "
        + "identified by the index number used in the last person listing. "
        + "Parameters: INDEX (must be a positive integer) NOTE_CONTENT\n"
        + "Example: " + COMMAND_WORD + " 1 This is a sample note for the person.";
    public static final String MESSAGE_NOTE_SUCCESS = "Added note to person: %s";
    private final Index index;
    private final Note note;

    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param note details of the person to be updated to
     */
    public AddNoteCommand(Index index, Note note) {
        requireAllNonNull(index, note);

        this.index = index;
        this.note = note;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person p = lastShownList.get(index.getZeroBased());
        p.addNote(note);
        return new CommandResult(String.format(MESSAGE_NOTE_SUCCESS, p.getName()));
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

        AddNoteCommand otherAddAltCommand = (AddNoteCommand) other;
        return index.equals(otherAddAltCommand.index)
                && note.equals(otherAddAltCommand.note);
    }

}
