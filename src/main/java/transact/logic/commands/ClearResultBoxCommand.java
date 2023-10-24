package transact.logic.commands;

import static java.util.Objects.requireNonNull;

import transact.model.Model;
import transact.ui.MainWindow.TabWindow;

/**
 * Clears the address book.
 */
public class ClearResultBoxCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Result Box is cleared";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, TabWindow.CURRENT, false, false, true);
    }
}
