package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Person {

    // Identity fields
    protected final Name name;
    protected final Phone phone;
    protected final Email email;
    protected final Gender gender;
    protected final Ic ic;

    // Data fields
    protected final Address address;
    protected final Set<Tag> tags = new HashSet<>();
    protected final Remark remark;
    protected final Set<Appointment> appointments = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Remark remark, Gender gender, Ic ic,
                  Set<Appointment> appointments, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, gender, ic, appointments, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.remark = remark;
        this.gender = gender;
        this.ic = ic;
        this.tags.addAll(tags);
        this.appointments.addAll(appointments);
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

    public Remark getRemark() {
        return remark;
    }

    public Gender getGender() {
        return gender;
    }

    public Ic getIc() {
        return ic;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same {@code Ic}.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getIc().equals(getIc());
    }

    /**
     * Returns true if this person has the given {@code Ic}.
     */
    public boolean hasIc(Ic ic) {
        return this.ic.equals(ic);
    }

    /**
     * Checks if this person has an appointment at the given {@Code dateTime}.
     *
     * @param dateTime the time of appointment to be checked.
     * @return true is this person has an appointment at the specified time.
     */
    public boolean hasAppointmentAt(AppointmentTime dateTime) {
        requireNonNull(dateTime);
        // check from set of appointments
        for (Appointment a : appointments) {
            if (a.getAppointmentTime().equals(dateTime)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds the given {@code Appointment} to this {@code Person}'s set of appointments.
     *
     * @param appointment the appointment to be added.
     */
    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    /**
     * Removes the given {@code Appointment} from this {@code Person}'s set of appointments.
     *
     * @param appointment the appointment to be removed.
     */
    public void deleteAppointment(Appointment appointment) {
        this.appointments.remove(appointment);
    }

    /**
     * Retrieves the list of appointments stored in this medical facility.
     *
     * @return An ArrayList containing the appointments currently registered in the facility.
     */
    public Set<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Returns true if person is a {@code Patient}.
     */
    public boolean isPatient() {
        return false;
    }

    /**
     * Returns true if person is a {@code Doctor}.
     */
    public boolean isDoctor() {
        return false;
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
                && gender.equals(otherPerson.gender)
                && ic.equals(otherPerson.ic)
                && appointments.equals(otherPerson.appointments)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, gender, ic, appointments, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("remark", remark)
                .add("gender", gender)
                .add("nric", ic)
                .add("appointments", appointments)
                .add("tags", tags)
                .toString();
    }
}
