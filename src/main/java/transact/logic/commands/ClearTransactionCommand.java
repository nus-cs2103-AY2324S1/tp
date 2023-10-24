package transact.logic.commands;

import static java.util.Objects.requireNonNull;

import transact.model.Model;
import transact.model.TransactionBook;
import transact.ui.MainWindow.TabWindow;

/**
 * Clears the transaction list.
 */
public class ClearTransactionCommand extends Command {

    public static final String COMMAND_WORD = "cleartransaction";
    public static final String MESSAGE_SUCCESS = "Transaction list has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTransactionBook(new TransactionBook());
        return new CommandResult(MESSAGE_SUCCESS, TabWindow.TRANSACTIONS, false, false, false, true);
    }
}

