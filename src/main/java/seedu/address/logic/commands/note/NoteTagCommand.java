package seedu.address.logic.commands.note;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.messages.Messages;
import seedu.address.model.SeplendidModel;
import seedu.address.model.note.Note;
import seedu.address.model.tag.Tag;
import seedu.address.seplendidui.UiUtil;

/**
 * Adds a tag to a note to the NoteList.
 */
public class NoteTagCommand extends NoteCommand {
    public static final String MESSAGE_NONEXISTENT_NOTE = "Note not found, please put a valid index.";
    public static final String MESSAGE_DUPLICATE_TAGS = "Note cannot have duplicated tags.";
    public static final String ACTION_WORD = "tag";
    public static final String MESSAGE_SUCCESS = "Note tagged: %1$s";
    private final Integer noteIndexToUpdate;
    private final Tag addTag;

    /**
     * Creates a NoteTagCommand to update the specified {@code Note}
     *
     * @param noteIndexToUpdate The index of Note to be updated into Storage.
     * @param addTag The tag to be added to the note.
     */
    public NoteTagCommand(Integer noteIndexToUpdate, Tag addTag) {
        super();
        requireNonNull(noteIndexToUpdate);
        requireNonNull(addTag);
        this.noteIndexToUpdate = noteIndexToUpdate;
        this.addTag = addTag;
    }

    @Override
    public CommandResult execute(SeplendidModel seplendidModel) throws CommandException {
        requireNonNull(seplendidModel);
        int noteListSize = seplendidModel.getNoteCatalogue().getNoteList().size();
        if (this.noteIndexToUpdate <= -1 || noteListSize < this.noteIndexToUpdate) {
            throw new CommandException(MESSAGE_NONEXISTENT_NOTE);
        }
        Note oldNote = seplendidModel.getNoteCatalogue().getNoteList().get(this.noteIndexToUpdate - 1);
        if (oldNote.getTags().contains(addTag)) {
            throw new CommandException(MESSAGE_DUPLICATE_TAGS);
        }
        oldNote.getTags().add(addTag);
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
        if (!(other instanceof NoteTagCommand)) {
            return false;
        }

        NoteTagCommand otherNoteTagCommand = (NoteTagCommand) other;
        return noteIndexToUpdate.equals(otherNoteTagCommand.noteIndexToUpdate)
                && addTag.equals(otherNoteTagCommand.addTag);
    }

    public Integer getNoteIndexToUpdate() {
        return noteIndexToUpdate;
    }

    public Tag getAddTag() {
        return addTag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("noteToUpdate", noteIndexToUpdate)
                .add("tag", addTag)
                .toString();
    }
}

