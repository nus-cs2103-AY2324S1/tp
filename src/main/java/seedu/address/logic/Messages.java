package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_EMAIL = "The email provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_GROUPS_LISTED_OVERVIEW = "%1$d groups listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating duplicate prefixes.
     *
     * @param duplicatePrefixes An array of Prefixes that are duplicated.
     * @return Error message indicating duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the given {@code person} for display to the user.
     *
     * @param person The Person object to be formatted.
     * @return A formatted string representing the Person's information.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ").append(person.getName())
                .append("; Major: ").append(person.getMajor())
                .append("; Year: ").append(person.getYear())
                .append("; Email: ").append(person.getEmail())
                .append("; Description: ").append(person.getDescription())
                .append("; Tutorial: ").append(person.getTutorials())
                .append("; Social Media: ").append(person.getSocialMediaLinks());
        return builder.toString();
    }

    /**
     * Formats the given {@code group} for display to the user.
     *
     * @param group The Group object to be formatted.
     * @return A formatted string representing the Group's information.
     */
    public static String format(Group group) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Group Number: ").append(group.getNumber())
                .append("; Members: ").append(group.getMembers()); // todo: needs to be edited, formatting is wrong
        return builder.toString();
    }

}
