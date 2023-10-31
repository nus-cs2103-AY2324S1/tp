package transact.logic.commands;

import transact.logic.commands.exceptions.CommandException;
import transact.model.Model;
import transact.ui.MainWindow;

/**
 * Creates Import Transactions command
 */
public class ImportTransactionCommand extends Command {
    public static final String COMMAND_WORD = "importtransactions";
    public static final String MESSAGE_EXPORT_ACKNOWLEDGEMENT = "Importing Transactions as requested ...";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_EXPORT_ACKNOWLEDGEMENT, MainWindow.TabWindow.TRANSACTIONS, false, false, false, false, true);
    }
}
