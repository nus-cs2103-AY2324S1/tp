package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.ATTENDANCE_W0_ABSENT;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDANCE_W0_PRESENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_G01;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_T09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.AddressBookManager;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com").withPhone("94351253")
            .withId("A1234567Y").withTags("T09").withAttendance(ATTENDANCE_W0_ABSENT).build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withId("A1234567Z").withTags("G01").withAttendance(ATTENDANCE_W0_PRESENT).build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withId("A1234567M").withEmail("heinz@example.com").withAttendance(ATTENDANCE_W0_PRESENT).build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withId("A1234567N").withEmail("cornelia@example.com").withTags("T01")
            .withAttendance(ATTENDANCE_W0_PRESENT).build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withId("A1234567L").withEmail("werner@example.com").withAttendance(ATTENDANCE_W0_PRESENT).build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withId("A1234567Q").withEmail("lydia@example.com").withAttendance(ATTENDANCE_W0_PRESENT).build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withId("A1234567C").withEmail("anna@example.com").withAttendance(ATTENDANCE_W0_PRESENT).build();


    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withId("A1234567A").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withId("A1234567D").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withId(VALID_ID_AMY).withTags(VALID_TAG_T09).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withId(VALID_ID_BOB).withTags(VALID_TAG_G01, VALID_TAG_T09).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBookManager} with an address book with the typical persons.
     */
    public static AddressBookManager getTypicalAddressBookManager() {
        AddressBookManager abm = new AddressBookManager();
        abm.setAddressBook(getTypicalAddressBook());
        return abm;
    }

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook("Test Course Code");
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
