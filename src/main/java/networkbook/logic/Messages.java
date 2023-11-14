package networkbook.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import networkbook.logic.parser.Prefix;
import networkbook.model.person.Graduation;
import networkbook.model.person.Person;
import networkbook.model.person.Priority;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_EXACTLY_ONE_FIELD = "One and only one field must be specified here.";
    public static final String MESSAGE_DUPLICATE_PREFIX = "The same field cannot be specified twice here.";
    public static final String MESSAGE_INDEX_MUST_BE_PRESENT =
            "Since %s is present, one and only one /index prefix must be present.";
    public static final String MESSAGE_INDEX_CANNOT_BE_PRESENT = "/index prefix should not be indicated here.";
    public static final String MESSAGE_VALUE_CANNOT_BE_PRESENT = "No value should be specified after the %s prefix.";
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_CONTACT_NAME = "Invalid name provided for contact to create!";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX =
            "The person at index %d does not have %s at index %d";
    public static final String MESSAGE_DELETE_EMPTY_SINGLE_VALUED_FIELD =
            "The person at index %d does not have %s";
    public static final String MESSAGE_PERSONS_SORTED_OVERVIEW = "Here is your sorted list of contacts:"
            + "\n(%1$d persons listed)";
    public static final String MESSAGE_DUPLICATE_SINGLE_VALUED_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_SINGLE_VALUED_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phones: ").append(person.getPhones())
                .append("; Emails: ").append(person.getEmails())
                .append("; Links: ").append(person.getLinks());
        person.getGraduation().ifPresent((Graduation g) -> {
            builder.append("; Graduation: ");
            builder.append(g.getFullString());
        });
        builder.append("; Courses: ").append(person.getCourses());
        builder.append("; Specialisations ").append(person.getSpecialisations());
        builder.append("; Tags: ");
        person.getTags().forEach(builder::append);
        person.getPriority().ifPresent((Priority p) -> {
            builder.append("; Priority: ");
            builder.append(p);
        });
        return builder.toString();
    }

}
