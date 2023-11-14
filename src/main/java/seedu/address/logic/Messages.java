package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

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
    public static final String MESSAGE_NO_PERSON_WITH_NAME_FOUND = "No person with such name found.\n"
            + "Please provide the person's full name as in the existing contactlist.";
    public static final java.lang.String MESSAGE_DUPLICATE_PERSON_IN_GROUP =
            "Error, invalid input entered, unable to put the person into group";
    public static final String MESSAGE_NO_GROUP_WITH_NAME_FOUND = "No group with such name found.\n"
            + "Please provide the group's full name as in the existing contactlist.";



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
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Groups: ");
        person.getGroups().toStream().map(Group::getGroupName).forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code group} for display to the user.
     */
    public static String format(Group group) {
        final StringBuilder builder = new StringBuilder();
        builder.append(group.getGroupName());
        return builder.toString();
    }

    /**
     * Formats the {@code name} for display to the user.
     */
    public static String format(Name personName) {
        //add print function
        return personName.fullName;
    }


}
