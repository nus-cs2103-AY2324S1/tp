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
import seedu.address.model.person.Remark;
import seedu.address.model.policy.Company;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyDate;
import seedu.address.model.policy.PolicyNumber;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final Remark EMPTY_REMARK = new Remark("");

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
                EMPTY_REMARK,
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
                new Address("Blk 30 Serangoon North, #07-18"),
                getTagSet("colleagues", "friends"),
                new Nric("362D"),
                new LicencePlate("SLR5E"),
                EMPTY_REMARK,
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
                EMPTY_REMARK,
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
                EMPTY_REMARK,
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
                EMPTY_REMARK,
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
                EMPTY_REMARK,
                new Policy(
                    new Company(Company.DEFAULT_VALUE),
                    new PolicyNumber(PolicyNumber.DEFAULT_VALUE),
                    new PolicyDate(PolicyDate.DEFAULT_VALUE),
                    new PolicyDate(PolicyDate.DEFAULT_VALUE)
                )
            ),
            new Person(
                new Name("Bob White"),
                new Phone("23456789"),
                new Email("bob.white@example.com"),
                new Address("Blk 456 Serangoon Avenue 3, #05-12"),
                getTagSet("colleagues"),
                new Nric("456B"),
                new LicencePlate("SGP5678Y"),
                EMPTY_REMARK,
                new Policy(
                    new Company("ABC Insurance"),
                    new PolicyNumber("RS67890"),
                    new PolicyDate("15-06-2023"),
                    new PolicyDate("15-06-2024")
                )
            ),
            new Person(
                new Name("Alice Green"),
                new Phone("34567890"),
                new Email("alice.green@example.com"),
                new Address("Blk 789 Orchard Road, #10-15"),
                getTagSet("friends"),
                new Nric("567C"),
                new LicencePlate("SGP6789Z"),
                EMPTY_REMARK,
                new Policy(
                    new Company("XYZ Insurance"),
                    new PolicyNumber("TP12345"),
                    new PolicyDate("20-07-2023"),
                    new PolicyDate("20-07-2024")
                )
            ),
            new Person(
                new Name("John Doe"),
                new Phone("12345678"),
                new Email("john.doe@example.com"),
                new Address("Blk 101 Bukit Timah Road, #15-20"),
                getTagSet("neighbors"),
                new Nric("123A"),
                new LicencePlate("SGP1234A"),
                EMPTY_REMARK,
                new Policy(
                    new Company("PQR Insurance"),
                    new PolicyNumber("XY123456"),
                    new PolicyDate("15-07-2023"),
                    new PolicyDate("15-07-2024")
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
