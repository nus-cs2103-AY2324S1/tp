package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddPropertyCommand;
import seedu.address.logic.commands.EditPropertyCommand;
import seedu.address.model.property.Property;
import seedu.address.model.tag.Tag;



/**
 * A utility class for Property.
 */
public class PropertyUtil {

    /**
     * Returns an add command string for adding the {@code Property}.
     */
    public static String getAddCommand(Property property) {
        return AddPropertyCommand.COMMAND_WORD + " " + getPropertyDetails(property);
    }

    /**
     * Returns the part of command string for the given {@code Property}'s details.
     */
    public static String getPropertyDetails(Property property) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + property.getName().fullName + " ");
        sb.append(PREFIX_PHONE + property.getPhone().value + " ");
        sb.append(PREFIX_ADDRESS + property.getAddress().value + " ");
        sb.append(PREFIX_PRICE + property.getPrice().value + " ");
        property.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }
    /**
     * Returns the part of command string for the given {@code EditPropertyDescriptor}'s details.
     */
    public static String getEditPropertyDescriptorDetails(EditPropertyCommand.EditPropertyDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getPrice().ifPresent(price -> sb.append(PREFIX_PRICE).append(price.value).append(" "));
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
