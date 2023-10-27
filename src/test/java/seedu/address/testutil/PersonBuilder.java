package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Subject;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_REMARK = "";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Subject> subjects;
    private Set<Tag> tags;
    private Remark remark;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        try {
            name = new Name(DEFAULT_NAME);
            phone = new Phone(DEFAULT_PHONE);
            email = new Email(DEFAULT_EMAIL);
            address = new Address(DEFAULT_ADDRESS);
            subjects = new HashSet<>();
            tags = new HashSet<>();
            remark = new Remark(DEFAULT_REMARK);
        } catch (Exception e) {
            name = Name.DEFAULT_NAME;
            phone = Phone.DEFAULT_PHONE;
            email = Email.DEFAULT_EMAIL;
            address = Address.DEFAULT_ADDRESS;
            subjects = new HashSet<>();
            tags = new HashSet<>();
            remark = Remark.DEFAULT_REMARK;
        }
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        subjects = personToCopy.getSubjectsSet();
        tags = personToCopy.getTagsSet();
        remark = personToCopy.getRemark();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        try {
            this.name = new Name(name);
        } catch (Exception e) {
            this.name = Name.DEFAULT_NAME;
        }
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code subjects} into a {@code Set<Subject>} and set it to the {@code Subject} that we are building.
     */
    public PersonBuilder withSubjects(String ... subjects) {
        this.subjects = SampleDataUtil.getSubjectSet(subjects);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        try {
            this.address = new Address(address);
        } catch (Exception e) {
            this.address = Address.DEFAULT_ADDRESS;
        }
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        try {
            this.phone = new Phone(phone);
        } catch (Exception e) {
            this.phone = Phone.DEFAULT_PHONE;
        }
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        try {
            this.email = new Email(email);
        } catch (Exception e) {
            this.email = Email.DEFAULT_EMAIL;
        }
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Person} that we are building.
     */
    public PersonBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, subjects, tags, remark);
    }

}
