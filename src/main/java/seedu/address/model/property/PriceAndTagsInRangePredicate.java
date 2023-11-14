package seedu.address.model.property;

import static java.util.Objects.isNull;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Property}'s {@code Price} and/or {@code Tags} are in range of the specified price and/or tags.
 */
public class PriceAndTagsInRangePredicate implements Predicate<Property> {
    private final Price price;
    private final Set<Tag> tags;

    /**
     * Constructs a {@code PriceAndTagsInRangePredicate}.
     *
     * @param price the specified price if any
     * @param tags the specified tags if any
     */
    public PriceAndTagsInRangePredicate(Price price, Set<Tag> tags) {
        this.price = price;
        this.tags = tags;
    }

    @Override
    public boolean test(Property property) {
        return (isNull(tags) || property.getTags().containsAll(tags))
                && property.getPrice().isInRangePrice(price);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PriceAndTagsInRangePredicate)) {
            return false;
        }

        PriceAndTagsInRangePredicate otherBudgetAndTagsInRangePredicate = (PriceAndTagsInRangePredicate) other;
        return price.equals(otherBudgetAndTagsInRangePredicate.price)
                && tags.equals(otherBudgetAndTagsInRangePredicate.tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("price", price)
                .add("tags", tags).toString();
    }
}
