package seedu.lovebook.ui;

import seedu.lovebook.logic.commands.CommandResult;
import seedu.lovebook.logic.commands.exceptions.CommandException;
import seedu.lovebook.logic.parser.exceptions.ParseException;

/**
 * A stub class to represent the CommandExecutor.
 */
public class StubCommandExecutor implements CommandBox.CommandExecutor {
    private CommandResult commandResult;
    private CommandException commandException;

    public void setCommandResult(CommandResult commandResult) {
        this.commandResult = commandResult;
    }

    public void setCommandException(CommandException commandException) {
        this.commandException = commandException;
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        if (commandException != null) {
            throw commandException;
        } else {
            return commandResult;
        }
    }
}
