package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Contact;
import seedu.address.model.person.Course;
import seedu.address.model.person.Person;
import seedu.address.model.person.Role;
import seedu.address.model.person.Tutorial;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons found!";
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
        builder.append("Name: ");
        builder.append(person.getName());

        builder.append("; Role: ");
        String roleList = person.getRoles().stream()
                .map(Role::toString) // Assuming you have a suitable toString method for Contact
                .collect(Collectors.joining(", "));
        builder.append(roleList);

        builder.append("; Contacts: ");
        String contactList = person.getContacts().stream()
                .map(Contact::toString) // Assuming you have a suitable toString method for Contact
                .collect(Collectors.joining(", "));
        builder.append(contactList);

        builder.append("; Courses: ");
        //        person.getCourses().forEach(builder::append);
        String courseList = person.getCourses().stream()
                .map(Course::getCourseName)
                .collect(Collectors.joining(", "));
        builder.append(courseList);

        builder.append("; Tutorials: ");
        String tutorialList = person.getTutorials().stream()
                .map(Tutorial::toString) // Assuming you have a suitable toString method for Tutorial
                .collect(Collectors.joining(", "));
        builder.append(tutorialList);
        return builder.toString();
    }

}
