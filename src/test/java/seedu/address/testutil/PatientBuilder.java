package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.Condition;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Patient objects.
 */
public class PatientBuilder {
    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMERGENCY_CONTACT = "91234567";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_REMARK = "She likes aardvarks.";
    public static final String DEFAULT_GENDER = "F";
    public static final String DEFAULT_NRIC = "T0000000Z";
    public static final String DEFAULT_CONDITION = "Default condition";
    public static final String DEFAULT_BLOODTYPE = "O+";

    private Name name;
    private Phone phone;
    private Phone emergencyContact;
    private Email email;
    private Address address;
    private Remark remark;
    private Gender gender;
    private Ic ic;
    private Condition condition;
    private BloodType bloodType;
    private Set<Tag> tags;

    /**
     * Constructor for the PatientBuilder class that initialises
     * a PatientBuilder instance populated with default values.
     */
    public PatientBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        emergencyContact = new Phone(DEFAULT_EMERGENCY_CONTACT);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        remark = new Remark(DEFAULT_REMARK);
        gender = new Gender(DEFAULT_GENDER);
        ic = new Ic(DEFAULT_NRIC);
        condition = new Condition(DEFAULT_CONDITION);
        bloodType = new BloodType(DEFAULT_BLOODTYPE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PatientBuilder with the data of {@code patientToCopy}.
     */
    public PatientBuilder(Patient patientToCopy) {
        name = patientToCopy.getName();
        phone = patientToCopy.getPhone();
        emergencyContact = patientToCopy.getEmergencyContact();
        email = patientToCopy.getEmail();
        address = patientToCopy.getAddress();
        remark = patientToCopy.getRemark();
        gender = patientToCopy.getGender();
        ic = patientToCopy.getIc();
        condition = patientToCopy.getCondition();
        bloodType = patientToCopy.getBloodType();
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
    public PatientBuilder withTags(String... tags) {
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
     * Sets the {@code emergencyContact} of the {@code Patient} that we are building.
     */
    public PatientBuilder withEmergencyContact(String emergencyContact) {
        this.emergencyContact = new Phone(emergencyContact);
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
     * Sets the {@code Remark} of the {@code Patient} that we are building.
     */
    public PatientBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
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
     * Sets the {@code Ic} of the {@code Patient} that we are building.
     */
    public PatientBuilder withIc(String ic) {
        this.ic = new Ic(ic);
        return this;
    }

    /**
     * Sets the {@code condition} of the {@code Patient} that we are building.
     */
    public PatientBuilder withCondition(String condition) {
        this.condition = new Condition(condition);
        return this;
    }

    /**
     * Sets the {@code bloodType} of the {@code Patient} that we are building.
     */
    public PatientBuilder withBloodType(String bloodType) {
        this.bloodType = new BloodType(bloodType);
        return this;
    }


    public Patient build() {
        return new Patient(name, phone, emergencyContact, email, address, remark, gender, ic, condition, bloodType,
                tags);
    }

}
