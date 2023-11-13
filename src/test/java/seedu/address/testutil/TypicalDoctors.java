package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AGE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AGE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ClinicAssistant;
import seedu.address.model.doctor.Doctor;

/**
 * A utility class containing a list of {@code Doctor} objects to be used in tests.
 */
public class TypicalDoctors {

    public static final Doctor ALICE = new DoctorBuilder()
            .withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("alice@example.com")
            .withGender("F")
            .withAge(21)
            .withPhone("94351253")
            .build();
    public static final Doctor BENSON = new DoctorBuilder()
            .withName("Benson Meier")
            .withPhone("98765432")
            .withEmail("johnd@example.com")
            .withGender("M")
            .withAge(42)
            .withAddress("311, Clementi Ave 2, #02-25")
            .build();
    public static final Doctor CARL = new DoctorBuilder()
            .withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withGender("M")
            .withAge(22)
            .withAddress("wall street")
            .build();
    public static final Doctor DANIEL = new DoctorBuilder()
            .withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withGender("M")
            .withAge(25)
            .withAddress("10th street")
            .build();
    public static final Doctor ELLE = new DoctorBuilder()
            .withName("Elle Meyer")
            .withPhone("94822244")
            .withEmail("werner@example.com")
            .withGender("F")
            .withAge(32)
            .withAddress("michegan ave")
            .build();
    public static final Doctor FIONA = new DoctorBuilder()
            .withName("Fiona Kunz")
            .withPhone("94824277")
            .withEmail("lydia@example.com")
            .withGender("F")
            .withAge(20)
            .withAddress("little tokyo")
            .build();
    public static final Doctor GEORGE = new DoctorBuilder()
            .withName("George Best")
            .withPhone("94824422")
            .withEmail("anna@example.com")
            .withGender("M")
            .withAge(100)
            .withAddress("4th street")
            .build();

    // Manually added
    public static final Doctor HOON = new DoctorBuilder()
            .withName("Hoon Meier")
            .withPhone("84824244")
            .withEmail("stefan@example.com")
            .withGender("F")
            .withAge(0)
            .withAddress("little india")
            .build();
    public static final Doctor IDA = new DoctorBuilder()
            .withName("Ida Mueller")
            .withPhone("84821311")
            .withEmail("hans@example.com")
            .withGender("F")
            .withAge(12)
            .withAddress("chicago ave")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Doctor AMY = new DoctorBuilder()
            .withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withGender(VALID_GENDER_AMY)
            .withAge(VALID_AGE_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .build();
    public static final Doctor BOB = new DoctorBuilder()
            .withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withGender(VALID_GENDER_BOB)
            .withAge(VALID_AGE_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalDoctors() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static ClinicAssistant getTypicalAddressBook() {
        ClinicAssistant ab = new ClinicAssistant();
        for (Doctor doctor : getTypicalDoctors()) {
            ab.addDoctor(doctor);
        }
        return ab;
    }

    public static List<Doctor> getTypicalDoctors() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
