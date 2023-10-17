package seedu.address.testutil;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class DoctorBuilder {
    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355256";
    public static final String DEFAULT_EMAIL = "alicepauline@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #09-111";
    public static final String DEFAULT_REMARK = "She hated Medical School.";
    public static final String DEFAULT_GENDER = "F";
    public static final String DEFAULT_NRIC = "T1111111Z";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Remark remark;
    private Gender gender;
    private Ic ic;
    private Set<Tag> tags;

    /**
     * Constructor for the PersonBuilder class that initialises
     * a PersonBuilder instance populated with default values.
     */
    public DoctorBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        remark = new Remark(DEFAULT_REMARK);
        gender = new Gender(DEFAULT_GENDER);
        ic = new Ic(DEFAULT_NRIC);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public DoctorBuilder(Doctor doctorToCopy) {
        name = doctorToCopy.getName();
        phone = doctorToCopy.getPhone();
        email = doctorToCopy.getEmail();
        address = doctorToCopy.getAddress();
        remark = doctorToCopy.getRemark();
        gender = doctorToCopy.getGender();
        ic = doctorToCopy.getIc();
        tags = new HashSet<>(doctorToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public DoctorBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }
    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public DoctorBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }
    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public DoctorBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }
    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public DoctorBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }
    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public DoctorBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Person} that we are building.
     */
    public DoctorBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Person} that we are building.
     */
    public DoctorBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code Ic} of the {@code Person} that we are building.
     */
    public DoctorBuilder withIc(String ic) {
        this.ic = new Ic(ic);
        return this;
    }

    public Doctor build() {
        return new Doctor(name, phone, email, address, remark, gender, ic, tags);
    }

}
