package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.financialplan.FinancialPlan;
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
    private final NextOfKinName nextOfKinName;
    private final NextOfKinPhone nextOfKinPhone;
    private final Set<FinancialPlan> financialPlans = new HashSet<>();
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, NextOfKinName nextOfKinName,
                  NextOfKinPhone nextOfKinPhone, Set<FinancialPlan> financialPlans, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, nextOfKinName, nextOfKinPhone, financialPlans, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.nextOfKinName = nextOfKinName;
        this.nextOfKinPhone = nextOfKinPhone;
        this.financialPlans.addAll(financialPlans);
        this.tags.addAll(tags);
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
    public NextOfKinName getNextOfKinName() {
        return nextOfKinName;
    }
    public NextOfKinPhone getNextOfKinPhone() {
        return nextOfKinPhone;
    }

    /**
     * Returns an immutable financial plan set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<FinancialPlan> getFinancialPlans() {
        return Collections.unmodifiableSet(financialPlans);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
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
     * Checks if the given {@code prompt} is in the {@code financialPlans} and returns the email if true.
     */
    public String gatherEmailsContainsFinancialPlan(String prompt) {
        FinancialPlan fp = new FinancialPlan(prompt);
        /*
         .contains() method checks if the hashcode of objects in the Hashset
         and the object passed as argument corresponds.
         */
        if (financialPlans.contains(fp)) {
            return this.email.toString();
        }
        return new String();
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
                && nextOfKinName.equals(otherPerson.nextOfKinName)
                && nextOfKinPhone.equals(otherPerson.nextOfKinPhone)
                && financialPlans.equals(otherPerson.financialPlans)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, nextOfKinName, nextOfKinPhone, financialPlans, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("nextOfKinName", nextOfKinName)
                .add("nextOfKinPhone", nextOfKinPhone)
                .add("financialPlans", financialPlans)
                .add("tags", tags)
                .toString();
    }

}
