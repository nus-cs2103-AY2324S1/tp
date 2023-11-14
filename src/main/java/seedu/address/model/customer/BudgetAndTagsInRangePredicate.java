package seedu.address.model.customer;

import static java.util.Objects.isNull;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Customer}'s {@code Budget}
 * and/or minimum one {@code Tags} are in range of the specified budget and/or tags.
 */
public class BudgetAndTagsInRangePredicate implements Predicate<Customer> {
    private final Budget budget;
    private final Set<Tag> tags;

    /**
     * Constructs a {@code BudgetAndTagsInRangePredicate}.
     *
     * @param budget the specified budget if any
     * @param tags the specified tags if any
     */
    public BudgetAndTagsInRangePredicate(Budget budget, Set<Tag> tags) {
        this.budget = budget;
        this.tags = tags;
    }

    @Override
    public boolean test(Customer customer) {
        return (isNull(tags) || customer.getTags().containsAll(tags))
                && customer.getBudget().isInRangeBudget(budget);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BudgetAndTagsInRangePredicate)) {
            return false;
        }

        BudgetAndTagsInRangePredicate otherBudgetAndTagsInRangePredicate = (BudgetAndTagsInRangePredicate) other;
        return budget.equals(otherBudgetAndTagsInRangePredicate.budget)
                && tags.equals(otherBudgetAndTagsInRangePredicate.tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("budget", budget)
                .add("tags", tags).toString();
    }
}
