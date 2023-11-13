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
                new Email("alexyeoh@gmail.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friend"),
                new Nric("023A"),
                new LicencePlate("SNB9538E"),
                new Remark("Wants cheap policies!"),
                new Policy(
                    new Company("NTUC Income"),
                    new PolicyNumber("NI11235"),
                    new PolicyDate("11-11-2023"),
                    new PolicyDate("11-11-2024")
                )
            ),
            new Person(
                new Name("Bernice Yu"),
                new Phone("99272758"),
                new Email("berniceyu@gmail.com"),
                new Address("Blk 30 Woodleigh Crescent, #07-18"),
                getTagSet("colleagues", "friends"),
                new Nric("362D"),
                new LicencePlate("SLR5694E"),
                new Remark("Friends for 4 years! Likes to hang out"),
                new Policy(
                    new Company("AXA Insurance"),
                    new PolicyNumber("AI38495"),
                    new PolicyDate("11-10-2023"),
                    new PolicyDate("11-10-2024")
                )
            ),
            new Person(
                new Name("Eva Ng"),
                new Phone("89326549"),
                new Email("evang@example.com"),
                new Address("Blk 101 Bukit Timah Road, #15-20"),
                getTagSet("neighbours"),
                new Nric("123A"),
                new LicencePlate("SGP6535A"),
                new Remark("Neighbours at current residence for 15 years already!"),
                new Policy(
                    new Company("HSBC Insurance"),
                    new PolicyNumber("HI23593"),
                    new PolicyDate("30-12-2022"),
                    new PolicyDate("30-12-2023")
                )
            ),
            new Person(
                new Name("David Li"),
                new Phone("91031282"),
                new Email("lidavid@outlook.com"),
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
                new Email("irfan@gmail.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"),
                new Nric("752X"),
                new LicencePlate("SBP5950T"),
                new Remark("Classmate from high school"),
                new Policy(
                    new Company("NTUC Income"),
                    new PolicyNumber("NI294759"),
                    new PolicyDate("20-12-2022"),
                    new PolicyDate("20-12-2023")
                )
            ),
            new Person(
                new Name("Roy Balakrishnan"),
                new Phone("92624417"),
                new Email("royb@yahoo.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"),
                new Nric("764J"),
                new LicencePlate("SJD6453Y"),
                new Remark("Colleagues since 2 years ago"),
                new Policy(
                    new Company(Company.DEFAULT_VALUE),
                    new PolicyNumber(PolicyNumber.DEFAULT_VALUE),
                    new PolicyDate(PolicyDate.DEFAULT_VALUE),
                    new PolicyDate(PolicyDate.DEFAULT_VALUE)
                )
            ),
            new Person(
                new Name("Amirah Abdullah"),
                new Phone("86532542"),
                new Email("amirah@gmail.com.com"),
                new Address("Blk 456 Serangoon Avenue 3, #05-12"),
                getTagSet("colleagues"),
                new Nric("456B"),
                new LicencePlate("SGP5678Y"),
                EMPTY_REMARK,
                new Policy(
                    new Company("DirectAsia Insurance"),
                    new PolicyNumber("DI149503"),
                    new PolicyDate("15-06-2023"),
                    new PolicyDate("15-06-2024")
                )
            ),
            new Person(
                new Name("Raj Kumar"),
                new Phone("93653498"),
                new Email("rkumar@gmail.com"),
                new Address("Blk 789 Orchard Road, #10-15"),
                getTagSet("friends"),
                new Nric("567C"),
                new LicencePlate("SGP6789Z"),
                EMPTY_REMARK,
                new Policy(
                    new Company("AXA Insurance"),
                    new PolicyNumber("AI948593"),
                    new PolicyDate("20-07-2023"),
                    new PolicyDate("20-07-2024")
                )
            ),
            new Person(
                new Name("Noor Muhammad"),
                new Phone("93210283"),
                new Email("noormuhammad@yahoo.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("friends"),
                new Nric("743G"),
                new LicencePlate("SBA8473A"),
                EMPTY_REMARK,
                new Policy(
                    new Company("NTUC Income"),
                    new PolicyNumber("NI49383"),
                    new PolicyDate("20-12-2022"),
                    new PolicyDate("20-12-2023")
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
