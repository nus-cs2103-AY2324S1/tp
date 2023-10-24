package seedu.address.model.person;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.lead.Lead;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Person objects.
 */
public class PersonCreator {

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Lead lead;
    private Set<Tag> tags;
    private TelegramHandle telegram;
    private Profession profession;
    private Income income;
    private Details details;
    private Set<Interaction> interactions = new HashSet<>();

    /**
     * Initializes the PersonCreator with the data of {@code personToCopy}.
     */
    public PersonCreator(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        lead = personToCopy.getLead();
        tags = new HashSet<>(personToCopy.getTags());
        telegram = personToCopy.getTelegram();
        profession = personToCopy.getProfession();
        income = personToCopy.getIncome();
        details = personToCopy.getDetails();
        interactions = new HashSet<>(personToCopy.getInteractions());
    }

    /**
     * Sets the {@code Telegram} of the {@code Person} that we are building.
     */
    public PersonCreator withTelegram(TelegramHandle telegramHandle) {
        this.telegram = telegramHandle;
        return this;
    }

    /**
     * Parses the {@code Profession} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonCreator withProfession(Profession profession) {
        this.profession = profession;
        return this;
    }

    /**
     * Sets the {@code Income} of the {@code Person} that we are building.
     */
    public PersonCreator withIncome(Income income) {
        this.income = income;
        return this;
    }

    /**
     * Sets the {@code Details} of the {@code Person} that we are building.
     */
    public PersonCreator withDetails(Details details) {
        this.details = details;
        return this;
    }

    /**
     * Parses the {@code interactions} into a {@code Set<Interaction>}
     * and set it to the {@code Person} that we are building.
     */
    public PersonCreator withInteractions(Set<Interaction> interactions) {
        this.interactions.addAll(interactions);
        return this;
    }

    /**
     * Parses the {@code lead} of the {@code Lead} that we are building.
     */
    public PersonCreator withLead(Lead lead) {
        this.lead = lead;
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
