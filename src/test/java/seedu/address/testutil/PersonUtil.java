package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.musician.Musician;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Musician.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code musician}.
     */
    public static String getAddCommand(Musician musician) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(musician);
    }

    /**
     * Returns the part of command string for the given {@code musician}'s details.
     */
    public static String getPersonDetails(Musician musician) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + musician.getName().fullName + " ");
        sb.append(PREFIX_PHONE + musician.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + musician.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + musician.getAddress().value + " ");
        musician.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
