package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ContactList;
import seedu.address.model.ContactsManager;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Note;
import seedu.address.model.contact.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ContactsManager} with sample data.
 */
public class SampleDataUtil {
    public static Contact[] getSampleContacts() {
        return new Contact[] {
            new Contact(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Note("CS2103 tutorial mate."),
                getTagSet("friends")),
            new Contact(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Note("CS2103 tutorial mate."),
                getTagSet("colleagues", "friends")),
            new Contact(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Note("CS2103 tutorial mate."),
                getTagSet("neighbours")),
            new Contact(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Note("CS2103 tutorial mate."),
                getTagSet("family")),
            new Contact(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Note("CS2103 tutorial mate."),
                getTagSet("classmates")),
            new Contact(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Note("CS2103 tutorial mate."),
                getTagSet("colleagues"))
        };
    }

    public static ContactList getSampleContactsManager() {
        ContactsManager sampleAb = new ContactsManager();
        for (Contact sampleContact : getSampleContacts()) {
            sampleAb.addContact(sampleContact);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
