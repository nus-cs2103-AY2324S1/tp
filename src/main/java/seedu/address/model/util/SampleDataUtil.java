package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.LicencePlate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.policy.Company;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyDate;
import seedu.address.model.policy.PolicyNumber;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(
                new Name("Alex Yeoh"),
                new Phone("87438807"),
                new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"),
                new Nric("023A"),
                new LicencePlate("SNB9538E"),
                new Policy(
                    new Company("NTUC"),
                    new PolicyNumber("A1231X"),
                    new PolicyDate("01-01-2023"),
                    new PolicyDate("01-01-2024")
                )
            ),
            new Person(
                new Name("Bernice Yu"),
                new Phone("99272758"),
                new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"),
                new Nric("362D"),
                new LicencePlate("SLR5E"),
                new Policy(
                    new Company("InsureMe"),
                    new PolicyNumber("B3425"),
                    new PolicyDate("01-01-2020"),
                    new PolicyDate("01-01-2030")
                )
            ),
            new Person(
                new Name("Charlotte Oliveiro"),
                new Phone("93210283"),
                new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"),
                new Nric("743G"),
                new LicencePlate("SBA1234A"),
                new Policy(
                    new Company("NTUC"),
                    new PolicyNumber("B3425"),
                    new PolicyDate("20-12-2020"),
                    new PolicyDate("01-01-2030")
                )
            ),
            new Person(
                new Name("David Li"),
                new Phone("91031282"),
                new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"),
                new Nric("362D"),
                new LicencePlate("SDN5345A"),
                new Policy(
                    new Company(Company.DEFAULT_VALUE),
                    new PolicyNumber(PolicyNumber.DEFAULT_VALUE),
                    new PolicyDate(PolicyDate.DEFAULT_VALUE),
                    new PolicyDate(PolicyDate.DEFAULT_VALUE)
                )
            ),
            new Person(
                new Name("Irfan Ibrahim"),
                new Phone("92492021"),
                new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"),
                new Nric("752X"),
                new LicencePlate("SBP8888T"),
                new Policy(
                    new Company(Company.DEFAULT_VALUE),
                    new PolicyNumber(PolicyNumber.DEFAULT_VALUE),
                    new PolicyDate(PolicyDate.DEFAULT_VALUE),
                    new PolicyDate(PolicyDate.DEFAULT_VALUE)
                )
            ),
            new Person(
                new Name("Roy Balakrishnan"),
                new Phone("92624417"),
                new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"),
                new Nric("764J"),
                new LicencePlate("SJD6453Y"),
                new Policy(
                    new Company(Company.DEFAULT_VALUE),
                    new PolicyNumber(PolicyNumber.DEFAULT_VALUE),
                    new PolicyDate(PolicyDate.DEFAULT_VALUE),
                    new PolicyDate(PolicyDate.DEFAULT_VALUE)
                )
            )
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

}
