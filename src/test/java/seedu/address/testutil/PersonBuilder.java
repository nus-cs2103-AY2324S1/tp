package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.AnimalType;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Email;
import seedu.address.model.person.Housing;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
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
    public static final String DEFAULT_ANIMAL_NAME = "Lalaland";
    public static final String DEFAULT_AVAILABILITY = "NotAvailable";
    public static final String DEFAULT_ANIMAL_TYPE = "current.Dog";
    public static final String DEFAULT_HOUSING = "Landed";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private Name animalName;
    private Availability availability;
    private AnimalType animalType;
    private Housing housing;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        animalName = new Name(DEFAULT_ANIMAL_NAME);
        availability = new Availability(DEFAULT_AVAILABILITY);
        animalType = new AnimalType(DEFAULT_ANIMAL_TYPE, DEFAULT_AVAILABILITY);
        housing = new Housing(DEFAULT_HOUSING);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        animalName = personToCopy.getAnimalName();
        availability = personToCopy.getAvailability();
        animalType = personToCopy.getAnimalType();
        housing = personToCopy.getHousing();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
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
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the animal {@code Name} of the animal, which the {@code Person} that we are building is fostering.
     */
    public PersonBuilder withAnimalName(String animalName) {
        this.animalName = new Name(animalName);
        return this;
    }

    /**
     * Sets the {@code Availability} of the {@code Person} that we are building.
     */
    public PersonBuilder withAvailability(String availability) {
        this.availability = new Availability(availability);
        return this;
    }

    /**
     * Sets the {@code AnimalType} of the animal, which the {@code Person} that we are building is fostering.
     */
    public PersonBuilder withAnimalType(String animalType, String availability) {
        this.animalType = new AnimalType(animalType, availability);
        return this;
    }

    /**
     * Sets the {@code Housing} of the {@code Person} that we are building.
     */
    public PersonBuilder withHousing(String housing) {
        this.housing = new Housing(housing);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, housing, availability, animalName, animalType, tags);
    }

}
