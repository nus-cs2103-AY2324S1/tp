package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private Optional<Birthday> birthday;
    private Optional<Linkedin> linkedin;
    private Optional<Email> secondaryEmail;
    private Optional<Telegram> telegram;
    private final Set<Tag> tags = new HashSet<>();
    private Optional<Integer> id;
    private Avatar avatar = new Avatar();
    private Balance balance;

    private ObservableList<Note> notes;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = Optional.empty();
        this.linkedin = Optional.empty();
        this.secondaryEmail = Optional.empty();
        this.telegram = Optional.empty();
        this.tags.addAll(tags);
        this.id = Optional.empty();
        this.notes = FXCollections.observableArrayList();
        this.balance = new Balance(0);
    }

    /**
     * Constructor including optional fields.
     */
    public Person(Name name, Phone phone, Email email, Address address, Optional<Birthday> birthday,
                  Optional<Linkedin> linkedin, Optional<Email> secondaryEmail,
                  Optional<Telegram> telegram, Set<Tag> tags, Optional<Integer> id, ObservableList<Note> notes,
                  Balance balance) {
        requireAllNonNull(name, phone, email, address, birthday, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.linkedin = linkedin;
        this.secondaryEmail = secondaryEmail;
        this.telegram = telegram;
        this.tags.addAll(tags);
        this.id = id;
        this.notes = notes;
        this.balance = balance;
    }

    /**
     * Constructor allowing to add all attributes.
     */
    public Person(Name name, Phone phone, Email email, Address address, Optional<Birthday> birthday,
                  Optional<Linkedin> linkedin, Optional<Email> secondaryEmail,
                  Optional<Telegram> telegram, Set<Tag> tags, Optional<Integer> id,
                  Avatar avatar, ObservableList<Note> notes, Balance balance) {
        requireAllNonNull(name, phone, email, address, birthday, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.linkedin = linkedin;
        this.secondaryEmail = secondaryEmail;
        this.telegram = telegram;
        this.tags.addAll(tags);
        this.id = id;
        this.avatar = avatar;
        this.notes = notes;
        this.balance = balance;
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

    public Optional<Birthday> getBirthday() {
        return birthday;
    }

    public Optional<Linkedin> getLinkedin() {
        return linkedin;
    }

    public Optional<Email> getSecondaryEmail() {
        return secondaryEmail;
    }

    public Optional<Telegram> getTelegram() {
        return telegram;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns a set of non-emergency tags.
     * @return
     */
    public Set<Tag> getNonEmergencyTags() {
        return tags.stream()
                .filter(tag -> !Tag.EmergencyTags.isEmergencyTag(tag.tagName))
                .collect(Collectors.toSet());
    }

    /**
     * Returns a set of emergency tags.
     * @return
     */
    public Set<Tag> getEmergencyTags() {
        return tags.stream()
                .filter(tag -> Tag.EmergencyTags.isEmergencyTag(tag.tagName))
                .collect(Collectors.toSet());
    }
    public Optional<Integer> getId() {
        return id;
    }

    public Balance getBalance() {
        return balance;
    }

    public int setId(int id) {
        this.id = Optional.of(id);
        return id;
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
     * Adds a note to the person.
     * @param note
     */
    public void addNote(Note note) {
        notes.add(note);
    }

    /**
     * Removes a note from the person.
     * @param idx
     * @throws Exception
     */
    public void removeNote(int idx) {
        notes.remove(idx);
    }

    /**
     * Sets new balance for the person by adding
     * given amount.
     * @param amount
     */
    public void pay(Balance amount) {
        balance = balance.add(amount);
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
                && telegram.equals(otherPerson.telegram)
                && linkedin.equals(otherPerson.linkedin)
                && birthday.equals(otherPerson.birthday)
                && secondaryEmail.equals(otherPerson.secondaryEmail)
                && tags.equals(otherPerson.tags);
    }

    public ObservableList<Note> getNotes() {
        return notes;
    }

    /**
     * Adds a listener to the notes list, currently used to ensure the notes list
     * window is reactive.
     * @param listener Listener to add.
     */
    public void addNotesListener(ListChangeListener<Note> listener) {
        notes.addListener(listener);
    }

    /**
     * Returns true if birthday has a value.
     */
    public boolean hasValidBirthday() {
        return !birthday.equals(Optional.empty());
    }

    /**
     * Returns true if linkedin has a value.
     */
    public boolean hasValidLinkedin() {
        return !linkedin.equals(Optional.empty());
    }

    /**
     * Returns true if secondaryEmail has a value.
     */
    public boolean hasValidSecondaryEmail() {
        return !secondaryEmail.equals(Optional.empty());
    }

    /**
     * Returns true if telegram has a value.
     */
    public boolean hasValidTelegram() {
        return !telegram.equals(Optional.empty());
    }

    /**
     * Returns true if the person has same primary and secondary email.
     */
    public boolean hasRepeatedEmail() {
        if (secondaryEmail.equals(Optional.empty())) {
            return false;
        }
        return email.equals(secondaryEmail.get());
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
                .toString();
    }
}

