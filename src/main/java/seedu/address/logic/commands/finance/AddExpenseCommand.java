package seedu.address.logic.commands.finance;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_DUE;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.finance.Expense;
import seedu.address.model.person.Person;

/**
 * Adds an Expense to the app.
 */
public class AddExpenseCommand extends Command {

    public static final String COMMAND_WORD = "add-e";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": adds an expense to the Finance Tab.\n"
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_AMOUNT + "AMOUNT "
            + "[" + PREFIX_CLIENT + "CLIENT] "
            + "[" + PREFIX_TIME_DUE + "TIME] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "Lunch "
            + PREFIX_AMOUNT + "50 "
            + PREFIX_CLIENT + "John Doe "
            + PREFIX_TIME_DUE + "today ";

    public static final String MESSAGE_SUCCESS = "New expense added: %1$s";

    private Expense toAdd;

    public AddExpenseCommand(Expense expense) {
        this.toAdd = expense;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person client = this.toAdd.getClient();
        if (client != null) {
            if (!model.isValidClient(client)) {
                throw new CommandException(Messages.MESSAGE_CLIENT_DOES_NOT_EXIST);
            }
            toAdd.setMatchedClientInstance(model.getMatchedClient(client));
        }

        model.addExpense(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatFinance(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddExpenseCommand)) {
            return false;
        }

        AddExpenseCommand otherAddExpenseCommand = (AddExpenseCommand) other;
        return toAdd.isSameExpense(otherAddExpenseCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }

}
