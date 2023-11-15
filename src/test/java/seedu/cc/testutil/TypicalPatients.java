package seedu.cc.testutil;

import static seedu.cc.logic.commands.CommandTestUtil.VALID_AGE_AMY;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_AGE_BOB;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_TAG_ASTHMA;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_TAG_HYPERTENSION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import seedu.cc.commons.core.index.Index;
import seedu.cc.model.ClinicBook;
import seedu.cc.model.patient.Patient;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalPatients {
    public static final Patient ALICE = new PatientBuilder().withName("Alice Pauline")
            .withNric("S1234567A")
            .withAge("32").withEmail("alice@example.com")
            .withPhone("94351253")
            .withMedicalHistory(new MedicalHistoryEventBuilder().buildMedicalHistory())
            .withAppointment(new AppointmentEventBuilder().buildAppointmentList())
            .withTags("Hypertension").build();
    public static final Patient BENSON = new PatientBuilder().withName("Benson Meier")
            .withNric("S1234567B")
            .withAge("46")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("Asthma", "Hypertension").build();
    public static final Patient CARL = new PatientBuilder().withName("Carl Kurz")
            .withNric("S1234567C")
            .withPhone("95352563")
            .withEmail("heinz@example.com").withAge("77").build();
    public static final Patient DANIEL = new PatientBuilder().withName("Daniel Meier")
            .withNric("S1234567D")
            .withPhone("87652533")
            .withEmail("cornelia@example.com").withAge("50")
            .withMedicalHistory(new MedicalHistoryEventBuilder().buildMedicalHistory())
            .withAppointment(new AppointmentEventBuilder().buildAppointmentList())
            .withTags("Hypertension").build();
    public static final Patient ELLE = new PatientBuilder().withName("Elle Meyer")
            .withNric("S1234567E")
            .withPhone("94822240")
            .withEmail("werner@example.com").withAge("96").build();
    public static final Patient FIONA = new PatientBuilder().withName("Fiona Kunz")
            .withNric("S1234567F")
            .withPhone("94824270")
            .withEmail("lydia@example.com").withAge("69").build();
    public static final Patient GEORGE = new PatientBuilder().withName("George Best")
            .withNric("S1234567G")
            .withPhone("94824420")
            .withEmail("anna@example.com").withAge("52").build();

    // Manually added
    public static final Patient HOON = new PatientBuilder().withName("Hoon Meier")
            .withNric("S1234567H")
            .withPhone("84824240")
            .withEmail("stefan@example.com").withAge("13").build();
    public static final Patient IDA = new PatientBuilder().withName("Ida Mueller")
            .withNric("S1234567I")
            .withPhone("84821310")
            .withEmail("hans@example.com").withAge("22").build();

    // Manually added - Patient's details found in {@code CommandTestUtil}
    public static final Patient AMY = new PatientBuilder().withName(VALID_NAME_AMY)
            .withNric(VALID_NRIC_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAge(VALID_AGE_AMY).withTags(VALID_TAG_HYPERTENSION).build();
    public static final Patient BOB = new PatientBuilder().withName(VALID_NAME_BOB)
            .withNric(VALID_NRIC_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAge(VALID_AGE_BOB).withTags(VALID_TAG_ASTHMA, VALID_TAG_HYPERTENSION)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPatients() {
    } // prevents instantiation

    /**
     * Returns an {@code ClinicBook} with all the typical persons.
     */
    public static ClinicBook getTypicalClinicBook() {
        ClinicBook cb = new ClinicBook();
        for (Patient person : getTypicalPatients()) {
            cb.addPatient(person);
        }
        return cb;
    }

    public static List<Patient> getTypicalPatients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
