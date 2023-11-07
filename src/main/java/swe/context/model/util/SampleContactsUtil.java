package swe.context.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import swe.context.model.Contacts;
import swe.context.model.alternate.AlternateContact;
import swe.context.model.contact.Contact;
import swe.context.model.contact.Email;
import swe.context.model.contact.Name;
import swe.context.model.contact.Note;
import swe.context.model.contact.Phone;
import swe.context.model.tag.Tag;



/**
 * Contains utility methods for working with sample {@link Contact}s.
 */
public class SampleContactsUtil {
    private static Contact[] getSampleContactsArray() {
        return new Contact[] {
            new Contact(
                new Name("Amy Bee"),
                new Phone("87438807"),
                new Email("e0705018@u.nus.edu"),
                new Note("CS2103 tutorial mate."),
                getTagSet("NUS", "CS2103 course"),
                getAlternateContactSet("Telegram: amy_bee")
            ),
            new Contact(
                new Name("Ben Lee"),
                new Phone("99272758"),
                new Email("ben-lee@gmail.com"),
                new Note("Dorm at 42 Wallaby Way."),
                getTagSet("friend"),
                getAlternateContactSet("HousePhone: 67280491", "Discord: ben.games")
            ),
            new Contact(
                new Name("Charlotte Oliveiro"),
                new Phone("83210283"),
                new Email("coliveiro@comp.nus.edu.sg"),
                new Note("CS2103 Prof."),
                getTagSet("NUS", "CS2103 course", "prof"),
                getAlternateContactSet()
            ),
            new Contact(
                new Name("David Li"),
                new Phone("91031282"),
                new Email("e0789123@u.nus.edu"),
                new Note(""),
                getTagSet("NUS", "CS1101S", "CS1231S"),
                getAlternateContactSet("Telegram: David99")
            ),
            new Contact(
                new Name("Ethan Ibrahim"),
                new Phone("92492021"),
                new Email("ethan.ibrahim@my-mail.com"),
                new Note("(teaching assistant)"),
                getTagSet("NUS", "CS3241", "TA"),
                getAlternateContactSet("X: ethaaan", "Telegram: ethaaan")
            )
        };
    }

    /**
     * Returns a {@link Set} of {@link Tag}s for the specified strings.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static Set<AlternateContact> getAlternateContactSet(String... strings) {
        return Arrays.stream(strings)
                .map(AlternateContact::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a {@link Contacts} instance populated with sample {@link Contact}s.
     */
    public static Contacts getSampleContacts() {
        Contacts contacts = new Contacts();
        for (Contact contact : getSampleContactsArray()) {
            contacts.add(contact);
        }
        return contacts;
    }
}
