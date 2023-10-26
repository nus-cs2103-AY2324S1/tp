package networkbook.testutil;

import networkbook.logic.commands.CreateCommand;
import networkbook.logic.parser.CliSyntax;
import networkbook.model.person.Person;

/**
 * A utility class for Person.
 */
public class PersonUtil {
    //TODO: Add priority
    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return CreateCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(CliSyntax.PREFIX_NAME + " " + person.getName().fullName + " ");
        person.getPhones().stream().forEach(
                e -> sb.append(CliSyntax.PREFIX_PHONE + " " + e.toString() + " ")
        );
        person.getEmails().stream().forEach(
                e -> sb.append(CliSyntax.PREFIX_EMAIL + " " + e.toString() + " ")
        );
        person.getLinks().stream().forEach(
                e -> sb.append(CliSyntax.PREFIX_LINK + " " + e.toString() + " ")
        );
        person.getGraduation().ifPresent(graduation -> sb.append(CliSyntax.PREFIX_GRADUATION)
                .append(" ").append(graduation).append(" "));
        person.getCourses().forEach(
                e -> sb.append(CliSyntax.PREFIX_COURSE + " " + e.toString() + " ")
        );
        person.getSpecialisations().stream().forEach(
                e -> sb.append(CliSyntax.PREFIX_SPECIALISATION + " " + e.toString() + " ")
        );
        person.getTags().stream().forEach(
            s -> sb.append(CliSyntax.PREFIX_TAG + " " + s.getValue() + " ")
        );
        return sb.toString();
    }
}
