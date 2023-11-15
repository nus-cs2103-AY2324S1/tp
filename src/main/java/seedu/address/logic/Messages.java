package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.item.Item;
import seedu.address.model.item.review.ItemReview;
import seedu.address.model.stall.Stall;
import seedu.address.model.stall.review.StallReview;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_STALL_DISPLAYED_INDEX = "The stall index provided is invalid";
    public static final String MESSAGE_INVALID_ITEM_DISPLAYED_INDEX = "The item index provided is invalid";
    public static final String MESSAGE_STALLS_LISTED_OVERVIEW = "%1$d stalls listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

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
     * Formats the {@code stall} for display to the user.
     */
    public static String format(Stall stall) {
        final StringBuilder builder = new StringBuilder();
        builder.append(stall.getName())
                .append("; Location: ")
                .append(stall.getLocation());
        return builder.toString();
    }

    /**
     * Formats the {@code item} for display to the user.
     */
    public static String format(Item item) {
        final StringBuilder builder = new StringBuilder();
        builder.append(item.getName());
        return builder.toString();
    }

    /**
     * Formats the {@code itemReview} for display to the user.
     */
    public static String format(ItemReview itemReview) {
        final StringBuilder builder = new StringBuilder();
        builder.append(itemReview.getRating())
                .append("; Description: ")
                .append(itemReview.getDescription());
        return builder.toString();
    }

    /**
     * Formats the {@code stallReview} for display to the user.
     */
    public static String format(StallReview stallReview) {
        final StringBuilder builder = new StringBuilder();
        builder.append(stallReview.getRating())
                .append("; Description: ")
                .append(stallReview.getDescription());
        return builder.toString();
    }

}
