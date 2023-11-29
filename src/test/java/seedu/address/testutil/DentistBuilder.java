package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.dentist.Dentist;
import seedu.address.model.person.dentist.Specialization;
import seedu.address.model.person.dentist.Yoe;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Dentist objects.
 */
public class DentistBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_SPECIALIZATION = "ORTHODONTICS";
    public static final String DEFAULT_YOE = "5";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Specialization specialization;
    private Yoe yoe;
    private Set<Tag> tags;
    private long dentistId;

    /**
     * Creates a {@code DentistBuilder} with the default details.
     */
    public DentistBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        specialization = new Specialization(DEFAULT_SPECIALIZATION);
        yoe = new Yoe(DEFAULT_YOE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the DentistBuilder with the data of {@code dentistToCopy}.
     */
    public DentistBuilder(Dentist dentistToCopy) {
        name = dentistToCopy.getName();
        phone = dentistToCopy.getPhone();
        email = dentistToCopy.getEmail();
        address = dentistToCopy.getAddress();
        specialization = dentistToCopy.getSpecialization();
        yoe = dentistToCopy.getYoe();
        tags = new HashSet<>(dentistToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Dentist} that we are building.
     */
    public DentistBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Dentist} that we are building.
     */
    public DentistBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Dentist} that we are building.
     */
    public DentistBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Dentist} that we are building.
     */
    public DentistBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Dentist} that we are building.
     */
    public DentistBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Specialization} of the {@code Dentist} that we are building.
     */
    public DentistBuilder withSpecialization(String specialization) {
        this.specialization = new Specialization(specialization);
        return this;
    }

    /**
     * Sets the {@code Yoe} (Years of Experience) of the {@code Dentist} that we are building.
     */
    public DentistBuilder withYoe(String yoe) {
        this.yoe = new Yoe(yoe);
        return this;
    }

    /**
     * Sets the {@code dentistId} of the {@code Dentist} that we are building.
     */
    public DentistBuilder withDentistId(String dentistId) {
        this.dentistId = Long.parseLong(dentistId);
        return this;
    }

    /**
     * Builds and returns a new {@code Dentist} with the specified details.
     * The details must be set using the various "with" methods before calling this method.
     *
     * @return A new {@code Dentist} instance with the specified details.
     */
    public Dentist build() {
        Dentist newDentist = new Dentist(name, phone, email, address, specialization, yoe, tags);
        if (dentistId != 0) {
            newDentist.setId(dentistId);
        }
        return newDentist;
    }

    /**
     * Builds and returns a new {@code Dentist} with the specified details (Including Id).
     * The details must be set using the various "with" methods before calling this method.
     *
     * @return A new {@code Dentist} instance with the specified details.
     */
    public Dentist buildWithId(long id) {
        // For testing purposes
        return new Dentist(name, phone, email, address, specialization, yoe, id, tags);
    }

}
