package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_HIDE_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_HIDE_ALL_TRANSACTIONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TRANSACTIONS;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ViewCommand extends Command {
    /**
     * Enum for different types of views.
     */
    public enum ViewType {
        STAFF,
        TRANSACTION
    }

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_SUCCESS_STAFF = "Listed all staff";

    public static final String MESSAGE_SUCCESS_TRANSACTIONS = "Listed all transactions";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays list of staff or transactions. "
            + "Parameters: staff/transaction";

    private final ViewType type;

    public ViewCommand(ViewType type) {
        this.type = type;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (type == ViewType.TRANSACTION) {
            model.updateFilteredPersonList(PREDICATE_HIDE_ALL_PERSONS);
            model.updateFilteredTransactionList(PREDICATE_SHOW_ALL_TRANSACTIONS);
            return new CommandResult(MESSAGE_SUCCESS_TRANSACTIONS);
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredTransactionList(PREDICATE_HIDE_ALL_TRANSACTIONS);
        return new CommandResult(MESSAGE_SUCCESS_STAFF);
    }
}
