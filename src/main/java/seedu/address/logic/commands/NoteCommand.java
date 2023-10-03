package seedu.address.logic.commands;

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

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Executed NoteCommand.");
    }
}
