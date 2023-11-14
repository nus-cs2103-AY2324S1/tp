package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command for redoing the previous command in the application.
 * Extends the abstract class {@link Command}.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_SUCCESS = "Previous command redid successfully!";
    public static final String MESSAGE_EMPTY = "There's nothing to redo";
    private static final Logger logger = LogsCenter.getLogger(RedoCommand.class);

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.redo();
        logger.info("Successfully redid previously undone task");
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
