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
 * Deletes a note to the NoteList.
 */
public class NoteDeleteCommand extends NoteCommand {
    public static final String MESSAGE_NONEXISTENT_NOTE = "Note not found, please put a valid index.";
    public static final String ACTION_WORD = "delete";
    public static final String MESSAGE_SUCCESS = "Note deleted: %1$s";

    private final Integer noteIndexToDelete;

    /**
     * Creates a NoteAddCommand to add the specified {@code Note}
     *
     * @param note The Note to be added into Storage.
     */
    public NoteDeleteCommand(Integer note) {
        super();
        requireNonNull(note);
        noteIndexToDelete = note;
    }

    @Override
    public CommandResult execute(SeplendidModel seplendidModel) throws CommandException {
        requireNonNull(seplendidModel);

        int noteListSize = seplendidModel.getNoteCatalogue().getNoteList().size();

        if (this.noteIndexToDelete <= -1 || noteListSize < this.noteIndexToDelete) {
            throw new CommandException(MESSAGE_NONEXISTENT_NOTE);
        }

        Note deletedNote = seplendidModel.deleteNote(noteIndexToDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(deletedNote)),
                UiUtil.ListViewModel.NOTE_LIST);

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteDeleteCommand)) {
            return false;
        }

        NoteDeleteCommand otherNoteDeleteCommand = (NoteDeleteCommand) other;
        return noteIndexToDelete.equals(otherNoteDeleteCommand.noteIndexToDelete);
    }

    public Integer getNoteIndexToDelete() {
        return noteIndexToDelete;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("noteToDelete", noteIndexToDelete)
                .toString();
    }
}
