package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Contact;
import seedu.address.model.person.Course;
import seedu.address.model.person.Person;
import seedu.address.model.person.Role;
import seedu.address.model.person.Tutorial;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        person.getRoles().stream().forEach(
                s -> sb.append(PREFIX_ROLE + s.toString() + " ")
        );
        person.getContacts().stream().forEach(
                s -> sb.append(PREFIX_CONTACT + s.contact + " ")
        );
        person.getCourses().stream().forEach(
                s -> sb.append(PREFIX_COURSE + s.courseName + " ")
        );
        person.getTutorials().stream().forEach(
                s -> sb.append(PREFIX_TUTORIAL + s.tutorialName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(" ").append(name.fullName).append(" "));
        if (descriptor.getRoles().isPresent()) {
            Set<Role> roles = descriptor.getRoles().get();
            if (roles.isEmpty()) {
                sb.append(PREFIX_ROLE).append(" ");
            } else {
                roles.forEach(s -> sb.append(PREFIX_ROLE).append(" ").append(s.toString()).append(" "));
            }
        }
        if (descriptor.getContacts().isPresent()) {
            Set<Contact> contacts = descriptor.getContacts().get();
            if (contacts.isEmpty()) {
                sb.append(PREFIX_CONTACT).append(" ");
            } else {
                contacts.forEach(s -> sb.append(PREFIX_CONTACT).append(" ").append(s.contact).append(" "));
            }
        }
        if (descriptor.getCourses().isPresent()) {
            Set<Course> courses = descriptor.getCourses().get();
            if (courses.isEmpty()) {
                sb.append(PREFIX_COURSE).append(" ");
            } else {
                courses.forEach(s -> sb.append(PREFIX_COURSE).append(" ").append(s.courseName).append(" "));
            }
        }
        if (descriptor.getTutorials().isPresent()) {
            Set<Tutorial> tutorials = descriptor.getTutorials().get();
            if (tutorials.isEmpty()) {
                sb.append(PREFIX_TUTORIAL).append(" ");
            } else {
                tutorials.forEach(s -> sb.append(PREFIX_TUTORIAL).append(" ").append(s.tutorialName).append(" "));
            }
        }
        return sb.toString();
    }
}
