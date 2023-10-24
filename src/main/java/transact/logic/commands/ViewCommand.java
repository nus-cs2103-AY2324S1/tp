package transact.logic.commands;

import static java.util.Objects.requireNonNull;
import static transact.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static transact.model.Model.PREDICATE_SHOW_ALL_TRANSACTIONS;

import transact.model.Model;
import transact.ui.MainWindow.TabWindow;

/**
 * Lists all persons in the address book to the user.
 */
public class ViewCommand extends Command {
    /**
     * Enum for different types of views.
     */
    public enum ViewType {
        STAFF, TRANSACTION, OVERVIEW
    }

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_SUCCESS_STAFF = "Listed all staff";

    public static final String MESSAGE_SUCCESS_TRANSACTIONS = "Listed all transactions";
    public static final String MESSAGE_SUCCESS_OVERVIEW = "Showed transaction overview";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays list of staff, transactions, or overview of transactions. "
            + "Parameters: staff/s (for staff), transaction/t (for transactions), overview/o (for overview)";

    private final ViewType type;

    public ViewCommand(ViewType type) {
        this.type = type;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        switch (type) {
        case STAFF:
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_SUCCESS_STAFF, TabWindow.ADDRESSBOOK);
        case TRANSACTION:
            model.updateFilteredTransactionList(PREDICATE_SHOW_ALL_TRANSACTIONS);
            return new CommandResult(MESSAGE_SUCCESS_TRANSACTIONS, TabWindow.TRANSACTIONS);
        case OVERVIEW:
            return new CommandResult(MESSAGE_SUCCESS_OVERVIEW, TabWindow.OVERVIEW);
        default:
            return new CommandResult(MESSAGE_USAGE, TabWindow.CURRENT);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewCommand)) {
            return false;
        }

        ViewCommand otherViewCommand = (ViewCommand) other;
        return type == otherViewCommand.type;
    }
}
