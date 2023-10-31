package transact.logic.commands;

import transact.logic.commands.exceptions.CommandException;
import transact.model.Model;
import transact.ui.MainWindow;

/**
 * Creates export transaction command
 */
public class ExportTransactionCommand extends Command {

    public static final String COMMAND_WORD = "exporttransactions";
    public static final String MESSAGE_EXPORT_ACKNOWLEDGEMENT = "Exporting Transactions as requested ...";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_EXPORT_ACKNOWLEDGEMENT, MainWindow.TabWindow.TRANSACTIONS, false, false, false, true, false);
    }
}
