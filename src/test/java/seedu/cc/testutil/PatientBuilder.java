package seedu.cc.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.cc.model.appointment.PatientAppointmentList;
import seedu.cc.model.medicalhistory.PatientMedicalHistory;
import seedu.cc.model.patient.Nric;
import seedu.cc.model.patient.Patient;
import seedu.cc.model.person.Age;
import seedu.cc.model.person.Email;
import seedu.cc.model.person.Name;
import seedu.cc.model.person.Phone;
import seedu.cc.model.tag.Tag;
import seedu.cc.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PatientBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_NRIC = "S2345678Y";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_AGE = "1";

    private Name name;
    private Nric nric;
    private Phone phone;
    private Email email;
    private Age age;
    private PatientMedicalHistory patientMedicalHistory;
    private PatientAppointmentList patientAppointmentList;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PatientBuilder() {
        name = new Name(DEFAULT_NAME);
        nric = new Nric(DEFAULT_NRIC);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        age = new Age(DEFAULT_AGE);
        patientMedicalHistory = new PatientMedicalHistory();
        patientAppointmentList = new PatientAppointmentList();
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PatientBuilder(Patient personToCopy) {
        name = personToCopy.getName();
        nric = personToCopy.getNric();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        age = personToCopy.getAge();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PatientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Nric} of the {@code Patient} that we are building.
     */
    public PatientBuilder withNric(String nric) {
        this.nric = new Nric(nric);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PatientBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Age} of the {@code Person} that we are building.
     */
    public PatientBuilder withAge(String age) {
        this.age = new Age(age);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PatientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PatientBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code PatientMedicalHistory} of the {@code Person} that we are building.
     */
    public PatientBuilder withMedicalHistory(PatientMedicalHistory patientMedicalHistory) {
        this.patientMedicalHistory = patientMedicalHistory;
        return this;
    }

    /**
     * Sets the {@code PatientAppointmentList} of the {@code Person} that we are building.
     */
    public PatientBuilder withAppointment(PatientAppointmentList appointmentList) {
        this.patientAppointmentList = appointmentList;
        return this;
    }

    /**
     * Builds a {@code Person} with the given fields.
     */
    public Patient build() {
        return new Patient(name, nric, phone, email, age,
                patientAppointmentList, patientMedicalHistory, tags);
    }

}
