package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Balance;
import seedu.address.model.person.Person;

/**
 * Pays the specified amount to the specified person.
 */
public class PayCommand extends Command {

    public static final String COMMAND_WORD = "pay";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Pays the specified amount to the specified person.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "AMOUNT (must be a positive dollar amount, with maximum precision of 2 d.p. (cents))\n"
            + "Example: " + COMMAND_WORD + " 1 2.50";

    public static final String MESSAGE_SUCCESS = "Recorded payment of %1$s to %2$s";

    private final Index index;
    private final Balance amount;

    /**
     * Constructs a {@code PayCommand}.
     * @param index of the person in the filtered person list to edit the remark
     * @param amount details of the person to be updated to
     */
    public PayCommand(Index index, Balance amount) {
        requireAllNonNull(index, amount);
        this.index = index;
        this.amount = amount;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person p = lastShownList.get(index.getZeroBased());
        if (p.getBalance().wouldExceedBalanceLimit(amount)) {
            throw new CommandException(Balance.MESSAGE_BALANCE_LIMIT_EXCEEDED);
        }
        p.pay(amount);
        model.setPerson(p, p);
        return new CommandResult(String.format(MESSAGE_SUCCESS, amount.toAbsoluteDollarString(),
                Messages.format(p)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PayCommand)) {
            return false;
        }

        PayCommand e = (PayCommand) other;
        return index.equals(e.index)
                && amount.equals(e.amount);
    }

}
