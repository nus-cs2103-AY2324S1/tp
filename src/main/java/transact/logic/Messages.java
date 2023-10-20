package transact.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import transact.logic.parser.Prefix;
import transact.model.person.Person;
import transact.model.transaction.Transaction;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_TRANSACTION_ID = "Cannot find transaction with id %d";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS = "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_INVALID_PERSON_ID = "Cannot find person with id: %d";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields = Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Address: ")
                .append(person.getAddress())
                .append("; Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code transaction} for display to the user.
     */

    public static String format(Transaction transaction) {
        final StringBuilder builder = new StringBuilder();
        builder.append(transaction.getTransactionId())
                .append("; TransactionType: ")
                .append(transaction.getTransactionType())
                .append("; Description: ")
                .append(transaction.getDescription())
                .append("; Amount: ")
                .append(transaction.getAmount())
                .append("; Date: ")
                .append(transaction.getDate());
        // .append("; Staff: ")
        // .append(transaction.getPerson().getName());
        return builder.toString();
    }
}
