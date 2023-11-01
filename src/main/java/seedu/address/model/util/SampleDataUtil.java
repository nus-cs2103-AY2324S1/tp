package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.AddressBookManager;
import seedu.address.model.person.Email;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSampleCS2103TPersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new ID("A1234567E"), getTagSet("CS2103T")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new ID("A1234567B"), getTagSet("CS2103T")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new ID("A1234567X"), getTagSet("CS2103T"))
        };
    }

    public static Person[] getSampleCS2030SPersons() {
        return new Person[] {
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new ID("A1234567F"), getTagSet("CS2030S")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new ID("A1234567K"), getTagSet("CS2030S")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new ID("A1234567A"), getTagSet("CS2030S"))
        };
    }

    public static AddressBookManager getSampleAddressBookManager() {
        AddressBookManager sampleAbm = new AddressBookManager();

        AddressBook sampleCS2103TAb = new AddressBook("CS2103T");
        for (Person samplePerson : getSampleCS2103TPersons()) {
            sampleCS2103TAb.addPerson(samplePerson);
        }

        AddressBook sampleCS2030SAb = new AddressBook("CS2030S");
        for (Person samplePerson : getSampleCS2030SPersons()) {
            sampleCS2030SAb.addPerson(samplePerson);
        }

        sampleAbm.setAddressBook(sampleCS2103TAb);
        sampleAbm.addAddressBook(sampleCS2030SAb);

        return sampleAbm;

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
