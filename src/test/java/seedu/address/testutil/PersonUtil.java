package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NATIONALITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOCIAL_MEDIA_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.socialmedialink.SocialMediaLink;
import seedu.address.model.tutorial.Tutorial;

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
        sb.append(PREFIX_MAJOR + person.getMajor().value + " ");
        sb.append(PREFIX_YEAR + person.getYear().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_DESCRIPTION + person.getDescription().value + " ");
        person.getTutorials().stream().forEach(
                t -> sb.append(PREFIX_TUTORIAL + t.value + " ")
        );
        person.getSocialMediaLinks().stream().forEach(
                s -> sb.append(PREFIX_SOCIAL_MEDIA_LINK + s.socialMediaLink + " ")
        );
        sb.append(PREFIX_NATIONALITY + person.getNationality().value + " ");
        sb.append(PREFIX_GENDER + person.getGender().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getMajor().ifPresent(major -> sb.append(PREFIX_MAJOR).append(major.value).append(" "));
        descriptor.getYear().ifPresent(year -> sb.append(PREFIX_YEAR).append(year.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getDescription().ifPresent(description -> sb.append(PREFIX_DESCRIPTION).append(description.value)
                .append(" "));
        if (descriptor.getTutorials().isPresent()) {
            Set<Tutorial> tutorials = descriptor.getTutorials().get();
            if (tutorials.isEmpty()) {
                sb.append(PREFIX_TUTORIAL).append(" ");
            } else {
                tutorials.forEach(t -> sb.append(PREFIX_TUTORIAL).append(t.value).append(" "));
            }
        }
        if (descriptor.getSocialMediaLinks().isPresent()) {
            Set<SocialMediaLink> socialMediaLinks = descriptor.getSocialMediaLinks().get();
            if (socialMediaLinks.isEmpty()) {
                sb.append(PREFIX_SOCIAL_MEDIA_LINK).append(" ");
            } else {
                socialMediaLinks.forEach(s -> sb.append(PREFIX_SOCIAL_MEDIA_LINK).append(s.socialMediaLink)
                        .append(" "));
            }
        }
        descriptor.getNationality().ifPresent(nationality -> sb.append(PREFIX_NATIONALITY)
                                                .append(nationality.value).append(" "));
        descriptor.getGender().ifPresent(gender -> sb.append(PREFIX_GENDER).append(gender.value).append(" "));
        return sb.toString();
    }
}
