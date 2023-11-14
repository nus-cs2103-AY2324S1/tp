package seedu.address.logic.commands.note;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.messages.Messages;
import seedu.address.model.SeplendidModel;
import seedu.address.model.note.Content;
import seedu.address.model.note.Note;
import seedu.address.seplendidui.UiUtil;

/**
 * Updates the content a note to the NoteList.
 */
public class NoteUpdateCommand extends NoteCommand {
    public static final String MESSAGE_NONEXISTENT_NOTE = "Note not found, please put a valid index.";
    public static final String ACTION_WORD = "update";
    public static final String MESSAGE_SUCCESS = "Note updated: %1$s";
    private final Integer noteIndexToUpdate;
    private final Content updateContent;

    /**
     * Creates a NoteUpdateCommand to update the specified {@code Note}
     *
     * @param noteIndexToUpdate The Index of Note to be updated into Storage.
     * @param updateContent     The updated content of the note
     */
    public NoteUpdateCommand(Integer noteIndexToUpdate, Content updateContent) {
        super();
        requireNonNull(noteIndexToUpdate);
        requireNonNull(updateContent);
        this.noteIndexToUpdate = noteIndexToUpdate;
        this.updateContent = updateContent;
    }

    @Override
    public CommandResult execute(SeplendidModel seplendidModel) throws CommandException {
        requireNonNull(seplendidModel);
        int noteListSize = seplendidModel.getNoteCatalogue().getNoteList().size();
        if (this.noteIndexToUpdate <= -1 || noteListSize < this.noteIndexToUpdate) {
            throw new CommandException(MESSAGE_NONEXISTENT_NOTE);
        }
        Note oldNote = seplendidModel.getNoteCatalogue().getNoteList().get(this.noteIndexToUpdate - 1);
        Note newNote = new Note(this.updateContent, oldNote.getTags(), oldNote.getIndex());
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
        if (!(other instanceof NoteUpdateCommand)) {
            return false;
        }

        NoteUpdateCommand otherNoteDeleteCommand = (NoteUpdateCommand) other;
        return noteIndexToUpdate.equals(otherNoteDeleteCommand.noteIndexToUpdate)
                && updateContent.equals(otherNoteDeleteCommand.updateContent);
    }

    public Integer getNoteIndexToUpdate() {
        return noteIndexToUpdate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("noteToUpdate", noteIndexToUpdate)
                .add("content", updateContent)
                .toString();
    }
}
