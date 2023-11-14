package seedu.address.logic.commands.note;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.messages.Messages;
import seedu.address.model.SeplendidModel;
import seedu.address.model.note.Note;
import seedu.address.seplendidui.UiUtil;

/**
 * Adds a note to the NoteList.
 */
public class NoteAddCommand extends NoteCommand {

    public static final String ACTION_WORD = "add";
    public static final String MESSAGE_SUCCESS = "New note added: %1$s";
    public static final String MESSAGE_DUPLICATE_NOTE = "Note cannot have duplicated content from existing notes.";

    private final Note noteToAdd;

    /**
     * Creates a NoteAddCommand to add the specified {@code Note}
     *
     * @param note The Note to be added into Storage.
     */
    public NoteAddCommand(Note note) {
        super();
        requireNonNull(note);
        noteToAdd = note;
    }

    @Override
    public CommandResult execute(SeplendidModel seplendidModel) throws CommandException {
        requireNonNull(seplendidModel);

        if (seplendidModel.hasNote(noteToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_NOTE);
        }

        seplendidModel.addNote(noteToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(noteToAdd)),
                UiUtil.ListViewModel.NOTE_LIST);

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteAddCommand)) {
            return false;
        }

        NoteAddCommand otherNoteAddCommand = (NoteAddCommand) other;
        return noteToAdd.equals(otherNoteAddCommand.noteToAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("noteToAdd", noteToAdd)
                .toString();
    }
}
