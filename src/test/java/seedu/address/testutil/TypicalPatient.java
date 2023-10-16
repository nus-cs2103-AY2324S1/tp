package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOODTYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONDITION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONDITION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMERGENCY_CONTACT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMERGENCY_CONTACT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_FEMALE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_MALE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Patient;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalPatient {

    public static final Patient ALICE = new PatientBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withRemark("She likes aardvarks.").withGender("F").withIc("T0131267K")
            .withTags("friends").withCondition("NA").withBloodType("O+").withEmergencyContact("90234567").build();
    public static final Patient BENSON = new PatientBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withRemark("He can't take beer!")
            .withEmail("johnd@example.com").withPhone("98765432").withGender("M").withIc("T0131268K")
            .withTags("owesMoney", "friends").withCondition("Type 1 Diabetes").withBloodType("O-")
            .withEmergencyContact("92234567").build();
    public static final Patient CARL = new PatientBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withGender("M").withIc("T0131269K")
            .withCondition("Type 2 Diabetes").withBloodType("AB+").withEmergencyContact("91334567").build();
    public static final Patient DANIEL = new PatientBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends")
            .withGender("M").withIc("T0131260K").withCondition("Kidney Failure").withBloodType("A+")
            .withEmergencyContact("91234567").build();
    public static final Patient ELLE = new PatientBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withGender("F").withIc("T0131261K")
            .withCondition("Brain Tumour").withBloodType("B+").withEmergencyContact("91434567").build();
    public static final Patient FIONA = new PatientBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withGender("F").withIc("T0131262K")
            .withCondition("Lung Cancer").withBloodType("A-").withEmergencyContact("95234567").build();
    public static final Patient GEORGE = new PatientBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withGender("M").withIc("T0131263K")
            .withCondition("Unknown").withBloodType("B-").withEmergencyContact("91634567").build();

    // Manually added
    public static final Patient HOON = new PatientBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withGender("M")
            .withIc("T0131263J").withCondition("NA").withBloodType("O+").withEmergencyContact("97234567").build();
    public static final Patient IDA = new PatientBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withGender("F").withIc("T0131263P")
            .withCondition("NA").withBloodType("O+").withEmergencyContact("98234567").build();

    // Manually added - Patient's details found in {@code CommandTestUtil}
    public static final Patient AMY = new PatientBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmergencyContact(VALID_EMERGENCY_CONTACT_AMY).withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY).withIc(VALID_NRIC_AMY).withGender(VALID_GENDER_FEMALE)
            .withRemark(VALID_REMARK_AMY).withCondition(VALID_CONDITION_AMY).withBloodType(VALID_BLOODTYPE_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Patient BOB = new PatientBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmergencyContact(VALID_EMERGENCY_CONTACT_BOB).withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB).withIc(VALID_NRIC_BOB).withGender(VALID_GENDER_MALE)
            .withRemark(VALID_REMARK_BOB).withCondition(VALID_CONDITION_BOB).withBloodType(VALID_CONDITION_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPatient() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical patients.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Patient patient : getTypicalPatients()) {
            ab.addPatient(patient);
        }
        return ab;
    }

    public static List<Patient> getTypicalPatients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
