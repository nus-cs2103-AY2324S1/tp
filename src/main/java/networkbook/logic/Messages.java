package networkbook.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import networkbook.logic.parser.Prefix;
import networkbook.model.person.Person;
import networkbook.model.person.Priority;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
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
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Emails: ")
                .append(person.getEmails())
                .append("; Links: ")
                .append(person.getLinks())
                .append("; Graduating Year: ")
                .append(person.getGraduatingYear())
                .append("; Course: ")
                .append(person.getCourse())
                .append("; Specialisation: ")
                .append(person.getSpecialisation())
                .append("; Tags: ");
        person.getTags().forEach(builder::append);
        person.getPriority().ifPresent((Priority p) -> {
            builder.append("; Priority: ");
            builder.append(p);
        });
        return builder.toString();
    }

}
