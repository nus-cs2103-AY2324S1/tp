package seedu.letsgethired.logic.commands;

import static seedu.letsgethired.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.letsgethired.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

import java.util.List;

import seedu.letsgethired.commons.core.index.Index;
import seedu.letsgethired.logic.Messages;
import seedu.letsgethired.logic.commands.exceptions.CommandException;
import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.model.application.Note;

/**
 * Adds a note to an existing intern application in the interntracker.
 */
public class NoteInsertCommand extends NoteCommand {

    public static final String MESSAGE_ADD_NOTE_SUCCESS = "Added note to Intern Application";
    private final Index index;
    private final Note note;

    /**
     * @param index of the intern application in the filtered intern application list to edit
     * @param note  note to add to the intern application
     */
    public NoteInsertCommand(Index index, Note note) {
        requireAllNonNull(index, note);

        this.index = index;
        this.note = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<InternApplication> lastShownList = model.getFilteredInternApplicationList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERN_APPLICATION_DISPLAYED_INDEX);
        }

        InternApplication internApplicationToEdit = lastShownList.get(index.getZeroBased());
        InternApplication editedInternApplication = internApplicationToEdit.addNote(note);

        model.setInternApplication(internApplicationToEdit, editedInternApplication);
        model.updateFilteredInternApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);

        return new CommandResult(MESSAGE_ADD_NOTE_SUCCESS, editedInternApplication);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteInsertCommand)) {
            return false;
        }

        NoteInsertCommand e = (NoteInsertCommand) other;
        return index.equals(e.index)
                && note.equals(e.note);
    }
}
