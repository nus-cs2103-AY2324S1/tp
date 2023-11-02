package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.group.Group;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Optional<Phone> phone;
    private final Optional<Email> email;
    // Data fields
    private final Optional<Address> address;
    private final Optional<Birthday> birthday;
    private final Optional<Remark> remark;
    private final Set<Group> groups = new HashSet<>();

    /**
     * Every field need not be present and not null.
     */
    public Person(Name name) {
        requireAllNonNull(name);
        this.name = name;
        this.phone = Optional.empty();
        this.email = Optional.empty();
        this.address = Optional.empty();
        this.birthday = Optional.empty();
        this.remark = Optional.empty();
    }

    /**
     * Constructor for Person with all fields present.
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param birthday
     * @param remark
     * @param groups
     */
    public Person(Name name, Optional<Phone> phone, Optional<Email> email, Optional<Address> address,
                  Optional<Birthday> birthday, Optional<Remark> remark, Set<Group> groups) {
        requireAllNonNull(name, phone, email, address, birthday, groups);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.remark = remark;
        this.groups.addAll(groups);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone.orElse(null);
    }

    public Email getEmail() {
        return email.orElse(null);
    }

    public Address getAddress() {
        return address.orElse(null);
    }

    public Birthday getBirthday() {
        return birthday.orElse(null);
    }

    public Remark getRemark() {
        return remark.orElse(null);
    }

    /**
     * Returns an immutable group set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Group> getGroups() {
        return Collections.unmodifiableSet(groups);
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
                && birthday.equals(otherPerson.birthday)
                && remark.equals(otherPerson.remark)
                && groups.equals(otherPerson.groups);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, birthday, remark, groups);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", this.getPhone())
                .add("email", this.getEmail())
                .add("address", this.getAddress())
                .add("birthday", this.getBirthday())
                .add("remark", this.getRemark())
                .add("groups", groups)
                .toString();
    }

    /**
     * Returns true if the person has a phone number.
     */
    public boolean hasPhone() {
        return getPhone() != Phone.NULL_PHONE;
    }

    /**
     * Returns true if the person has an email.
     */
    public boolean hasEmail() {
        return getEmail() != Email.NULL_EMAIL;
    }

    /**
     * Returns true if the person has an address.
     */
    public boolean hasAddress() {
        return getAddress() != Address.NULL_ADDRESS;
    }
    /**
     * Returns true if the person has a birthday.
     */
    public boolean hasBirthday() {
        return getBirthday() != Birthday.NULL_BIRTHDAY;
    }
    /**
     * Returns true if the person has a remark.
     */
    public boolean hasRemark() {
        return getRemark() != Remark.NULL_REMARK;
    }
    /**
     * Returns true if the person has groups.
     */
    public boolean hasGroups() {
        return !getGroups().isEmpty();
    }

    /**
     * Returns true if the person has a birthday within the next {@code days} days.
     */
    public boolean hasBirthdayWithinDays(int days) {
        Birthday birthday = getBirthday();
        return birthday.isWithinDays(days);
    }

    public boolean hasGroup(Group group) {
        return this.getGroups().contains(group);
    }

}
