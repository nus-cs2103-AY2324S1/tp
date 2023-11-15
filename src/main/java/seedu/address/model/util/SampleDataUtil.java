package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.AnimalType;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Email;
import seedu.address.model.person.Housing;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final Availability AVAILABLE = new Availability("Available");
    public static final Availability NOT_AVAILABLE = new Availability("NotAvailable");
    public static final Availability NIL_AVAILABILITY = new Availability("nil");

    public static Person[] getSamplePersons() {
        Person personOne = new Person(new Name("Alex Yeoh"), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                new Housing("HDB"), AVAILABLE, new Name("nil"),
                new AnimalType("able.Cat", AVAILABLE),
                getTagSet("new"));

        Person personTwo = new Person(new Name("Bernice Yu"), new Phone("99272758"),
                new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Housing("Landed"),
                NOT_AVAILABLE, new Name("Bobby"),
                new AnimalType("current.Dog", NOT_AVAILABLE),
                getTagSet("reliable", "experienced"));

        Person personThree = new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new Housing("HDB"), AVAILABLE, new Name("nil"),
                new AnimalType("able.Dog", AVAILABLE),
                getTagSet("new"));

        Person personFour = new Person(new Name("David Li"), new Phone("91031282"),
                new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Housing("Condo"),
                NOT_AVAILABLE, new Name("MeowMeow"),
                new AnimalType("current.Cat", NOT_AVAILABLE),
                getTagSet("new"));

        Person personFive = new Person(new Name("Irfan Ibrahim"), new Phone("92492021"),
                new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Housing("HDB"),
                NIL_AVAILABILITY, new Name("nil"),
                new AnimalType("nil", NIL_AVAILABILITY),
                getTagSet("experienced"));

        Person personSix = new Person(new Name("Roy Balakrishnan"), new Phone("92624417"),
                new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Housing("Landed"),
                AVAILABLE, new Name("nil"),
                new AnimalType("able.Cat", AVAILABLE),
                getTagSet("new"));

        return new Person[]{personOne, personTwo, personThree, personFour, personFive, personSix};
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
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
