package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Details;
import seedu.address.model.person.Email;
import seedu.address.model.person.Income;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Profession;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.person.interaction.Interaction;
import seedu.address.model.person.lead.Lead;
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
    public static final String DEFAULT_LEAD = "cold";
    public static final String DEFAULT_TELEGRAM = "@amybee";
    public static final String DEFAULT_PROFESSION = "teacher";
    public static final String DEFAULT_INCOME = "4000";
    public static final String DEFAULT_DETAILS = "Likes to play sports";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private Lead lead;
    private TelegramHandle telegram;
    private Profession profession;
    private Income income;
    private Details details;
    private List<Interaction> interactions;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        lead = new Lead(DEFAULT_LEAD);
        telegram = new TelegramHandle(DEFAULT_TELEGRAM);
        profession = new Profession(DEFAULT_PROFESSION);
        income = new Income(Integer.valueOf(DEFAULT_INCOME));
        details = new Details(DEFAULT_DETAILS);
        interactions = new ArrayList<>();
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
        lead = personToCopy.getLead();
        telegram = personToCopy.getTelegram();
        profession = personToCopy.getProfession();
        income = personToCopy.getIncome();
        details = personToCopy.getDetails();
        interactions = personToCopy.getInteractions();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
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
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
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
     * Sets the {@code Lead} of the {@code Person} that we are building.
     */
    public PersonBuilder withLead(String lead) {
        this.lead = new Lead(lead);
        return this;
    }

    /**
     * Sets the {@code TelegramHandle} of the {@code Person} that we are building.
     */
    public PersonBuilder withTelegram(String telegram) {
        this.telegram = new TelegramHandle(telegram);
        return this;
    }

    /**
     * Sets the {@code Profession} of the {@code Person} that we are building.
     */
    public PersonBuilder withProfession(String profession) {
        this.profession = new Profession(profession);
        return this;
    }

    /**
     * Sets the {@code Income} of the {@code Person} that we are building.
     */
    public PersonBuilder withIncome(String income) {
        this.income = new Income(Integer.valueOf(income));
        return this;
    }

    /**
     * Sets the {@code Details} of the {@code Person} that we are building.
     */
    public PersonBuilder withDetails(String details) {
        this.details = new Details(details);
        return this;
    }

    /**
     * Sets the {@code Interaction} of the {@code Person} that we are building.
     */
    public PersonBuilder withInteractions(List<Interaction> interactions) {
        this.interactions = interactions;
        return this;
    }

    /**
     * Creates the built {@code Person} after building.
     *
     * @return the built {@code Person}
     */
    public Person build() {
        return new Person.PersonBuilder(name, phone, email, address, tags)
                .withLead(lead).withTelegram(telegram).withProfession(profession)
                .withIncome(income).withDetails(details).withInteractions(interactions)
                .build();
    }
}
