package seedu.address.model.customer;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Customer}'s {@code Budget} and/or {@code Tags} are in range of the specified budget and/or tags.
 */
public class BudgetAndOneTagsPredicate implements Predicate<Customer> {
    private final Budget budget;
    private final Set<Tag> tags;

    /**
     * Constructs a {@code BudgetAndOneTagsPredicate}.
     *
     * @param budget the specified budget if any
     * @param tags the specified tags if any
     */
    public BudgetAndOneTagsPredicate(Budget budget, Set<Tag> tags) {
        this.budget = budget;
        this.tags = tags;
    }

    @Override
    public boolean test(Customer customer) {
        if (tags.size() == 0) {
            return customer.getBudget().isInRangeBudget(budget);
        } else {
            return tags.stream().anyMatch(tag -> customer.getTags().contains(tag))
                    && customer.getBudget().isInRangeBudget(budget);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BudgetAndOneTagsPredicate)) {
            return false;
        }

        BudgetAndOneTagsPredicate otherBudgetAndOneTagsPredicate = (BudgetAndOneTagsPredicate) other;
        return budget.equals(otherBudgetAndOneTagsPredicate.budget)
                && tags.equals(otherBudgetAndOneTagsPredicate.tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("budget", budget)
                .add("tags", tags).toString();
    }
}
