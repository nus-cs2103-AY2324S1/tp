package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.event.Event;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";

    /** Message for person */
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_AND_EVENTS_LISTED_OVERVIEW = "%d persons and %d events listed!";
    public static final String MESSAGE_PERSON_AND_EVENTS_LISTED_OVERVIEW = "%d person and %d events listed!";
    public static final String MESSAGE_PERSONS_AND_EVENT_LISTED_OVERVIEW = "%d persons and %d event listed!";
    public static final String MESSAGE_PERSON_AND_EVENT_LISTED_OVERVIEW = "%d person and %d event listed!";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_PERSON_LISTED_OVERVIEW = "%1$d person listed!";
    public static final String MESSAGE_EVENTS_LISTED_OVERVIEW = "%1$d events listed!";
    public static final String MESSAGE_EVENT_LISTED_OVERVIEW = "%1$d event listed!";

    public static final String MESSAGE_PERSONS_AND_EVENTS_SHOWN_OVERVIEW =
            "Showing all birthdays and events happening in the next %1$s days:";
    public static final String MESSAGE_DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";

    /** Message for events */
    public static final String MESSAGE_INVALID_EVENT_DISPLAYED_INDEX = "The event index provided is invalid";
    public static final String MESSAGE_INVALID_PERSON = "The person(s) provided(%s) do not exist!";
    public static final String MESSAGE_INVALID_BIRTHDAY = "The person's birthday(%s) is invalid!";
    public static final String MESSAGE_INVALID_UNASSIGN_PERSON =
            "The person(s) provided(%s) have not been assigned to the event!";
    public static final String MESSAGE_INVALID_GROUP = "The group(s) provided(%s) do not exist!";
    public static final String MESSAGE_INVALID_UNASSIGN_GROUP =
            "The group(s) provided(%s) have not been assigned to the event!";

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
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName());
        if (person.hasPhone()) {
            builder.append("; Phone: ")
                    .append(person.getPhone());
        }
        if (person.hasEmail()) {
            builder.append("; Email: ")
                    .append(person.getEmail());
        }
        if (person.hasAddress()) {
            builder.append("; Address: ")
                    .append(person.getAddress());
        }
        if (person.hasBirthday()) {
            builder.append("; Birthday: ")
                    .append(person.getBirthday().forDisplay());
        }
        if (person.hasRemark()) {
            builder.append("; Remark: ")
                    .append(person.getRemark());
        }
        if (person.hasGroups()) {
            builder.append("; Groups: ");
            person.getGroups().forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Formats the {@code event} for display to the user.
     */
    public static String formatEvent(Event event) {
        final StringBuilder builder = new StringBuilder();
        builder.append(event.getName())
                .append("; Date: ")
                .append(event.getStartDate().forDisplay());
        if (event.hasStartTime()) {
            builder.append("; Start Time: ")
                    .append(event.getStartTime().forDisplay());
        }
        if (event.hasEndTime()) {
            builder.append("; End Time: ")
                    .append(event.getEndTime().forDisplay());
        }
        if (!event.getNames().isEmpty()) {
            builder.append("; Persons involved: ");
            for (Name name : event.getNames()) {
                builder.append(name.toString());
                builder.append(", ");
            }
            builder.delete(builder.length() - 2, builder.length()); //removes the last comma
        }
        if (!event.getGroups().isEmpty()) {
            builder.append("; Groups involved: ");
            for (Group group : event.getGroups()) {
                builder.append(group.toString());
                builder.append(", ");
            }
            builder.delete(builder.length() - 2, builder.length()); //removes the last comma
        }
        return builder.toString();
    }
}
