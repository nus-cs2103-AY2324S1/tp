package seedu.address.model.displayable.buyer;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.Comparator;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.displayable.SortOrder;

/**
 * Represents a comparator used to sort buyers in the address book.
 */
public class BuyerComparator implements Comparator<Buyer> {

    public static final String MESSAGE_INVALID_SORT_ORDER =
            "Buyer comparator only works with ascending and descending sort orders!";
    public static final String MESSAGE_UNSUPPORTED_PREFIX = "Buyer comparator does not support that prefix!";

    private static final BuyerComparator ASCENDING_NAME_COMPARATOR = new BuyerComparator((o1, o2) ->
            o1.getName().fullName.toLowerCase().compareTo(o2.getName().fullName.toLowerCase()));
    private static final BuyerComparator DESCENDING_NAME_COMPARATOR = new BuyerComparator((o1, o2) ->
            o2.getName().fullName.toLowerCase().compareTo(o1.getName().fullName.toLowerCase()));
    private static final BuyerComparator ASCENDING_ADDRESS_COMPARATOR = new BuyerComparator((o1, o2) ->
            o1.getAddress().value.toLowerCase().compareTo(o2.getAddress().value.toLowerCase()));
    private static final BuyerComparator DESCENDING_ADDRESS_COMPARATOR = new BuyerComparator((o1, o2) ->
            o2.getAddress().value.toLowerCase().compareTo(o1.getAddress().value.toLowerCase()));
    private static final BuyerComparator ASCENDING_HOUSE_INFO_COMPARATOR = new BuyerComparator((o1, o2) ->
            o1.getHouseInfo().info.toLowerCase().compareTo(o2.getHouseInfo().info.toLowerCase()));
    private static final BuyerComparator DESCENDING_HOUSE_INFO_COMPARATOR = new BuyerComparator((o1, o2) ->
            o2.getHouseInfo().info.toLowerCase().compareTo(o1.getHouseInfo().info.toLowerCase()));
    private static final BuyerComparator ASCENDING_PRIORITY_COMPARATOR = new BuyerComparator((o1, o2) ->
            o2.getPriority().value.compareTo(o1.getPriority().value));
    private static final BuyerComparator DESCENDING_PRIORITY_COMPARATOR = new BuyerComparator((o1, o2) ->
            o1.getPriority().value.compareTo(o2.getPriority().value));
    private static final BuyerComparator DEFAULT_COMPARATOR = null;

    private final Comparator<Buyer> buyerComparator;

    /**
     * Constructs a BuyerComparator instance.
     *
     * @param comparator A comparator that implements a comparison function to sort buyers by.
     */
    private BuyerComparator(Comparator<Buyer> comparator) {
        this.buyerComparator = comparator;
    }

    /**
     * Returns a BuyerComparator instance that can be passed into a sort method.
     * Sorts buyers by the field specified by the prefix, and by the specified sort order.
     *
     * @param prefix The prefix indicating the field to sort buyers by.
     * @param sortOrder The sort order, which can be either ascending or descending, to sort buyers by.
     * @return A BuyerComparator instance.
     */
    public static BuyerComparator of(Prefix prefix, SortOrder sortOrder) {
        requireNonNull(prefix);
        requireNonNull(sortOrder);

        if (prefix.equals(PREFIX_NAME)) {
            switch (sortOrder.orderType) {
            case ASCENDING:
                return ASCENDING_NAME_COMPARATOR;
            case DESCENDING:
                return DESCENDING_NAME_COMPARATOR;
            default:
                throw new IllegalArgumentException(MESSAGE_INVALID_SORT_ORDER);
            }
        } else if (prefix.equals(PREFIX_ADDRESS)) {
            switch (sortOrder.orderType) {
            case ASCENDING:
                return ASCENDING_ADDRESS_COMPARATOR;
            case DESCENDING:
                return DESCENDING_ADDRESS_COMPARATOR;
            default:
                throw new IllegalArgumentException(MESSAGE_INVALID_SORT_ORDER);
            }
        } else if (prefix.equals(PREFIX_HOUSE_INFO)) {
            switch (sortOrder.orderType) {
            case ASCENDING:
                return ASCENDING_HOUSE_INFO_COMPARATOR;
            case DESCENDING:
                return DESCENDING_HOUSE_INFO_COMPARATOR;
            default:
                throw new IllegalArgumentException(MESSAGE_INVALID_SORT_ORDER);
            }
        } else if (prefix.equals(PREFIX_PRIORITY)) {
            switch (sortOrder.orderType) {
            case ASCENDING:
                return ASCENDING_PRIORITY_COMPARATOR;
            case DESCENDING:
                return DESCENDING_PRIORITY_COMPARATOR;
            default:
                throw new IllegalArgumentException(MESSAGE_INVALID_SORT_ORDER);
            }
        } else {
            throw new IllegalArgumentException(MESSAGE_UNSUPPORTED_PREFIX);
        }
    }

    /**
     * Returns a default BuyerComparator instance, which is null.
     *
     * @return A default BuyerComparator instance, which is null.
     */
    public static BuyerComparator of() {
        return DEFAULT_COMPARATOR;
    }

    @Override
    public int compare(Buyer o1, Buyer o2) {
        return buyerComparator.compare(o1, o2);
    }
}
