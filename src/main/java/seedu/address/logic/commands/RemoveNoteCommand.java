package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Removes a note from the person in the command book based on the index of the person and the note.
 */
public class RemoveNoteCommand extends Command {

    public static final String COMMAND_WORD = "removenote";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes the note from the person "
        + "identified by the index number and the index of the note.\n"
        + "Parameters: INDEX_PERSON INDEX_NOTE\n"
        + "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_REMOVE_NOTE_SUCCESS = "Removed note from person: %1$s";

    private final Index indexPerson;
    private final Index indexNote;

    /**
     * Creates a RemoveNoteCommand to remove the specified {@code Note} from the {@code Person}
     * @param indexPerson
     * @param indexNote
     */
    public RemoveNoteCommand(Index indexPerson, Index indexNote) {
        requireAllNonNull(indexPerson, indexNote);

        this.indexPerson = indexPerson;
        this.indexNote = indexNote;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (indexPerson.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person = lastShownList.get(indexPerson.getZeroBased());
        if (indexNote.getZeroBased() >= person.getNotes().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);

        }
        person.removeNote(indexNote.getZeroBased());

        return new CommandResult(String.format(MESSAGE_REMOVE_NOTE_SUCCESS, person.getName().toString()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RemoveNoteCommand)) {
            return false;
        }

        RemoveNoteCommand otherCommand = (RemoveNoteCommand) other;
        return indexPerson.equals(otherCommand.indexPerson)
            && indexNote.equals(otherCommand.indexNote);
    }
}
