package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command for undoing the previous command in the application.
 * Extends the abstract class {@link Command}.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Previous command undid successfully!";
    public static final String MESSAGE_EMPTY = "There's nothing to undo";
    private static final Logger logger = LogsCenter.getLogger(UndoCommand.class);

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.undo();
        logger.info("Successfully undid previous command");
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
