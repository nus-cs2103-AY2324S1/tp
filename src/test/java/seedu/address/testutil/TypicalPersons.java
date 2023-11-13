package seedu.address.testutil;



import static seedu.address.logic.commands.CommandTestUtil.VALID_AGE_THIRTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_HISTORY_ANEMIA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALTY_DERMATOLOGY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Specialist;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Patient ALICE = (Patient) new PatientBuilder().withAge("17")
            .withMedicalHistory("Anemia", "Osteoporosis")
            .withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Patient BENSON = (Patient) new PatientBuilder().withAge("29")
            .withMedicalHistory("Osteoporosis")
            .withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();

    public static final Patient CARL = (Patient) new PatientBuilder().withAge("47")
            .withName("Carl Kurz").withPhone("95352563").withEmail("heinz@example.com")
            .build();
    public static final Patient DANIEL = (Patient) new PatientBuilder().withAge("78").withMedicalHistory("Asthma")
            .withName("Daniel Meier").withPhone("87652533").withEmail("cornelia@example.com")
            .withTags("friends").build();
    public static final Specialist ELLE = (Specialist) new SpecialistBuilder().withSpecialty("Dermatology")
            .withLocation("michegan ave").withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").build();
    public static final Specialist FIONA = (Specialist) new SpecialistBuilder().withSpecialty("Orthopaedic")
            .withLocation("little tokyo").withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").build();
    public static final Specialist GEORGE = (Specialist) new SpecialistBuilder().withSpecialty("Physiotherapy")
            .withLocation("4th street").withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").build();

    // Manually added
    public static final Patient HOON = (Patient) new PatientBuilder()
            .withAge("79").withMedicalHistory("Diabetes")
            .withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").build();

    public static final Specialist IDA = (Specialist) new SpecialistBuilder()
            .withSpecialty("Gynaecology")
            .withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Patient AMY = (Patient) new PatientBuilder()
            .withAge(VALID_AGE_THIRTY).withMedicalHistory(VALID_MEDICAL_HISTORY_ANEMIA)
            .withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Specialist BOB = (Specialist) new SpecialistBuilder()
            .withLocation(VALID_LOCATION_BOB).withSpecialty(VALID_SPECIALTY_DERMATOLOGY)
            .withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

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
