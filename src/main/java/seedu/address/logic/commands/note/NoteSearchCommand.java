package seedu.address.logic.commands.note;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.messages.Messages;
import seedu.address.model.SeplendidModel;
import seedu.address.model.note.NoteTagContainsKeywordsPredicate;
import seedu.address.seplendidui.UiUtil;

/**
 * Finds and lists all notes in SEPlendid whose tags contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class NoteSearchCommand extends NoteCommand {
    public static final String ACTION_WORD = "search";
    public static final String MESSAGE_SUCCESS = "Notes searched: %1$s";
    public static final String MESSAGE_NONEXISTENT_NOTES = "This note does not exist in SEPlendid";

    private final NoteTagContainsKeywordsPredicate predicate;

    public NoteSearchCommand(NoteTagContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(SeplendidModel model) throws CommandException {
        requireNonNull(model);

        model.getSearchNoteIfExists(predicate);

        if (model.getFilteredNoteList().isEmpty()) {
            throw new CommandException(MESSAGE_NONEXISTENT_NOTES);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(model.getFilteredNoteList())),
                UiUtil.ListViewModel.NOTE_LIST);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteSearchCommand)) {
            return false;
        }

        NoteSearchCommand otherNoteSearchCommand = (NoteSearchCommand) other;
        return predicate.equals(otherNoteSearchCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
