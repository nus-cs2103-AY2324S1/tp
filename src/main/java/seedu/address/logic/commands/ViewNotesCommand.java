package seedu.address.logic.commands;

import javafx.application.Platform;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.ui.NotesWindow;

/**
 * Opens a NotesWindow to view notes of the person in the address book.
 */
public class ViewNotesCommand extends Command {

    public static final String COMMAND_WORD = "viewnotes";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Opens a window to view notes of the person "
        + "identified by the index number used in the last person listing. "
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_VIEW_NOTES_SUCCESS = "Opened notes for person: %1$s";

    private final int index;

    /**
     * @param index of the person in the filtered person list whose notes are to be viewed
     */
    public ViewNotesCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (index >= model.getFilteredPersonList().size() || index < 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person = model.getFilteredPersonList().get(index);

        Platform.runLater(() -> {
            NotesWindow notesWindow = new NotesWindow(person);
            notesWindow.show();
        });


        return new CommandResult(String.format(MESSAGE_VIEW_NOTES_SUCCESS, person.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewNotesCommand)) {
            return false;
        }

        ViewNotesCommand otherViewNotesCommand = (ViewNotesCommand) other;
        return index == otherViewNotesCommand.index;
    }
}
