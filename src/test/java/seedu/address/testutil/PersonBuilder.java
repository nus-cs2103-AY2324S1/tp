package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Date;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Date objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_AGE = "33";
    public static final String DEFAULT_GENDER = "F";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Age age;
    private Gender gender;
    private Address address;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        age = new Age(DEFAULT_AGE);
        gender = new Gender(DEFAULT_GENDER);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code dateToCopy}.
     */
    public PersonBuilder(Date dateToCopy) {
        name = dateToCopy.getName();
        age = dateToCopy.getAge();
        gender = dateToCopy.getGender();
        address = dateToCopy.getAddress();
        tags = new HashSet<>(dateToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Date} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Date} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Date} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Age} of the {@code Date} that we are building.
     */
    public PersonBuilder withAge(String age) {
        this.age = new Age(age);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Date} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    public Date build() {
        return new Date(name, age, gender, address, tags);
    }

}
