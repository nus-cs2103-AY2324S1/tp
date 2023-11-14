package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.customer.Budget;
import seedu.address.model.customer.Customer;
import seedu.address.model.property.Price;
import seedu.address.model.property.PriceAndOneTagsPredicate;
import seedu.address.model.tag.Tag;

/**
 * Match all properties in the property book to the user based on specific tags
 * and/or budget satisfy the customer criteria.
 */
public class MatchCustomerCommand extends Command {
    public static final String COMMAND_WORD = "matchcust";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Match customers from the address book. \n"
            + "Parameters: "
            + "Index" + "\n"
            + "Example: " + COMMAND_WORD + "  2";

    public static final String MESSAGE_FAIL = "There is no customer with index ";
    private Index targetIndex;

    /**
     * Creates a MatchCustomerCommand to get all the specified {@code Property}
     */
    public MatchCustomerCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Customer> lastShownList = model.getFilteredCustomerList();

        try {
            Customer targetCustomer = lastShownList.get(targetIndex.getZeroBased());
            Budget budget = targetCustomer.getBudget();
            Set<Tag> tags = targetCustomer.getTags();

            Price maxPrice = budget.convertToPrice();
            PriceAndOneTagsPredicate predicate = new PriceAndOneTagsPredicate(maxPrice, tags);

            model.updateMatchedCustomerList(targetCustomer, predicate);

            return new CommandResult(
                    String.format(
                            Messages.MESSAGE_CUSTOMERS_MATCH_OVERVIEW + targetIndex.getOneBased(),
                            model.getFilteredPropertyList().size()
                    )
            );
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_FAIL + targetIndex.getOneBased());
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MatchCustomerCommand)) {
            return false;
        }

        MatchCustomerCommand otherMatchCustomerCommand = (MatchCustomerCommand) other;
        return targetIndex.equals(otherMatchCustomerCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Index", targetIndex)
                .toString();
    }
}
