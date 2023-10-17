package transact.logic.commands;

import static java.util.Objects.requireNonNull;

import transact.commons.util.ToStringBuilder;
import transact.logic.Messages;
import transact.logic.commands.exceptions.CommandException;
import transact.model.Model;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.TransactionId;
import transact.ui.MainWindow.TabWindow;

/**
 * Deletes a transaction identified using it's displayed index from the
 * transaction book.
 */
public class DeleteTransactionCommand extends Command {

    public static final String COMMAND_WORD = "del";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes transaction identified by the index number used in the transaction list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TRANSACTION_SUCCESS = "Deleted Transaction: %1$s";
    public static final String MESSAGE_DELETE_TRANSACTION_FAILURE = "Cannot find transaction with id: %d";

    private final Integer transactionId;

    public DeleteTransactionCommand(Integer transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        for (TransactionId id : model.getTransactionBook().getTransactionMap().keySet()) {
            if (id.getValue() == transactionId) {
                Transaction deletedTransaction = model.deleteTransaction(id);
                return new CommandResult(
                        String.format(MESSAGE_DELETE_TRANSACTION_SUCCESS, Messages.format(deletedTransaction)),
                        TabWindow.TRANSACTIONS);
            }
        }

        return new CommandResult(
                String.format(MESSAGE_DELETE_TRANSACTION_FAILURE, transactionId),
                TabWindow.TRANSACTIONS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTransactionCommand)) {
            return false;
        }

        DeleteTransactionCommand otherDeleteTransactionCommand = (DeleteTransactionCommand) other;
        return transactionId.equals(otherDeleteTransactionCommand.transactionId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("transactionId", transactionId)
                .toString();
    }
}
