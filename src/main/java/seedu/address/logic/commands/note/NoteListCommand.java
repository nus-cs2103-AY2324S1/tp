package seedu.address.logic.commands.note;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.SeplendidModel;
import seedu.address.seplendidui.UiUtil;

/**
 * Lists all notes in the NoteCatalogue.
 */
public class NoteListCommand extends NoteCommand {
    public static final String ACTION_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Listed all notes";

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteListCommand)) {
            return false;
        }

        return true;
    }

    @Override
    public CommandResult execute(SeplendidModel seplendidModel) throws CommandException {
        requireNonNull(seplendidModel);
        seplendidModel.updateFilteredNoteList(SeplendidModel.PREDICATE_SHOW_ALL_NOTES);
        return new CommandResult(MESSAGE_SUCCESS,
                UiUtil.ListViewModel.NOTE_LIST);
    }
}
