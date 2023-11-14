package seedu.address.model.property;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Property}'s {@code Price}
 * and/or minimal one {@code Tags} are in range of the specified price and/or tags.
 */
public class PriceAndOneTagsPredicate implements Predicate<Property> {
    private final Price price;
    private final Set<Tag> tags;

    /**
     * Constructs a {@code PriceAndOneTagsPredicate}.
     *
     * @param price the specified price if any
     * @param tags the specified tags if any
     */
    public PriceAndOneTagsPredicate(Price price, Set<Tag> tags) {
        this.price = price;
        this.tags = tags;
    }

    @Override
    public boolean test(Property property) {
        if (tags.size() == 0) {
            return property.getPrice().isInRangePrice(price);
        } else {
            return tags.stream().anyMatch(tag -> property.getTags().contains(tag))
                    && property.getPrice().isInRangePrice(price);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PriceAndOneTagsPredicate)) {
            return false;
        }

        PriceAndOneTagsPredicate otherBudgetAndOneTagsPredicate = (PriceAndOneTagsPredicate) other;
        return price.equals(otherBudgetAndOneTagsPredicate.price)
                && tags.equals(otherBudgetAndOneTagsPredicate.tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("price", price)
                .add("tags", tags).toString();
    }
}
