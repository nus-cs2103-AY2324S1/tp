package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.patients.Patient;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalPatients {

    public static final Patient PATIENT_ALICE = new PatientBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("alice@example.com")
            .withPhone("99999999")
            .withGender("F")
            .withBirthdate("01-01-1998")
            .withRemark("Peanut Allergy")
            .withTreatmentName("Cleaning")
            .withTags("friends")
            .build();
    public static final Patient PATIENT_BOB = new PatientBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com")
            .withPhone("88888888")
            .withGender("M")
            .withBirthdate("10-02-1990")
            .withRemark("No allergy")
            .withTreatmentName("Braces")
            .withTags("owesMoney", "friends").build();
    public static final Patient PATIENT_CARL = new PatientBuilder().withName("Carl Kurz")
            .withAddress("wall street")
            .withEmail("heinz@example.com")
            .withPhone("11111111")
            .withGender("M")
            .withBirthdate("05-04-1980")
            .withRemark("Medical Complications")
            .withTreatmentName("Cleaning").build();
    public static final Patient PATIENT_DANIEL = new PatientBuilder().withName("Daniel Meier")
            .withAddress("10th street")
            .withEmail("cornelia@example.com")
            .withPhone("22222222")
            .withGender("M")
            .withBirthdate("09-10-1972")
            .withRemark("Peanut allergy")
            .withTreatmentName("Cleaning")
            .withTags("friends").build();
    public static final Patient PATIENT_ELLE = new PatientBuilder().withName("Elle Meyer")
            .withAddress("michegan ave")
            .withEmail("werner@example.com")
            .withPhone("33333333")
            .withGender("F")
            .withBirthdate("20-10-2000")
            .withRemark("Drug allergy")
            .withTreatmentName("Cleaning")
            .build();
    public static final Patient PATIENT_FIONA = new PatientBuilder().withName("Fiona Kunz")
            .withAddress("little tokyo")
            .withEmail("lydia@example.com")
            .withPhone("44444444")
            .withGender("F")
            .withBirthdate("01-01-2002")
            .withRemark("Drug allergy")
            .withTreatmentName("Cleaning")
            .build();
    public static final Patient PATIENT_GEORGE = new PatientBuilder().withName("George Best")
            .withAddress("4th street")
            .withEmail("anna@example.com")
            .withPhone("55555555")
            .withGender("M")
            .withBirthdate("10-04-2001")
            .withRemark("No allergy")
            .withTreatmentName("Cleaning")
            .build();

    // Manually added
    public static final Patient PATIENT_HOON = new PatientBuilder().withName("Hoon Meier")
            .withAddress("little india")
            .withEmail("stefan@example.com")
            .withPhone("66666666")
            .withGender("F")
            .withBirthdate("07-07-1996")
            .withRemark("Allergy to anaesthesia")
            .withTreatmentName("Cleaning")
            .build();
    public static final Patient PATIENT_IDA = new PatientBuilder().withName("Ida Mueller")
            .withAddress("chicago ave")
            .withEmail("hans@example.com")
            .withPhone("77777777")
            .withGender("F")
            .withBirthdate("03-03-1999")
            .withRemark("Drug allergy")
            .withTreatmentName("Cleaning")
            .build();

    // Manually added - Patient's details found in {@code CommandTestUtil}
    public static final Patient AMY = new PatientBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Patient BOB = new PatientBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPatients() {} // prevents instantiation

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
        return new ArrayList<>(Arrays.asList(PATIENT_ALICE, PATIENT_BOB, PATIENT_CARL,
                PATIENT_DANIEL, PATIENT_ELLE, PATIENT_FIONA, PATIENT_GEORGE));
    }
}
