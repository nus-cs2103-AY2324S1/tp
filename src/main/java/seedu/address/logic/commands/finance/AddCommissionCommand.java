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
import seedu.address.model.finance.Commission;
import seedu.address.model.person.Person;

/**
 * Adds a Commission to the app.
 */
public class AddCommissionCommand extends Command {

    public static final String COMMAND_WORD = "add-c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": adds a commission to the Finance Tab.\n"
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_AMOUNT + "AMOUNT "
            + PREFIX_CLIENT + "CLIENT "
            + "[" + PREFIX_TIME_DUE + "TIME] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "Wedding photo shoot "
            + PREFIX_AMOUNT + "1000 "
            + PREFIX_CLIENT + "John Doe "
            + PREFIX_TIME_DUE + "tomorrow";

    public static final String MESSAGE_SUCCESS = "New commission added: %1$s";

    private Commission toAdd;

    public AddCommissionCommand(Commission commission) {
        this.toAdd = commission;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person client = this.toAdd.getClient();
        if (!model.isValidClient(client)) {
            throw new CommandException(Messages.MESSAGE_CLIENT_DOES_NOT_EXIST);
        }

        toAdd.setMatchedClientInstance(model.getMatchedClient(client));

        model.addCommission(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatFinance(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommissionCommand)) {
            return false;
        }

        AddCommissionCommand otherAddCommissionCommand = (AddCommissionCommand) other;
        return toAdd.isSameCommission(otherAddCommissionCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }

}
