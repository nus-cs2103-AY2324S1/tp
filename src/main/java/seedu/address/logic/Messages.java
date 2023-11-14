package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_ASSIGNMENT_NAME = "Assignment not found!";
    public static final String MESSAGE_INVALID_ASSIGNMENT_SCORE = "Assignment score should be between 0 to max score ";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_DUPLICATE_MARKINGS = "This week's attendance has already been marked!";
    public static final String MESSAGE_DUPLICATE_UNMARK = "This week's attendance has already been unmarked!";
    public static final String INVALID_GROUP = "Group does not exist!";


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
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone() == null ? " -" : person.getPhone())
                .append("; Email: ")
                .append(person.getEmail() == null ? " -" : person.getEmail())
                .append("; Telegram Handle: ")
                .append(person.getTelegramHandle() == null ? " -" : person.getTelegramHandle())
                .append("; Tags: ");
        person.getTags().forEach(builder::append);
        builder.append("; Comments: ");
        person.getComments().forEach(builder::append);
        return builder.toString();
    }

}
