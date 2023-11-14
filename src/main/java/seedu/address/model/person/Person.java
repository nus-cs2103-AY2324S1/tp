package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.lead.Lead;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees some fields are present and not null: name, phone, email, address, tags.
 * Optional fields may be null: lead, telegram, profession, income, details.
 * All field values are validated and immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    // Optional fields
    private final Lead lead;
    private final TelegramHandle telegram;
    private final Profession profession;
    private final Income income;
    private final Details details;
    private final List<Interaction> interactions = new ArrayList<>();

    // Dependant fields, may not exist
    private Optional<Reminder> reminder = Optional.empty();

    /**
     * Creates a {@code Person} given a PersonBuilder.
     */
    private Person(PersonBuilder builder) {
        requireNonNull(builder);
        this.name = builder.name;
        this.phone = builder.phone;
        this.email = builder.email;
        this.address = builder.address;
        this.tags.addAll(builder.tags);

        this.lead = builder.lead;
        this.telegram = builder.telegram;
        this.profession = builder.profession;
        this.income = builder.income;
        this.details = builder.details;
        this.interactions.addAll(builder.interactions);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Lead getLead() {
        return lead;
    }

    public TelegramHandle getTelegram() {
        return telegram;
    }

    public Profession getProfession() {
        return profession;
    }

    public Income getIncome() {
        return income;
    }

    public Details getDetails() {
        return details;
    }

    public List<Interaction> getInteractions() {
        return interactions;
    }

    /**
     * Returns the reminder associated with this person.
     */
    public Optional<Reminder> getReminder() {
        return this.reminder;
    }

    /**
     * Returns a filtered list of {@code Interaction} that matches the given predicate.
     */
    public List<Interaction> getFilteredInteractions(Predicate<Interaction> predicate) {
        return this.interactions.stream()
            .filter(predicate)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns true if the person has not been contacted yet, i.e. no interactions.
     */
    public boolean isUncontacted() {
        return this.interactions.isEmpty();
    }

    /**
     * Returns true if the person has a closed interaction.
     */
    public boolean isClosed() {
        return this.interactions.stream().anyMatch(i -> i.isOutcome(Interaction.Outcome.CLOSED));
    }

    /**
     * Returns true if the person is still in contact.
     */
    public boolean isContacting() {
        return !isUncontacted() && !isClosed();
    }

    /**
     * Returns true if the person is a hot lead.
     */
    public boolean isHotLead() {
        return lead != null && this.lead.isHot();
    }

    /**
     * Returns true if the person is a warm lead.
     */
    public boolean isWarmLead() {
        return this.lead != null && this.lead.isWarm();
    }

    /**
     * Returns true if the person is a cold lead.
     */
    public boolean isColdLead() {
        return this.lead != null && this.lead.isCold();
    }

    /**
     * Adds an interaction to the person.
     * @param interactions the set of interaction to be added
     * @return the updated set of interactions
     */
    public List<Interaction> addInteractions(List<Interaction> interactions) {
        this.interactions.addAll(interactions);
        return this.interactions;
    }

    /**
     * Returns the follow up date of the person.
     * This is calculated by adding the follow up period of the lead to the date of the latest interaction.
     * It is an optional because the person may not have a lead or interaction.
     * It is also used in Reminders constructor to determine the due time of the reminder.
     * @return
     */
    public Optional<LocalDate> getFollowUpDate() {
        if (interactions.isEmpty() || lead == null) {
            return Optional.empty();
        }
        LocalDate latestInteractionDate = interactions.get(interactions.size() - 1).getDate();
        int weeksToAdd = lead.getFollowUpPeriod();
        return Optional.of(latestInteractionDate.plusWeeks(weeksToAdd));
    }

    /**
     * Adds a Reminder to the list.
     * The Reminder must not already exist in the list.
     */
    public void updateReminder() {
        //Person must have lead and interaction to have a reminder
        //If person has no lead or interaction, remove reminder
        if (this.getFollowUpDate().isEmpty()) {
            this.reminder = Optional.empty();
            return;
        }

        Optional<Reminder> updatedReminder = Optional.of(new Reminder(this));
        this.reminder = updatedReminder;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .add("lead", lead)
                .add("telegram", telegram)
                .add("profession", profession)
                .add("income", income)
                .add("details", details)
                .toString();
    }

    /**
     * A builder factory class for Person.
     * This is used to prevent long argument lists for Person and handles optional fields well.
     */
    public static class PersonBuilder {
        // Mandatory fields
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags = new HashSet<>();

        // Optional fields
        private Lead lead;
        private TelegramHandle telegram;
        private Profession profession;
        private Income income;
        private Details details;
        private List<Interaction> interactions = new ArrayList<>();

        /**
         * Initialises the PersonBuilder with mandatory fields.
         */
        public PersonBuilder(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
            requireAllNonNull(name, phone, email, address, tags);
            this.name = name;
            this.phone = phone;
            this.email = email;
            this.address = address;
            this.tags.addAll(tags);
        }

        /**
         * Initializes the PersonBuilder with the data of {@code personToCopy}.
         */
        public PersonBuilder(Person personToCopy) {
            requireNonNull(personToCopy);
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
            interactions = personToCopy.getInteractions();
        }

        /**
         * Sets the {@code Lead} of the {@code Person} that we are building.
         */
        public PersonBuilder withLead(Lead lead) {
            this.lead = lead;
            return this;
        }

        /**
         * Sets the {@code TelegramHandle} of the {@code Person} that we are building.
         */
        public PersonBuilder withTelegram(TelegramHandle telegram) {
            this.telegram = telegram;
            return this;
        }

        /**
         * Sets the {@code Profession} of the {@code Person} that we are building.
         */
        public PersonBuilder withProfession(Profession profession) {
            this.profession = profession;
            return this;
        }

        /**
         * Sets the {@code Income} of the {@code Person} that we are building.
         */
        public PersonBuilder withIncome(Income income) {
            this.income = income;
            return this;
        }

        /**
         * Sets the {@code Details} of the {@code Person} that we are building.
         */
        public PersonBuilder withDetails(Details details) {
            this.details = details;
            return this;
        }

        /**
         * Sets the {@code InteractionList} of the {@code Person} that we are building.
         */
        public PersonBuilder withInteractions(List<Interaction> interactions) {
            this.interactions = interactions;
            return this;
        }

        /**
         * Adds the {@code Interaction} to the {@code Set<Interaction>} of the {@code Person} that we are building.
         */
        public PersonBuilder addInteraction(Interaction interaction) {
            this.interactions.add(interaction);
            return this;
        }

        /**
         * Creates the built {@code Person} after building.
         *
         * @return the built {@code Person}
         */
        public Person build() {
            return new Person(this);
        }
    }
}
