package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;



/**
 * Sets the note of an existing contact.
 */
public class NoteCommand extends Command {
    /**
     * Every Command subclass exposes a COMMAND_WORD for use in places like the
     * parser.
     */
    public static final String COMMAND_WORD = "note";

    /**
     * Non-trivial Command subclasses usually expose a MESSAGE_USAGE for use in
     * places like the parser.
     */
    public static final String MESSAGE_USAGE = String.format(
        "%s: Sets the note for the contact of the specified number."
        + "\nArguments: [number] o/[your note]."
        + "\nExample: %s 1 o/CS2103 Prof.",
        NoteCommand.COMMAND_WORD,
        NoteCommand.COMMAND_WORD
    );

    public static final String MESSAGE_TODO = "NoteCommand not implemented yet.";

    @Override
    public CommandResult execute(Model _model) throws CommandException {
        throw new CommandException(MESSAGE_TODO);
    }
}
