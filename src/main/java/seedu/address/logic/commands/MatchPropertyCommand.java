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
import seedu.address.model.customer.BudgetAndOneTagsPredicate;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.tag.Tag;

/**
 * Match all properties in the property book to the user based on specific tags
 * and/or budget satisfy the customer criteria.
 */
public class MatchPropertyCommand extends Command {
    public static final String COMMAND_WORD = "matchprop";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Match properties from the property book. \n"
            + "Parameters: "
            + "Index" + "\n"
            + "Example: " + COMMAND_WORD + "  2";

    public static final String MESSAGE_FAIL = "There is no property with index ";
    private Index targetIndex;

    /**
     * Creates a MatchPropertyCommand to get all the specified {@code Customer}
     */
    public MatchPropertyCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Property> lastShownList = model.getFilteredPropertyList();

        try {
            Property targetProperty = lastShownList.get(targetIndex.getZeroBased());

            Price price = targetProperty.getPrice();
            Set<Tag> tags = targetProperty.getTags();

            Budget minBudget = price.convertToBudget();
            BudgetAndOneTagsPredicate predicate = new BudgetAndOneTagsPredicate(minBudget, tags);

            model.updateMatchedPropertyList(targetProperty, predicate);

            return new CommandResult(
                    String.format(
                            Messages.MESSAGE_PROPERTIES_MATCH_OVERVIEW + targetIndex.getOneBased(),
                            model.getFilteredCustomerList().size()
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
        if (!(other instanceof MatchPropertyCommand)) {
            return false;
        }

        MatchPropertyCommand otherMatchPropertyCommand = (MatchPropertyCommand) other;
        return targetIndex.equals(otherMatchPropertyCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Index", targetIndex)
                .toString();
    }
}
