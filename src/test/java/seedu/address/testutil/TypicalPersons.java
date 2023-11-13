package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEAREST_MRT_STATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEAREST_MRT_STATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEC_LEVEL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEC_LEVEL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_BIOLOGY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_PHYSICS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Student ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withGender("F").withSecLevel("3")
            .withNearestMrtStation("Jurong East")
            .withSubjects("Chinese").build();
    public static final Student BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withGender("M").withSecLevel("2").withNearestMrtStation("Harbour Front")
            .withSubjects("Chemistry", "Chinese").build();
    public static final Student CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withGender("M").withSecLevel("1")
            .withNearestMrtStation("Jurong West").build();
    public static final Student DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withGender("M").withSecLevel("1").withNearestMrtStation("Tiong Bahru")
            .withSubjects("English").build();
    public static final Student ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("94822243")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withGender("F").withSecLevel("3")
            .withNearestMrtStation("Bugis")
            .withSubjects("Physics", "Biology").build();
    public static final Student FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("94824278")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withGender("F").withSecLevel("2")
            .withNearestMrtStation("Novena")
            .withSubjects("Biology").build();
    public static final Student GEORGE = new PersonBuilder().withName("George Best").withPhone("94824423")
            .withEmail("anna@example.com").withAddress("4th street")
            .withGender("M").withSecLevel("2")
            .withNearestMrtStation("Newton").build();

    // Manually added
    public static final Student HOON = new PersonBuilder().withName("Hoon Meier").withPhone("84824241")
            .withEmail("stefan@example.com").withAddress("little india")
            .withGender("M").withSecLevel("3")
            .withNearestMrtStation("Tanjong Pagar").build();
    public static final Student IDA = new PersonBuilder().withName("Ida Mueller").withPhone("84821312")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withGender("F").withSecLevel("1")
            .withNearestMrtStation("Choa Chu Kang").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withGender(VALID_GENDER_AMY).withSecLevel(VALID_SEC_LEVEL_AMY)
            .withNearestMrtStation(VALID_NEAREST_MRT_STATION_AMY)
            .withSubjects(VALID_SUBJECT_BIOLOGY).build();
    public static final Student BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withGender(VALID_GENDER_BOB).withSecLevel(VALID_SEC_LEVEL_BOB)
            .withNearestMrtStation(VALID_NEAREST_MRT_STATION_BOB)
            .withSubjects(VALID_SUBJECT_BIOLOGY, VALID_SUBJECT_PHYSICS)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalPersons()) {
            ab.addPerson(student);
        }
        return ab;
    }

    public static List<Student> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
