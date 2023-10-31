package transact.logic.commands;

import static java.util.Objects.requireNonNull;

import transact.model.Model;
import transact.ui.MainWindow.TabWindow;


/**
 * Clears the transaction list filter.
 */
public class ClearFilterCommand extends Command {

    public static final String COMMAND_WORD = "clearfilter";
    public static final String MESSAGE_SUCCESS = "Transaction filter has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTransactionList(Model.PREDICATE_SHOW_ALL_TRANSACTIONS);

        return new CommandResult(MESSAGE_SUCCESS, TabWindow.TRANSACTIONS);
    }
}
