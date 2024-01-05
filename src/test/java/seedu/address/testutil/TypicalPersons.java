package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentSet;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder()
            .withName("Alice Pauline")
            .withTelegramHandle("alicePaulding7").withEmail("alice@u.nus.edu")
            .withPhone("94351253")
            .withTags("friends")
            .withGradedTest("RA1:10 | RA2:20 | MidTerms:30 | Finals:40 | PE:50").build();
    public static final Person BENSON = new PersonBuilder()
            .withName("Benson Meier")
            .withTelegramHandle("Bensonson")
            .withEmail("johnd@u.nus.edu")
            .withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withGradedTest("RA1:1 | RA2:2 | MidTerms:3 | Finals:4 | PE:5").build();
    public static final Person CARL = new PersonBuilder()
            .withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("heinz@u.nus.edu")
            .withTelegramHandle("richcarlton1101")
            .withGradedTest("RA1:5 | RA2:4 | MidTerms:3 | Finals:2 | PE:1").build();
    public static final Person DANIEL = new PersonBuilder()
            .withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@u.nus.edu")
            .withTelegramHandle("dantheman")
            .withTags("friends")
            .withGradedTest("RA1:0 | RA2:0 | MidTerms:0 | Finals:0 | PE:0").build();
    public static final Person ELLE = new PersonBuilder()
            .withName("Elle Meyer")
            .withPhone("9482224")
            .withEmail("werner@u.nus.edu")
            .withTelegramHandle("AllieWithAnEButNoI").build();
    public static final Person FIONA = new PersonBuilder()
            .withName("Fiona Kunz")
            .withPhone("9482427")
            .withEmail("lydia@u.nus.edu")
            .withTelegramHandle("phi0na").build();

    public static final Person GEORGE = new PersonBuilder()
            .withName("George Best")
            .withPhone("9482442")
            .withEmail("anna@u.nus.edu")
            .withTelegramHandle("georgeBes17").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@u.nus.edu").withTelegramHandle("hoonismyname").withGradedTest().build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@u.nus.edu").withTelegramHandle("idaho1202").withGradedTest().build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withTelegramHandle(VALID_TELEGRAM_HANDLE_AMY).withTags(VALID_TAG_FRIEND)
            .withGradedTest().build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withTelegramHandle(VALID_TELEGRAM_HANDLE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withGradedTest().build();

    public static final StudentSet DEFAULT_PERSONS = SampleDataUtil.getStudentSet(AMY);

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
