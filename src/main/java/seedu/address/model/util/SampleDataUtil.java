package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.musician.Email;
import seedu.address.model.musician.Musician;
import seedu.address.model.musician.Name;
import seedu.address.model.musician.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Musician[] getSampleMusicians() {
        return new Musician[] {
            new Musician(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                getTagSet("friends"), getTagSet(), getTagSet()),
            new Musician(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                getTagSet("colleagues", "friends"), getTagSet(), getTagSet()),
            new Musician(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                getTagSet("neighbours"), getTagSet(), getTagSet()),
            new Musician(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                getTagSet("family"), getTagSet(), getTagSet()),
            new Musician(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                getTagSet("classmates"), getTagSet(), getTagSet()),
            new Musician(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                getTagSet("colleagues"), getTagSet(), getTagSet())
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Musician sampleMusician : getSampleMusicians()) {
            sampleAb.addMusician(sampleMusician);
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

    /**
     * Returns a musician set containing the list of musicians given.
     */
    public static Set<Musician> getMusicianSet(Musician... musicians) {
        return Arrays.stream(musicians).collect(Collectors.toSet());
    }

}
