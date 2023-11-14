package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.event.Event;
import seedu.address.model.finance.Finance;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_EVENTS_LISTED_OVERVIEW = "%1$d events listed!";
    public static final String MESSAGE_FINANCE_LISTED_OVERVIEW = "%1$d finances listed!";

    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_INVALID_DATE_TIME = "Invalid date-time duration! "
            + "End time cannot be before start time";
    public static final String MESSAGE_INVALID_EVENT_DISPLAYED_INDEX = "The event index provided is invalid";
    public static final String MESSAGE_INVALID_FINANCE_DISPLAYED_INDEX = "The finance index provided is invalid";
    public static final String MESSAGE_CLIENT_DOES_NOT_EXIST = "Client tagged does not exist in your contacts";
    public static final String MESSAGE_UNABLE_TO_EDIT_CLIENT = "Unable to remove client for commissions, "
            + "please edit with a different client.";


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
    public static String formatPerson(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName().fullName)
                .append("; Phone: ")
                .append(person.getPhone().value)
                .append("; Email: ")
                .append(person.getEmail().value)
                .append("; Address: ")
                .append(person.getAddress().value)
                .append("; Company: ")
                .append(person.getCompany().value)
                .append("; TelegramName: ")
                .append(person.getTelegramName().value);
        return builder.toString();
    }

    /**
     * Formats the {@code Event} for display to the user.
     */
    public static String format(Event event) {
        final StringBuilder builder = new StringBuilder();
        builder.append(event.getEventName())
                .append("; Start: ")
                .append(event.getDuration().getTimeStartValue())
                .append("; End: ")
                .append(event.getDuration().getTimeEndValue())
                .append("; Clients: ")
                .append(event.getClientNames())
                .append("; Location: ")
                .append(event.getLocation())
                .append("; Description: ")
                .append(event.getDescription());
        return builder.toString();
    }

    /**
     * Formats the {@code finance entry} for display to the user.
     */
    public static String formatFinance(Finance finance) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Amount: ")
                .append(finance.getAmount())
                .append("; Client: ")
                .append(finance.getClientName())
                .append("; Description: ")
                .append(finance.getDescription())
                .append("; Time: ")
                .append(finance.getTimeDue());
        return builder.toString();
    }
}
