package seedu.address.logic;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.delivery.Delivery;
import seedu.address.model.person.Customer;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_DELIVERY_DISPLAYED_INDEX = "The delivery index provided is invalid";
    public static final String MESSAGE_INVALID_DELIVERY_ID = "The delivery ID provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS = "Multiple values specified for the following"
        + "single-valued field(s): ";

    public static final String MESSAGE_USER_NOT_AUTHENTICATED = "Access denied! You are currently not logged in.";

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
     * Formats the {@code person} for display to the user.
     */
    public static String format(Customer customer) {
        final StringBuilder builder = new StringBuilder();
        builder.append(customer.getName())
            .append("; Phone: ")
            .append(customer.getPhone())
            .append("; Email: ")
            .append(customer.getEmail())
            .append("; Address: ")
            .append(customer.getAddress())
            .append("; Tags: ");
        customer.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code person} for display to the delivery.
     */
    public static String format(Delivery delivery) {
        final StringBuilder builder = new StringBuilder();
        builder.append(String.format("[%d]", delivery.getDeliveryId()))
            .append(String.format(" %s", delivery.getName()))
            .append(String.format("\n %s", delivery.getStatus().toString()))
            .append(String.format("\n Customer: %s", delivery.getCustomer().getName()))
            .append(String.format("\n Customer Id: %d", delivery.getCustomer().getCustomerId()))
            .append(String.format("\n Ordered On: %s", delivery.getOrderDate().toString()))
            .append(String.format("\n Delivered On: %s", delivery.getDeliveryDate().toString()))
            .append(Optional.ofNullable(delivery.getNote())
                .map(n -> String.format("\n Note:%s", n)).orElse(""));

        return builder.toString();
    }
}
