package transact.testutil;

import static transact.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static transact.logic.parser.CliSyntax.PREFIX_EMAIL;
import static transact.logic.parser.CliSyntax.PREFIX_NAME;
import static transact.logic.parser.CliSyntax.PREFIX_PHONE;
import static transact.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import transact.logic.commands.AddStaffCommand;
import transact.logic.commands.EditStaffCommand.EditPersonDescriptor;
import transact.model.AddressBook;
import transact.model.person.Person;
import transact.model.person.PersonId;
import transact.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddStaffCommand(Person person) {
        return AddStaffCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given
     * {@code EditPersonDescriptor}'s details.
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

    /**
     * Clears the {@code PersonId} used by {@code addressBook} to avoid duplicate IDs when testing
     */
    public static void clearAddressBookPersonIdUniqueness(AddressBook addressBook) {
        for (Person person : addressBook.getPersonList()) {
            PersonId.freeUsedPersonIds(person.getPersonId().value);
        }
    }
}
