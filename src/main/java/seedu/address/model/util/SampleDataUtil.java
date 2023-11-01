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
import seedu.address.model.person.Remark;
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
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
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
            ),
            new Person(
                new Name("Alice Johnson"),
                new Phone("23456789"),
                new Email("alice.j@example.com"),
                new Address("Blk 202 Clementi Ave 6, #09-15"),
                getTagSet("friends", "colleagues"),
                new Nric("234B"),
                new LicencePlate("SGP2345B"),
                    EMPTY_REMARK,
                new Policy(
                    new Company("LMN Insurance"),
                    new PolicyNumber("YZ345678"),
                    new PolicyDate("10-09-2023"),
                    new PolicyDate("10-09-2024")
                )
            ),
            new Person(
                new Name("Emma Lee"),
                new Phone("34567890"),
                new Email("emma.lee@example.com"),
                new Address("Blk 303 Orchard Road, #12-05"),
                getTagSet("friends", "shopping"),
                new Nric("567C"),
                new LicencePlate("SGP5678C"),
                EMPTY_REMARK,
                new Policy(
                    new Company("ABC Insurance"),
                    new PolicyNumber("ZA987654"),
                    new PolicyDate("25-12-2023"),
                    new PolicyDate("25-12-2024")
                )
            ),
            new Person(
                new Name("Sophia Lim"),
                new Phone("56789012"),
                new Email("sophia.l@example.com"),
                new Address("Blk 707 Bedok Reservoir Road, #05-02"),
                getTagSet("neighbors"),
                new Nric("789E"),
                new LicencePlate("SGP7890E"),
                EMPTY_REMARK,
                new Policy(
                    new Company("DEF Insurance"),
                    new PolicyNumber("UT678901"),
                    new PolicyDate("17-09-2023"),
                    new PolicyDate("17-09-2024")
                )
            ),
            new Person(
                new Name("Oliver Ng"),
                new Phone("67890123"),
                new Email("oliver.ng@example.com"),
                new Address("Blk 909 Ang Mo Kio Ave 4, #03-15"),
                getTagSet("friends", "gaming"),
                new Nric("890F"),
                new LicencePlate("SGP8901A"),
                EMPTY_REMARK,
                new Policy(
                    new Company("GHI Insurance"),
                    new PolicyNumber("SR123456"),
                    new PolicyDate("03-11-2022"),
                    new PolicyDate("03-11-2023")
                )
            ),
            new Person(
                new Name("Lily Goh"),
                new Phone("78901234"),
                new Email("lily.g@example.com"),
                new Address("Blk 606 Yishun Ring Road, #07-08"),
                getTagSet("family", "shopping"),
                new Nric("901G"),
                new LicencePlate("SGP9012G"),
                EMPTY_REMARK,
                new Policy(
                    new Company("JKL Insurance"),
                    new PolicyNumber("QP654321"),
                    new PolicyDate("18-05-2023"),
                    new PolicyDate("18-05-2024")
                )
            ),
            new Person(
                new Name("Lucas Tan"),
                new Phone("89012345"),
                new Email("lucas.t@example.com"),
                new Address("Blk 404 Clementi Ave 1, #11-20"),
                getTagSet("colleagues"),
                new Nric("012H"),
                new LicencePlate("SGP0123H"),
                EMPTY_REMARK,
                new Policy(
                    new Company("MNO Insurance"),
                    new PolicyNumber("ST987654"),
                    new PolicyDate("22-08-2023"),
                    new PolicyDate("22-08-2024")
                )
            ),
            new Person(
                new Name("Sophia Lim"),
                new Phone("90123456"),
                new Email("sophia.l@example.com"),
                new Address("Blk 707 Woodlands Drive 40, #05-12"),
                getTagSet("friends", "travel"),
                new Nric("345K"),
                new LicencePlate("SGP3456K"),
                EMPTY_REMARK,
                new Policy(
                    new Company("PQR Insurance"),
                    new PolicyNumber("XZ123456"),
                    new PolicyDate("11-09-2023"),
                    new PolicyDate("11-09-2024")
                )
            ),
            new Person(
                new Name("Ethan Tan"),
                new Phone("91234567"),
                new Email("ethan.tan@example.com"),
                new Address("Blk 505 Jurong West St 52, #08-03"),
                getTagSet("family", "movies"),
                new Nric("456L"),
                new LicencePlate("SGP4567L"),
                EMPTY_REMARK,
                new Policy(
                    new Company("GHI Insurance"),
                    new PolicyNumber("YX654321"),
                    new PolicyDate("07-04-2023"),
                    new PolicyDate("07-04-2024")
                )
            ),
            new Person(
                new Name("Zoe Ng"),
                new Phone("92345678"),
                new Email("zoe.ng@example.com"),
                new Address("Blk 303 Tampines Ave 5, #12-09"),
                getTagSet("neighbors", "reading"),
                new Nric("567M"),
                new LicencePlate("SGP5678M"),
                EMPTY_REMARK,
                new Policy(
                    new Company("JKL Insurance"),
                    new PolicyNumber("WV987654"),
                    new PolicyDate("25-12-2023"),
                    new PolicyDate("25-12-2024")
                )
            ),
            new Person(
                new Name("Liam Wong"),
                new Phone("93456789"),
                new Email("liam.w@example.com"),
                new Address("Blk 404 Hougang Ave 10, #06-18"),
                getTagSet("friends", "sports"),
                new Nric("678N"),
                new LicencePlate("SGP6789B"),
                EMPTY_REMARK,
                new Policy(
                    new Company("ABC Insurance"),
                    new PolicyNumber("AB123456"),
                    new PolicyDate("02-03-2023"),
                    new PolicyDate("02-03-2024")
                )
            ),
            new Person(
                new Name("Emma Koh"),
                new Phone("94567890"),
                new Email("emma.k@example.com"),
                new Address("Blk 808 Bukit Batok St 22, #03-11"),
                getTagSet("family", "cooking"),
                new Nric("789P"),
                new LicencePlate("SGP7890P"),
                EMPTY_REMARK,
                new Policy(
                    new Company("XYZ Insurance"),
                    new PolicyNumber("ZY654321"),
                    new PolicyDate("18-11-2022"),
                    new PolicyDate("18-11-2023")
                )
            ),
            new Person(
                new Name("Oliver Goh"),
                new Phone("95678901"),
                new Email("oliver.g@example.com"),
                new Address("Blk 202 Ang Mo Kio Ave 3, #09-25"),
                getTagSet("neighbors", "gardening"),
                new Nric("890Q"),
                new LicencePlate("SGP8901X"),
                EMPTY_REMARK,
                new Policy(
                    new Company("MNO Insurance"),
                    new PolicyNumber("MN987654"),
                    new PolicyDate("05-07-2023"),
                    new PolicyDate("05-07-2024")
                )
            ),
            new Person(
                new Name("Sophia Tan"),
                new Phone("96789012"),
                new Email("sophia.t@example.com"),
                new Address("Blk 505 Clementi Ave 2, #12-08"),
                getTagSet("friends", "reading"),
                new Nric("901R"),
                new LicencePlate("SGP9012R"),
                EMPTY_REMARK,
                new Policy(
                    new Company("EFG Insurance"),
                    new PolicyNumber("EF123456"),
                    new PolicyDate("09-05-2023"),
                    new PolicyDate("09-05-2024")
                )
            ),
            new Person(
                new Name("Jackson Lim"),
                new Phone("97890123"),
                new Email("jackson.l@example.com"),
                new Address("Blk 707 Serangoon Ave 2, #05-15"),
                getTagSet("colleagues", "travel"),
                new Nric("012S"),
                new LicencePlate("SGP0123S"),
                EMPTY_REMARK,
                new Policy(
                    new Company("PQR Insurance"),
                    new PolicyNumber("PQ987654"),
                    new PolicyDate("17-12-2023"),
                    new PolicyDate("17-12-2024")
                )
            ),
            new Person(
                new Name("Ava Ng"),
                new Phone("98901234"),
                new Email("ava.n@example.com"),
                new Address("Blk 101 Yishun Ring Rd, #08-22"),
                getTagSet("neighbors", "cooking"),
                new Nric("345T"),
                new LicencePlate("SGP3456T"),
                EMPTY_REMARK,
                new Policy(
                    new Company(Company.DEFAULT_VALUE),
                    new PolicyNumber(PolicyNumber.DEFAULT_VALUE),
                    new PolicyDate(PolicyDate.DEFAULT_VALUE),
                    new PolicyDate(PolicyDate.DEFAULT_VALUE)
                )
            ),
            new Person(
                new Name("Nor Aisyah"),
                new Phone("93332222"),
                new Email("nor.a@example.com"),
                new Address("Blk 303 Bukit Batok St 32, #15-11"),
                getTagSet("neighbors", "sewing"),
                new Nric("901F"),
                new LicencePlate("SGP9012G"),
                EMPTY_REMARK,
                new Policy(
                    new Company("DEF Insurance"),
                    new PolicyNumber("DE123456"),
                    new PolicyDate("05-07-2023"),
                    new PolicyDate("05-07-2024")
                )
            ),
            new Person(
                new Name("Ben Tan"),
                new Phone("91234567"),
                new Email("liam.tan@example.com"),
                new Address("Blk 808 Ang Mo Kio Ave 3, #10-18"),
                getTagSet("friends", "gaming"),
                new Nric("567Y"),
                new LicencePlate("SGP5678E"),
                EMPTY_REMARK,
                new Policy(
                    new Company(Company.DEFAULT_VALUE),
                    new PolicyNumber(PolicyNumber.DEFAULT_VALUE),
                    new PolicyDate(PolicyDate.DEFAULT_VALUE),
                    new PolicyDate(PolicyDate.DEFAULT_VALUE)
                )
            ),
            new Person(
                new Name("Edwin Koh"),
                new Phone("94567890"),
                new Email("ava.k@example.com"),
                new Address("Blk 555 Woodlands Ave 3, #12-05"),
                getTagSet("neighbors", "gardening"),
                new Nric("901C"),
                new LicencePlate("SGP9012Z"),
                EMPTY_REMARK,
                new Policy(
                    new Company("GHI Insurance"),
                    new PolicyNumber("GH123456"),
                    new PolicyDate("24-11-2023"),
                    new PolicyDate("24-11-2024")
                )
            ),
            new Person(
                new Name("Noah Lee"),
                new Phone("95678901"),
                new Email("noah.lee@example.com"),
                new Address("Blk 707 Clementi West St 2, #03-11"),
                getTagSet("friends", "reading"),
                new Nric("234D"),
                new LicencePlate("SGP2345E"),
                EMPTY_REMARK,
                new Policy(
                    new Company("JKL Insurance"),
                    new PolicyNumber("JK123456"),
                    new PolicyDate("07-08-2023"),
                    new PolicyDate("07-08-2024")
                )
            ),
            new Person(
                new Name("Lucas Tan"),
                new Phone("97890123"),
                new Email("lucas.t@example.com"),
                new Address("Blk 111 Pasir Ris St 11, #08-14"),
                getTagSet("friends", "fitness"),
                new Nric("678G"),
                new LicencePlate("SGP6789H"),
                EMPTY_REMARK,
                new Policy(
                    new Company(Company.DEFAULT_VALUE),
                    new PolicyNumber(PolicyNumber.DEFAULT_VALUE),
                    new PolicyDate(PolicyDate.DEFAULT_VALUE),
                    new PolicyDate(PolicyDate.DEFAULT_VALUE)
                )
            ),
            new Person(
                new Name("James Koh"),
                new Phone("95671234"),
                new Email("james.k@example.com"),
                new Address("Blk 707 Bedok Reservoir Rd, #05-09"),
                getTagSet("friends", "cooking"),
                new Nric("567L"),
                new LicencePlate("SGP5678M"),
                EMPTY_REMARK,
                new Policy(
                    new Company("UVW Insurance"),
                    new PolicyNumber("UV123456"),
                    new PolicyDate("22-03-2023"),
                    new PolicyDate("22-03-2024")
                )
            ),
            new Person(
                new Name("Yao Xuan Tan"),
                new Phone("93341234"),
                new Email("yxt@example.com"),
                new Address("Blk 505 Yishun St 51, #03-18"),
                getTagSet("neighbors", "reading"),
                new Nric("789M"),
                new LicencePlate("SGP7890H"),
                EMPTY_REMARK,
                new Policy(
                    new Company("WXY Insurance"),
                    new PolicyNumber("WX123456"),
                    new PolicyDate("17-09-2023"),
                    new PolicyDate("17-09-2024")
                )
            ),
            new Person(
                new Name("Harper Lim"),
                new Phone("93216543"),
                new Email("harperl@example.com"),
                new Address("Blk 101 Sengkang East Ave, #18-10"),
                getTagSet("neighbors", "cooking"),
                new Nric("901S"),
                new LicencePlate("SGP9012T"),
                EMPTY_REMARK,
                new Policy(
                    new Company("DEF Insurance"),
                    new PolicyNumber("DE123456"),
                    new PolicyDate("02-02-2023"),
                    new PolicyDate("02-02-2024")
                )
            ),
            new Person(
                new Name("Logan Tan"),
                new Phone("97658901"),
                new Email("logant@example.com"),
                new Address("Blk 303 Ang Mo Kio Ave 3, #04-17"),
                getTagSet("family", "movies"),
                new Nric("234U"),
                new LicencePlate("SGP2345X"),
                EMPTY_REMARK,
                new Policy(
                    new Company(Company.DEFAULT_VALUE),
                    new PolicyNumber(PolicyNumber.DEFAULT_VALUE),
                    new PolicyDate(PolicyDate.DEFAULT_VALUE),
                    new PolicyDate(PolicyDate.DEFAULT_VALUE)
                )
            ),
            new Person(
                new Name("Rajesh Kumar"),
                new Phone("93216789"),
                new Email("rajesh.k@example.com"),
                new Address("Blk 202 Serangoon Central, #10-07"),
                getTagSet("friends", "cricket"),
                new Nric("345E"),
                new LicencePlate("SGP3456J"),
                EMPTY_REMARK,
                new Policy(
                    new Company("PQR Insurance"),
                    new PolicyNumber("PQ123456"),
                    new PolicyDate("18-11-2023"),
                    new PolicyDate("18-11-2024")
                )
            ),
            new Person(
                new Name("Luna Goh"),
                new Phone("93213456"),
                new Email("lunag@example.com"),
                new Address("Blk 404 Clementi Ave 1, #08-05"),
                getTagSet("friends", "gaming"),
                new Nric("901V"),
                new LicencePlate("SGP9012H"),
                EMPTY_REMARK,
                new Policy(
                    new Company("WXY Insurance"),
                    new PolicyNumber("WX123456"),
                    new PolicyDate("21-09-2023"),
                    new PolicyDate("21-09-2024")
                )
            ),
            new Person(
                new Name("Mateo Lim"),
                new Phone("99881234"),
                new Email("mateol@example.com"),
                new Address("Blk 707 Jurong East St 72, #05-08"),
                getTagSet("neighbors", "music"),
                new Nric("567W"),
                new LicencePlate("SGP5678X"),
                EMPTY_REMARK,
                new Policy(
                    new Company("XYZ Insurance"),
                    new PolicyNumber("XY123456"),
                    new PolicyDate("11-05-2023"),
                    new PolicyDate("11-05-2024")
                )
            ),
            new Person(
                new Name("Hazel Tan"),
                new Phone("93211234"),
                new Email("hazelt@example.com"),
                new Address("Blk 101 Bedok North Ave 4, #03-14"),
                getTagSet("friends", "travel"),
                new Nric("901X"),
                new LicencePlate("SGP9012Y"),
                EMPTY_REMARK,
                new Policy(
                    new Company("ABC Insurance"),
                    new PolicyNumber("AB123456"),
                    new PolicyDate("27-07-2023"),
                    new PolicyDate("27-07-2024")
                )
            ),
            new Person(
                new Name("Aiden Lim"),
                new Phone("99889900"),
                new Email("aidenl@example.com"),
                new Address("Blk 303 Hougang Ave 5, #09-11"),
                getTagSet("friends", "sports"),
                new Nric("567Y"),
                new LicencePlate("SGP5678Z"),
                EMPTY_REMARK,
                new Policy(
                    new Company("LMN Insurance"),
                    new PolicyNumber("LM123456"),
                    new PolicyDate("03-12-2023"),
                    new PolicyDate("03-12-2024")
                )
            ),
            new Person(
                new Name("Amirah Abdullah"),
                new Phone("93331111"),
                new Email("amirah.a@example.com"),
                new Address("Blk 202 Geylang Road, #08-14"),
                getTagSet("neighbors", "cooking"),
                new Nric("345H"),
                new LicencePlate("SGP3456J"),
                EMPTY_REMARK,
                new Policy(
                    new Company("QRS Insurance"),
                    new PolicyNumber("QR123456"),
                    new PolicyDate("13-08-2023"),
                    new PolicyDate("13-08-2024")
                )
            ),
            new Person(
                new Name("Bryan Ng"),
                new Phone("97658901"),
                new Email("bryan.n@example.com"),
                new Address("Blk 303 Sengkang East Ave, #04-17"),
                getTagSet("family", "music"),
                new Nric("234M"),
                new LicencePlate("SGP2345A"),
                EMPTY_REMARK,
                new Policy(
                    new Company("GHI Insurance"),
                    new PolicyNumber("GH123456"),
                    new PolicyDate("07-02-2023"),
                    new PolicyDate("07-02-2024")
                )
            ),
            new Person(
                new Name("Benjamin Lee"),
                new Phone("93211234"),
                new Email("ben.l@example.com"),
                new Address("Blk 101 Yishun Ring Rd, #03-14"),
                getTagSet("friends", "travel"),
                new Nric("901Q"),
                new LicencePlate("SGP9012R"),
                EMPTY_REMARK,
                new Policy(
                    new Company("ABC Insurance"),
                    new PolicyNumber("AB123456"),
                    new PolicyDate("22-08-2023"),
                    new PolicyDate("22-08-2024")
                )
            ),
            new Person(
                new Name("Eli Tan"),
                new Phone("93216577"),
                new Email("eli.t@example.com"),
                new Address("Blk 202 Serangoon Central, #05-11"),
                getTagSet("friends", "pets"),
                new Nric("678R"),
                new LicencePlate("SGP6789S"),
                EMPTY_REMARK,
                new Policy(
                    new Company(Company.DEFAULT_VALUE),
                    new PolicyNumber(PolicyNumber.DEFAULT_VALUE),
                    new PolicyDate(PolicyDate.DEFAULT_VALUE),
                    new PolicyDate(PolicyDate.DEFAULT_VALUE)
                )
            ),
            new Person(
                new Name("Oscar Ng"),
                new Phone("99889988"),
                new Email("oscar.n@example.com"),
                new Address("Blk 202 Tampines St 21, #05-11"),
                getTagSet("friends", "travelling"),
                new Nric("901M"),
                new LicencePlate("SGP9012Z"),
                EMPTY_REMARK,
                new Policy(
                    new Company("DEF Insurance"),
                    new PolicyNumber("DE123456"),
                    new PolicyDate("14-09-2023"),
                    new PolicyDate("14-09-2024")
                )
            ),
            new Person(
                new Name("Leo Tan"),
                new Phone("93217654"),
                new Email("leo.t@example.com"),
                new Address("Blk 505 Bishan St 22, #18-10"),
                getTagSet("neighbors", "reading"),
                new Nric("890K"),
                new LicencePlate("SGP8901L"),
                EMPTY_REMARK,
                new Policy(
                    new Company("PQR Insurance"),
                    new PolicyNumber("PQ123456"),
                    new PolicyDate("19-08-2023"),
                    new PolicyDate("19-08-2024")
                )
            ),
            new Person(
                new Name("Ananya Sharma"),
                new Phone("98887777"),
                new Email("ananya.s@example.com"),
                new Address("Blk 505 Clementi Ave 1, #12-09"),
                getTagSet("friends", "yoga"),
                new Nric("567D"),
                new LicencePlate("SGP5678E"),
                EMPTY_REMARK,
                new Policy(
                    new Company("XYZ Insurance"),
                    new PolicyNumber("XY123456"),
                    new PolicyDate("12-09-2023"),
                    new PolicyDate("12-09-2024")
                )
            ),
            new Person(
                new Name("Zara Ng"),
                new Phone("99882345"),
                new Email("zara.n@example.com"),
                new Address("Blk 101 Hougang Ave 5, #14-22"),
                getTagSet("friends", "shopping"),
                new Nric("234H"),
                new LicencePlate("SGP2345K"),
                EMPTY_REMARK,
                new Policy(
                    new Company("QRS Insurance"),
                    new PolicyNumber("QR123456"),
                    new PolicyDate("28-11-2022"),
                    new PolicyDate("28-11-2023")
                )
            ),
            new Person(
                new Name("Isaac Lim"),
                new Phone("97659900"),
                new Email("isaac.l@example.com"),
                new Address("Blk 202 Bukit Merah Central, #11-15"),
                getTagSet("neighbors", "cooking"),
                new Nric("678Y"),
                new LicencePlate("SGP6789H"),
                EMPTY_REMARK,
                new Policy(
                    new Company("ABC Insurance"),
                    new PolicyNumber("AB123456"),
                    new PolicyDate("05-03-2023"),
                    new PolicyDate("05-03-2024")
                )
            ),
            new Person(
                new Name("Maya Lim"),
                new Phone("93213450"),
                new Email("maya.l@example.com"),
                new Address("Blk 101 Yishun Ave 11, #08-15"),
                getTagSet("friends", "volunteering"),
                new Nric("901N"),
                new LicencePlate("SGP9012P"),
                EMPTY_REMARK,
                new Policy(
                    new Company("QRS Insurance"),
                    new PolicyNumber("QR123456"),
                    new PolicyDate("12-03-2023"),
                    new PolicyDate("12-03-2024")
                )
            ),
            new Person(
                new Name("Kai Tan"),
                new Phone("99880011"),
                new Email("kai.t@example.com"),
                new Address("Blk 303 Bedok North Ave 4, #09-11"),
                getTagSet("friends", "fitness"),
                new Nric("567P"),
                new LicencePlate("SGP5678H"),
                EMPTY_REMARK,
                new Policy(
                    new Company(Company.DEFAULT_VALUE),
                    new PolicyNumber(PolicyNumber.DEFAULT_VALUE),
                    new PolicyDate(PolicyDate.DEFAULT_VALUE),
                    new PolicyDate(PolicyDate.DEFAULT_VALUE)
                )
            ),
            new Person(
                new Name("Nina Wong"),
                new Phone("91234567"),
                new Email("nina.w@example.com"),
                new Address("Blk 505 Woodlands Drive 14, #11-22"),
                getTagSet("friends", "travel"),
                new Nric("345S"),
                new LicencePlate("SGP3456T"),
                EMPTY_REMARK,
                new Policy(
                    new Company("PQR Insurance"),
                    new PolicyNumber("PQ123456"),
                    new PolicyDate("14-02-2023"),
                    new PolicyDate("14-02-2024")
                )
            ),
            new Person(
                new Name("Zack Lim"),
                new Phone("97778888"),
                new Email("zack.l@example.com"),
                new Address("Blk 101 Sengkang East Ave, #09-11"),
                getTagSet("friends", "gaming"),
                new Nric("901Z"),
                new LicencePlate("SGP9012P"),
                EMPTY_REMARK,
                new Policy(
                    new Company("GHI Insurance"),
                    new PolicyNumber("GH123456"),
                    new PolicyDate("30-01-2023"),
                    new PolicyDate("30-01-2024")
                )
            ),
            new Person(
                new Name("Muhammad Afiq"),
                new Phone("93331111"),
                new Email("nora.t@example.com"),
                new Address("Blk 303 Tampines St 32, #15-14"),
                getTagSet("friends", "cooking"),
                new Nric("678P"),
                new LicencePlate("SGP6789J"),
                EMPTY_REMARK,
                new Policy(
                    new Company("QRS Insurance"),
                    new PolicyNumber("QR123456"),
                    new PolicyDate("14-11-2022"),
                    new PolicyDate("14-11-2023")
                )
            ),
            new Person(
                new Name("Omar Ng"),
                new Phone("98889999"),
                new Email("omar.n@example.com"),
                new Address("Blk 202 Yishun Ave 5, #05-01"),
                getTagSet("friends", "travelling"),
                new Nric("678Y"),
                new LicencePlate("SGP6789Z"),
                EMPTY_REMARK,
                new Policy(
                    new Company("ABC Insurance"),
                    new PolicyNumber("AB123456"),
                    new PolicyDate("07-11-2022"),
                    new PolicyDate("07-11-2023")
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
