package seedu.address.model.person.dentist;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
/**
 * Represents a Dentist in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Dentist extends Person {
    // Identity fields
    private final Specialization specialization;
    private final Yoe yoe;
    private long id;

    // Solution below inspired and adapted from
    // https://github.com/AY2223S2-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/model/person/doctor/Doctor.java
    /**
     * Initializes {@code Dentist} object without {@code id}.
     * Every field must be present and not null.
     *
     * @param name              name of dentist
     * @param phone             phone number of dentist
     * @param email             email of dentist
     * @param address           address of dentist
     * @param specialization    specialization of dentist
     * @param yoe               number of years of experience of dentist
     * @param tags              tags
     */
    public Dentist(Name name, Phone phone, Email email, Address address,
                   Specialization specialization, Yoe yoe, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        requireAllNonNull(name, phone, email, address, specialization, yoe, tags);
        this.specialization = specialization;
        this.yoe = yoe;
        this.id = -1;
    }

    /**
     * Initializes {@code Dentist} object with {@code id}.
     * Every field must be present and not null.
     *
     * @param name              name of dentist
     * @param phone             phone number of dentist
     * @param email             email of dentist
     * @param address           address of dentist
     * @param specialization    specialization of dentist
     * @param yoe               number of years of experience of dentist
     * @param dentistId         dentist ID
     * @param tags              tags
     */
    public Dentist(Name name, Phone phone, Email email, Address address,
                   Specialization specialization, Yoe yoe, long dentistId, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        requireAllNonNull(name, phone, email, address, specialization, yoe, tags);
        this.specialization = specialization;
        this.yoe = yoe;
        this.id = dentistId;
    }

    public void setId(long dentistId) {
        this.id = dentistId;
    }

    public long getId() {
        return this.id;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public Yoe getYoe() {
        return yoe;
    }

    /**
     * Returns true if both dentists have the same name.
     */
    public boolean isSameDentist(Dentist otherDentist) {
        if (otherDentist == this) {
            return true;
        }

        return otherDentist != null
                && otherDentist.getName().equals(getName());
    }

    /**
     * Returns true if both dentists have all identical data fields.
     * This defines a stronger notion of equality between two dentists.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Dentist)) {
            return false;
        }

        Dentist otherDentist = (Dentist) other;
        return otherDentist.getName().equals(getName())
                && otherDentist.getPhone().equals(getPhone())
                && otherDentist.getEmail().equals(getEmail())
                && otherDentist.getAddress().equals(getAddress())
                && otherDentist.getSpecialization().equals(getSpecialization())
                && otherDentist.getYoe().equals(getYoe())
                && otherDentist.getTags().equals(getTags())
                && this.getId() == otherDentist.getId();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getAddress(),
                getSpecialization(), getYoe(), getTags(), getId());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Specialization: ")
                .append(getSpecialization())
                .append("; Years of Experience: ")
                .append(getYoe())
                .append("; Dentist ID: ")
                .append(getId())
                .append("; Tags: ");
        this.getTags().forEach(builder::append);

        return builder.toString();
    }
}
