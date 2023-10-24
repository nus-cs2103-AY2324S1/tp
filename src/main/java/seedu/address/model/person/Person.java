package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.appointment.NullAppointment;
import seedu.address.model.appointment.ScheduleItem;
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
    private final Address address;
    private final NextOfKinName nextOfKinName;
    private final NextOfKinPhone nextOfKinPhone;
    private final Set<FinancialPlan> financialPlans = new HashSet<>();
    private final Set<Tag> tags = new HashSet<>();
    private final ScheduleItem appointment;
    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, NextOfKinName nextOfKinName,
                  NextOfKinPhone nextOfKinPhone, Set<FinancialPlan> financialPlans,
                  Set<Tag> tags, ScheduleItem appointment) {

        requireAllNonNull(name, phone, email, address, nextOfKinName, nextOfKinPhone,
                financialPlans, tags, appointment);

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.nextOfKinName = nextOfKinName;
        this.nextOfKinPhone = nextOfKinPhone;
        this.financialPlans.addAll(financialPlans);
        this.tags.addAll(tags);
        this.appointment = appointment;
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

    public ScheduleItem getAppointment() {
        return appointment;
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
     * Checks if the given {@code prompt} is a substring of {@code financialPlan} name in {@code financialPlans}
     * and returns the email if true.
     */
    public String gatherEmailsContainsFinancialPlan(String prompt) {
        StringBuilder result = new StringBuilder();

        for (FinancialPlan financialPlan : financialPlans) {
            // Perform a case-insensitive check if the financial plan contains the prompt as a substring
            if (financialPlan.containsSubstring(prompt)) {
                result.append(email); // Add the email to the result string
            }
        }
        return result.toString();
    }

    /**
     * Checks if the given {@code prompt} is a substring of {@code tag} name in {@code Tags}
     * and returns the email if true.
     */
    public String gatherEmailsContainsTag(String prompt) {
        StringBuilder result = new StringBuilder();

        for (Tag tag : tags) {
            // Perform a case-insensitive check if the tag contains the prompt substring
            if (tag.containsSubstring(prompt)) {
                result.append(email); // Add the email to the result string
            }
        }
        return result.toString();
    }

    public boolean isSameAppointmentDate(LocalDateTime date) {
        return appointment.isSameDate(date);
    }

    public Person clearAppointment() {
        return new Person(name, phone, email, address, nextOfKinName,
                nextOfKinPhone, financialPlans,
                tags, NullAppointment.getNullAppointment());
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
                && tags.equals(otherPerson.tags)
                && appointment.equals(otherPerson.appointment)
                && financialPlans.equals(otherPerson.financialPlans);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, nextOfKinName, nextOfKinPhone,
                financialPlans, tags, appointment);
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
                .add("appointment", appointment)
                .toString();
    }

}
