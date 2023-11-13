package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.SPECIALIST_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditSpecialistDescriptor;
import seedu.address.model.person.Specialist;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Specialist.
 */
public class SpecialistUtil {

    /**
     * Returns an add command string for adding the {@code specialist}.
     */
    public static String getAddCommand(Specialist specialist) {
        return AddCommand.COMMAND_WORD + " " + SPECIALIST_TAG + " " + getSpecialistDetails(specialist);
    }

    /**
     * Returns the part of command string for the given {@code specialist}'s details.
     */
    public static String getSpecialistDetails(Specialist specialist) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + specialist.getName().fullName + " ");
        sb.append(PREFIX_PHONE + specialist.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + specialist.getEmail().value + " ");
        sb.append(PREFIX_LOCATION + specialist.getLocation().value + " ");
        specialist.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        sb.append(PREFIX_SPECIALTY + specialist.getSpecialty().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditSpecialistDescriptor}'s details.
     */
    public static String getEditSpecialistDescriptorDetails(EditSpecialistDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getLocation().ifPresent(address -> sb.append(PREFIX_LOCATION).append(address.value).append(" "));
        descriptor.getSpecialty().ifPresent(specialty -> sb.append(PREFIX_SPECIALTY).append(specialty.value)
                .append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
        }
        return sb.toString();
    }
}
