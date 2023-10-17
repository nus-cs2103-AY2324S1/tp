package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Location;
import seedu.address.model.person.MedicalHistory;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Specialist;
import seedu.address.model.person.Specialty;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Patient(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                getTagSet("friends"), new Age("24"), getMedicalHistorySet("Diabetes")),
            new Specialist(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Location("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), new Specialty("Physiotherapist")),
            new Patient(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                getTagSet("neighbours"), new Age("114"), getMedicalHistorySet("Anemia")),
            new Patient(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                getTagSet("family"), new Age("9"), getMedicalHistorySet("Osteoporosis")),
            new Specialist(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Location("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), new Specialty("Orthopaedic")),
            new Specialist(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Location("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), new Specialty("Dermatology"))
        };
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

    /**
     * Returns a medical history set containing the list of strings given.
     */
    public static Set<MedicalHistory> getMedicalHistorySet(String... strings) {
        return Arrays.stream(strings)
                .map(MedicalHistory::new)
                .collect(Collectors.toSet());
    }
}
