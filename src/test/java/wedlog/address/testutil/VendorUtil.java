package wedlog.address.testutil;

import static wedlog.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static wedlog.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static wedlog.address.logic.parser.CliSyntax.PREFIX_NAME;
import static wedlog.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static wedlog.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import wedlog.address.logic.commands.VendorEditCommand.EditVendorDescriptor;
import wedlog.address.model.person.Vendor;
import wedlog.address.model.tag.Tag;

/**
 * A utility class for Vendor.
 */
public class VendorUtil {

    /**
     * Returns the part of command string for the given {@code vendor}'s details.
     */
    public static String getVendorDetails(Vendor vendor) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + vendor.getName().fullName + " ");
        sb.append(PREFIX_PHONE + vendor.getPhone().map(p -> p.value).orElse("") + " ");
        sb.append(PREFIX_EMAIL + vendor.getEmail().map(e -> e.value).orElse("") + " ");
        sb.append(PREFIX_ADDRESS + vendor.getAddress().map(a -> a.value).orElse("") + " ");
        vendor.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditVendorDescriptor}'s details.
     */
    public static String getEditVendorDescriptorDetails(EditVendorDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        if (descriptor.isNameEdited()) {
            sb.append(PREFIX_NAME).append(descriptor.getName().fullName).append(" ");
        }
        if (descriptor.isPhoneEdited()) {
            sb.append(PREFIX_PHONE).append(descriptor.getPhone().value).append(" ");
        }
        if (descriptor.isEmailEdited()) {
            sb.append(PREFIX_EMAIL).append(descriptor.getEmail().value).append(" ");
        }
        if (descriptor.isAddressEdited()) {
            sb.append(PREFIX_ADDRESS).append(descriptor.getAddress().value).append(" ");
        }
        if (descriptor.isTagsEdited()) {
            Set<Tag> tags = descriptor.getTags();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
