package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.Birthday;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.Gender;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Priority;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Patient[] getSamplePatients() {
        return new Patient[]{new Patient(new Name("Alex Yeoh"), new Phone("87438807"),
            new Email("alexyeoh@example.com"), new Gender("Male"), new IcNumber("t7654321a"),
            new Birthday("05/05/2005"), new Address("Blk 30 Geylang " + "Street " + "29, #06-40"), new Priority("NIL"),
            getTagSet("friends")), new Patient(new Name("Bernice Yu"), new Phone("99272758"),
                    new Email("berniceyu@example.com"), new Gender("Female"), new IcNumber("s1234567b"),
                    new Birthday("06/06/1990"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                new Priority("NIL"), getTagSet("colleagues", "friends")),
            new Patient(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    new Email("charlotte@example.com"), new Gender("Other"), new IcNumber("t1357912g"),
                    new Birthday("12/12/1989"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Priority("NIL"),
            getTagSet("neighbours")), new Patient(new Name("David Li"), new Phone("91031282"),
                    new Email("lidavid@example.com"), new Gender("Male"), new IcNumber("s7654321p"),
                new Birthday("01/01/2001"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                new Priority("NIL"), getTagSet("family")),
            new Patient(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Gender("Male"),
                            new IcNumber("s0987654e"), new Birthday("10/10/2000"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Priority("NIL"),
            getTagSet("classmates")), new Patient(new Name("Roy Balakrishnan"), new Phone("92624417"),
                    new Email("royb@example.com"), new Gender("Male"), new IcNumber("t6789031q"),
                    new Birthday("07/11/1976"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"), new Priority("NIL"), getTagSet("colleagues"))};
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Patient samplePatient : getSamplePatients()) {
            sampleAb.addPatient(samplePatient);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings).map(Tag::new).collect(Collectors.toSet());
    }

}
