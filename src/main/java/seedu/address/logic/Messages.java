package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    // success messages
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_PERSON_VIEWED_OVERVIEW = "Listing details for %s!";
    public static final String MESSAGE_MEETING_VIEWED_OVERVIEW = "Listing details for meeting: %s";
    public static final String MESSAGE_MEETINGS_LISTED_OVERVIEW = "%1$d meetings listed!";

    // command parsing error messages
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!";
    public static final String MESSAGE_DUPLICATE_FIELDS = "Multiple values specified for the following "
            + "single-valued field(s): ";
    public static final String MESSAGE_INVALID_FIELDS = "Invalid parameters provided.";

    // index parsing error messages
    public static final String MESSAGE_INVALID_INDEX_FORMAT = "Index must be a positive integer.";
    public static final String MESSAGE_MISSING_INDEX = "Index is a required parameter.";
    public static final String MESSAGE_TOO_MANY_INDEXES = "Too many index arguments provided.";

    // index value error messages
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid!";
    public static final String MESSAGE_INVALID_MEETING_DISPLAYED_INDEX = "The meeting index provided is invalid!";
    public static final String MESSAGE_INVALID_ATTENDEE_INDEX = "The attendee index provided is invalid!";

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
                .append("; Last Contacted Time: ")
                .append(person.getLastContactedTime())
                .append("; Status: ")
                .append(person.getStatus())
                .append("; Remark: ")
                .append(person.getRemark())
                .append("; Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Meeting meeting) {
        final StringBuilder builder = new StringBuilder();
        builder.append(meeting.getTitle())
                .append("; Location: ")
                .append(meeting.getLocation())
                .append("; Start: ")
                .append(meeting.getStart())
                .append("; End: ")
                .append(meeting.getEnd())
                .append("; Attendees: ");
        meeting.getAttendees().forEach(builder::append);
        builder.append("; Completed: ")
                .append(meeting.getStatus());
        return builder.toString();
    }

}
