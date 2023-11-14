package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_DUE;

import seedu.address.logic.commands.finance.AddCommissionCommand;
import seedu.address.logic.commands.finance.AddExpenseCommand;
import seedu.address.model.finance.Commission;
import seedu.address.model.finance.Expense;
import seedu.address.model.finance.Finance;

/**
 * A utility class for Finance.
 */
public class FinanceUtil {

    /**
     * Returns an add command string for adding the {@code commission}.
     */
    public static String getAddCommissionCommand(Commission commission) {
        return AddCommissionCommand.COMMAND_WORD + " " + getFinanceDetails(commission);
    }

    /**
     * Returns an add command string for adding the {@code expense}.
     */
    public static String getAddExpenseCommand(Expense expense) {
        return AddExpenseCommand.COMMAND_WORD + " " + getFinanceDetails(expense);
    }

    /**
     * Returns the part of command string for the given {@code finance}'s details.
     */
    public static String getFinanceDetails(Finance finance) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_AMOUNT + finance.getAmount().value + " ");
        sb.append(PREFIX_CLIENT + finance.getClient().getName().fullName + " ");
        sb.append(PREFIX_DESCRIPTION + finance.getDescription().value + " ");
        sb.append(PREFIX_TIME_DUE + finance.getTimeDue().getValue() + " ");
        return sb.toString();
    }
}
