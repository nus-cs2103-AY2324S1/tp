package seedu.letsgethired.logic.commands;

import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_NOTE_DELETE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_NOTE_INSERT;

import seedu.letsgethired.logic.commands.exceptions.CommandException;
import seedu.letsgethired.model.Model;

/**
 * Adds or Deletes a note to an existing intern application in the interntracker.
 */
public abstract class NoteCommand extends Command {

    public static final String COMMAND_WORD = "note";

    public static final String INVALID_NOTE_DISPLAYED_INDEX =
            "The note index provided is invalid";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add or Delete a note to the internship application "
            + "identified by the index number used in the last intern application listing.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NOTE_INSERT + "NOTE\n"
            + "Add Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NOTE_INSERT + "John Street is the leading market maker in the APAC region\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NOTE_DELETE + "NOTE_INDEX (must be a positive integer)\n"
            + "Delete Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NOTE_DELETE + "1";

    public static final String DUAL_NOTE_ERROR = "You cannot perform the action of deleting and adding a note at once";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public abstract boolean equals(Object other);
}
