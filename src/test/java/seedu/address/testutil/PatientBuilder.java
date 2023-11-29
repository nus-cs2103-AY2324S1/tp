package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Birthdate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.patients.Patient;
import seedu.address.model.tag.Tag;
import seedu.address.model.treatment.TreatmentName;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Patient objects.
 */
public class PatientBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_GENDER = "F";
    public static final String DEFAULT_BIRTHDATE = "03-02-1999";
    public static final String DEFAULT_REMARK = "Peanut Allergy";
    public static final String DEFAULT_TREATMENT_NAME = "Cleaning";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Gender gender;
    private Birthdate birthdate;
    private Remark remark;
    private TreatmentName treatmentName;
    private Set<Tag> tags;
    private long patientId;

    /**
     * Creates a {@code PatientBuilder} with the default details.
     */
    public PatientBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        gender = new Gender(DEFAULT_GENDER);
        birthdate = new Birthdate(DEFAULT_BIRTHDATE);
        remark = new Remark(DEFAULT_REMARK);
        treatmentName = new TreatmentName(DEFAULT_TREATMENT_NAME);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PatientBuilder with the data of {@code patientToCopy}.
     */
    public PatientBuilder(Patient patientToCopy) {
        name = patientToCopy.getName();
        phone = patientToCopy.getPhone();
        email = patientToCopy.getEmail();
        address = patientToCopy.getAddress();
        gender = patientToCopy.getGender();
        birthdate = patientToCopy.getBirthdate();
        remark = patientToCopy.getRemark();
        treatmentName = patientToCopy.getTreatmentName();
        tags = new HashSet<>(patientToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Patient} that we are building.
     */
    public PatientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Patient} that we are building.
     */
    public PatientBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Patient} that we are building.
     */
    public PatientBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Patient} that we are building.
     */
    public PatientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Patient} that we are building.
     */
    public PatientBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Patient} that we are building.
     */
    public PatientBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code Birthdate} of the {@code Patient} that we are building.
     */
    public PatientBuilder withBirthdate(String birthdate) {
        this.birthdate = new Birthdate(birthdate);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Patient} that we are building.
     */
    public PatientBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code TreatmentName} of the {@code Patient} that we are building.
     */
    public PatientBuilder withTreatmentName(String treatmentName) {
        this.treatmentName = new TreatmentName(treatmentName);
        return this;
    }

    /**
     * Sets the {@code patientID} of the {@code Patient} that we are building.
     */
    public PatientBuilder withPatientId(String patientId) {
        this.patientId = Long.parseLong(patientId);
        return this;
    }

    /**
     * Builds and returns a new {@code Patient} with the specified details.
     * The details must be set using the various "with" methods before calling this method.
     *
     * @return A new {@code Patient} instance with the specified details.
     */
    public Patient build() {
        Patient newPatient = new Patient(name, phone, birthdate, gender, remark, treatmentName, address, email, tags);
        if (patientId != 0) {
            newPatient.setId(patientId);
        }
        return newPatient;
    }

}
