package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Export class that handles the export functionality
 */
public class ExportCommand extends Command {
    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": exports deck window.\n";

    public static final String SHOWING_EXPORT_MESSAGE = "Opened export window.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert(model != null);
        return new CommandResult(SHOWING_EXPORT_MESSAGE);
    }
}
