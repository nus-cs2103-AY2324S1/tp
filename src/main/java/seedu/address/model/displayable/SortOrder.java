package seedu.address.model.displayable;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.AppUtil;

/**
 * Represents a sort order to sort Displayables in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSortOrder(String)}
 */
public class SortOrder {

    public static final String MESSAGE_CONSTRAINTS =
            "Sort order inputs must be either 'a' for ascending or 'd' for descending";
    public static final String VALIDATION_REGEX = "[ad]";
    public final OrderType orderType;

    /**
     * Represents the types of ordering that buyers and sellers can be sorted by.
     */
    public enum OrderType {
        ASCENDING,
        DESCENDING
    }

    /**
     * Constructs a {@code SortOrder}.
     *
     * @param sortOrder A valid sort order.
     */
    public SortOrder(String sortOrder) {
        requireNonNull(sortOrder);
        AppUtil.validateArgument(isValidSortOrder(sortOrder), MESSAGE_CONSTRAINTS);
        this.orderType = getOrderType(sortOrder);
    }

    /**
     * Returns an OrderType based on the user input.
     */
    public static OrderType getOrderType(String sortOrder) {
        requireNonNull(sortOrder);
        AppUtil.validateArgument(isValidSortOrder(sortOrder), MESSAGE_CONSTRAINTS);
        switch (sortOrder) {
        case ("a"):
            return OrderType.ASCENDING;
        case ("d"):
            return OrderType.DESCENDING;
        default:
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns true if a given string is a valid sort order.
     */
    public static boolean isValidSortOrder(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortOrder)) {
            return false;
        }

        SortOrder otherSortOrder = (SortOrder) other;
        return orderType.equals(otherSortOrder.orderType);
    }
}
