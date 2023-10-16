package seedu.address.testutil;

import java.time.MonthDay;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Linkedin;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;
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

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Optional<Birthday> birthday;
    private Optional<Linkedin> linkedin;
    private Optional<Email> secondaryEmail;
    private Optional<Telegram> telegram;
    private Optional<Integer> id;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        birthday = Optional.empty(); // No birthday by default
        linkedin = Optional.empty(); // No linkedin by default
        secondaryEmail = Optional.empty(); // No secondaryEmail by default
        telegram = Optional.empty(); // No telegram by default
        tags = new HashSet<>();
        id = Optional.empty();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        birthday = personToCopy.getBirthday();
        linkedin = personToCopy.getLinkedin();
        secondaryEmail = personToCopy.getSecondaryEmail();
        telegram = personToCopy.getTelegram();
        tags = new HashSet<>(personToCopy.getTags());
        id = personToCopy.getId();
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
     * Sets the {@code Birthday} of the {@code Person} that we are building.
     */
    public PersonBuilder withBirthday(MonthDay birthday) {
        this.birthday = Optional.of(new Birthday(birthday));
        return this;
    }

    /**
     * Sets the {@code Linkedin} of the {@code Person} that we are building.
     */
    public PersonBuilder withLinkedin(String linkedin) {
        this.linkedin = Optional.of(new Linkedin(linkedin));
        return this;
    }

    /**
     * Sets the {@code Secondary Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = Optional.of(new Email(secondaryEmail));
        return this;
    }

    /**
     * Sets the {@code Secondary Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withTelegram(String telegram) {
        this.telegram = Optional.of(new Telegram(telegram));
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
     * Sets the {@code ID} of the {@code Person} that we are building.
     */
    public PersonBuilder withId(int id) {
        this.id = Optional.of(id);
        return this;
    }

    /**
     * Builds a {@code Person}.
     */
    public Person build() {
        return new Person(name, phone, email, address, birthday, linkedin, secondaryEmail, telegram, tags, id);
    }

}
