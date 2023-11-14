package seedu.address.logic.commands.finance;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COMMISSIONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EXPENSES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_FINANCES;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.finance.FinanceListType;

/**
 * Lists all Finances to the user.
 */
public class ListFinancesCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS_FINANCES = "Listed all finances";
    public static final String MESSAGE_SUCCESS_EXPENSES = "Listed all expenses";
    public static final String MESSAGE_SUCCESS_COMMISSIONS = "Listed all commissions";

    public static final Object MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all finances, expenses or commissions.\n"
            + "Parameters: [TYPE] (expense for expenses, commission for commissions, leave blank for both)\n"
            + "Example: " + COMMAND_WORD + " finance";

    private final FinanceListType type;

    public ListFinancesCommand(FinanceListType type) {
        this.type = type;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        switch (type) {
        case COMMISSION:
            model.updateFilteredFinanceList(PREDICATE_SHOW_ALL_COMMISSIONS);
            return new CommandResult(MESSAGE_SUCCESS_COMMISSIONS);
        case EXPENSE:
            model.updateFilteredFinanceList(PREDICATE_SHOW_ALL_EXPENSES);
            return new CommandResult(MESSAGE_SUCCESS_EXPENSES);
        default:
            model.updateFilteredFinanceList(PREDICATE_SHOW_ALL_FINANCES);
            return new CommandResult(MESSAGE_SUCCESS_FINANCES);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListFinancesCommand)) {
            return false;
        }

        ListFinancesCommand otherListFinancesCommand = (ListFinancesCommand) other;
        return type.equals(otherListFinancesCommand.type);
    }

}
