package seedu.address.model.doctor;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Represents a Doctor in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Doctor {
    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Gender gender;
    private final Age age;
    private final Address address;
    private ArrayList<Appointment> appointments = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Doctor(Name name, Phone phone, Email email, Gender gender, Age age, Address address) {
        requireAllNonNull(name, phone, email, gender, age, address);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.address = address;
    }

    /**
     * Every field must be present and not null.
     */
    public Doctor(Name name, Phone phone, Email email, Gender gender, Age age, Address address,
                  ArrayList<Appointment> appointments) {
        requireAllNonNull(name, phone, email, gender, age, address);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.appointments = appointments;
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
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }
    public Gender getGender() {
        return gender;
    }
    public Age getAge() {
        return age;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameDoctor(Doctor otherDoctor) {
        if (otherDoctor == this) {
            return true;
        }

        return otherDoctor != null
                && otherDoctor.getName().equals(getName());
    }


    /**
     * Returns true if both persons have the same name, phone number and nric fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Doctor)) {
            return false;
        }

        Doctor otherDoctor = (Doctor) other;
        return isSameDoctor(otherDoctor);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, gender, age, address);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("gender", gender)
                .add("age", age)
                .add("address", address)
                .add("appointments", appointments)
                .toString();
    }


    /**
     * Returns true if patient already has this same Appointment
     * @param otherAppointment Appointment to check if it exists
     * @return if patient has this Appointment
     */
    public boolean hasAppointment(Appointment otherAppointment) {
        boolean result = false;
        for (Appointment appointment: appointments) {
            if (otherAppointment.getDateTime().equals(appointment.getDateTime())) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Returns true if doctor has an appointment on the input dateTime
     * @param dateTime LocalDateTime to check if doctor has an appointment on this time
     * @return true if this doctor has an appointment on the given timeslot
     */
    public boolean hasAppointmentOnTimeslot(LocalDateTime dateTime) {
        for (Appointment appointment : appointments) {
            if (appointment.getDateTime().equals(dateTime)) {
                return true;
            }
        }
        return false;
    }

    /**
     * returns First Appointment of the Patient
     *
     * @return First Appointment of patient
     */
    public Appointment firstAppointment() {
        return appointments.get(0);
    }

    /**
     * Adds an appointment to this Person
     * @param toAdd Appointment to be added
     */
    public void addAppointment(Appointment toAdd) {
        this.appointments.add(toAdd);
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    /**
     * @param index Index of the appointment to delete
     * @return An ArrayList without the deleted appointment
     */
    public Appointment deleteAppointment(int index) {
        Appointment appt = appointments.get(index);
        this.appointments.remove(index);
        return appt;
    }

    /**
     * Edits the appointment at the input index with the updated Appointment
     * @param old the old Appointment to replace
     * @param editedAppointment the updated Appointment
     */
    public void editAppointment(Appointment old, Appointment editedAppointment) {
        int index = appointments.indexOf(old);
        appointments.set(index, editedAppointment);
    }
}
