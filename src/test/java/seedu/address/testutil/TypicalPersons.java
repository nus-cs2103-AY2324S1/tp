package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.LogBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline").withId("T7243948H")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withAppointment("12-Dec-2023 12:00 15:00").withPhone("94351253")
            .withMedical("tachycardia").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier").withId("S1234567B")
            .withAddress("311, Clementi Ave 2, #02-25").withAppointment("12-Dec-2023 12:00 15:00")
            .withEmail("johnd@example.com").withPhone("98765432").withMedical("diabetes", "high-risk")
            .build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withId("S8765432B")
            .withPhone("95352563").withEmail("heinz@example.com")
            .withAddress("wall street").withAppointment("01-Jan-2023 10:00 12:00")
            .withMedical("O= Blood Type").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withId("T1234567I")
            .withPhone("87652533").withEmail("cornelia@example.com")
            .withAddress("10th street").withAppointment("01-Jan-2023 10:00 12:00")
            .build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withId("T1111111Z")
            .withPhone("9482224").withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withId("M2222222Z")
            .withPhone("9482427").withEmail("lydia@example.com").withAddress("little tokyo").build();

    public static final Person GEORGE = new PersonBuilder().withName("George Best").withId("G2767543H")
            .withAppointment("12-Dec-2023 12:00 15:00").withPhone("9482442").withEmail("anna@example.com")
            .withAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withId("S3333333H").withEmail("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withId("T4444444I").withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withAppointment(VALID_APPOINTMENT).withId(VALID_ID_AMY).withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withAppointment(VALID_APPOINTMENT).withId(VALID_ID_BOB).withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB).build();


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

    /**
     * Returns an {@code LogBook} with all the typical persons.
     */
    public static LogBook getTypicalLogBook() {
        LogBook ab = new LogBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
