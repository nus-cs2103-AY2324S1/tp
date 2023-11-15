package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.displayable.buyer.Buyer;
import seedu.address.model.displayable.seller.Seller;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_BUYER_DISPLAYED_INDEX = "The buyer index provided is higher than "
            + "the last number in the list!";
    public static final String MESSAGE_INVALID_SELLER_DISPLAYED_INDEX = "The seller index provided is higher than "
            + "the last number in the list!";
    public static final String MESSAGE_SIMILAR_BUYER = "The buyer is similar to one of the buyers in the list!";
    public static final String MESSAGE_SIMILAR_SELLER = "The seller is similar to one of the sellers in the list!";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%d buyer(s) and %d seller(s) listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_POTENTIAL_DUPLICATE_BUYER = "This seller potentially also exists in the"
            + " buyer list: If so, please verify that their contact information is the same";
    public static final String MESSAGE_POTENTIAL_DUPLICATE_SELLER = "This buyer potentially also exists in the"
            + " seller list: If so, please verify that their contact information is the same";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code Buyer} for display to the user.
     */
    public static String format(Buyer buyer) {
        final StringBuilder builder = new StringBuilder();
        builder.append(buyer.getName())
                .append("; Phone: ")
                .append(buyer.getPhone())
                .append("; Email: ")
                .append(buyer.getEmail())
                .append("; Address: ")
                .append(buyer.getAddress())
                .append("; House Info: ")
                .append(buyer.getHouseInfo())
                .append("; Priority: ")
                .append(buyer.getPriority())
                .append("; Tags: ");
        buyer.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code Seller} for display to the user.
     */
    public static String format(Seller seller) {
        final StringBuilder builder = new StringBuilder();
        builder.append(seller.getName())
                .append("; Phone: ")
                .append(seller.getPhone())
                .append("; Email: ")
                .append(seller.getEmail())
                .append("; Address: ")
                .append(seller.getAddress())
                .append("; Selling Address: ")
                .append(seller.getSellingAddress())
                .append("; House Info: ")
                .append(seller.getHouseInfo())
                .append("; Priority: ")
                .append(seller.getPriority())
                .append("; Tags: ");
        seller.getTags().forEach(builder::append);
        return builder.toString();
    }
}
