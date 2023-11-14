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
 * Clears all tags to a note to the NoteList.
 */
public class NoteClearTagCommand extends NoteCommand {
    public static final String MESSAGE_NONEXISTENT_NOTE = "Note not found, please put a valid index.";
    public static final String ACTION_WORD = "cleartag";
    public static final String MESSAGE_SUCCESS = "Removed tag for Note: %1$s";
    private final Integer noteIndexToUpdate;
    /**
     * Creates a NoteClearTagCommand to update the specified {@code Note}
     *
     * @param noteIndexToUpdate The index of Note to be updated into Storage.
     */
    public NoteClearTagCommand(Integer noteIndexToUpdate) {
        super();
        requireNonNull(noteIndexToUpdate);
        this.noteIndexToUpdate = noteIndexToUpdate;
    }

    @Override
    public CommandResult execute(SeplendidModel seplendidModel) throws CommandException {
        requireNonNull(seplendidModel);
        int noteListSize = seplendidModel.getNoteCatalogue().getNoteList().size();
        if (this.noteIndexToUpdate <= -1 || noteListSize < this.noteIndexToUpdate) {
            throw new CommandException(MESSAGE_NONEXISTENT_NOTE);
        }
        Note oldNote = seplendidModel.getNoteCatalogue().getNoteList().get(this.noteIndexToUpdate - 1);
        oldNote.getTags().clear();
        Note newNote = new Note(oldNote.getContent(), oldNote.getTags(), oldNote.getIndex());
        seplendidModel.setNote(oldNote, newNote);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(newNote)),
                UiUtil.ListViewModel.NOTE_LIST);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteClearTagCommand)) {
            return false;
        }

        NoteClearTagCommand otherNoteClearTagCommand = (NoteClearTagCommand) other;
        return noteIndexToUpdate.equals(otherNoteClearTagCommand.noteIndexToUpdate);
    }

    public Integer getNoteIndexToUpdate() {
        return noteIndexToUpdate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("noteToUpdate", noteIndexToUpdate)
                .toString();
    }
}

